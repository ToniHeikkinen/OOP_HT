package com.example.bootlegmon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.widget.TextView;

public class activity_fightlutemons extends AppCompatActivity {
    private static int lutemonID1,lutemonID2;
    private TextView BattleTextOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fightlutemons);

        BattleTextOutput=findViewById(R.id.BattleTextOutput);
        BattleTextOutput.setMovementMethod(new ScrollingMovementMethod());

        StringBuilder concatenatingIntermediateString = new StringBuilder(); // Used to concatenate all of the events during the battle.
        String boxText =null;

        // Lutemon health values are treated as separate variables for the sake of convenience.
        int constantAttackDamage1=HomeArea.getLutemonInHome(getNumber1()).getLutemonAttack();
        int constantAttackDamage2=HomeArea.getLutemonInHome(getNumber2()).getLutemonAttack();
        // Defence logic: 0 negates 0%, 1 negates 5%, 2 negates 10%, 3 negates 15%, 4 negates 20% //
        //              (Black)        (Orange)      (Pink)         (Green)         (White)       //
        float constantDefenceMultiplier1= (float) (1-(HomeArea.getLutemonInHome(getNumber1()).getLutemonDefence()*0.05));
        float constantDefenceMultiplier2= (float) (1-(HomeArea.getLutemonInHome(getNumber2()).getLutemonDefence()*0.05));

        float loopStartingHP1=HomeArea.getLutemonInHome(getNumber1()).getLutemonHealth();
        float loopStartingHP2=HomeArea.getLutemonInHome(getNumber2()).getLutemonHealth();

        concatenatingIntermediateString.append("Taistelu alkaa! \n");
        while(loopStartingHP1>0 && loopStartingHP2>0) { // While loop, within one round both lutemons attack each other once, if conditions are for breaking the loop and deleting the first lutemon whose health goes below 1.
            // First attack within the same loop iteration:
            concatenatingIntermediateString.append(("Nro: 1: " + HomeArea.getLutemonInHome(getNumber1()).getLutemonColor() + " (" + HomeArea.getLutemonInHome(getNumber1()).getLutemonName() + ") ; [Att: " + HomeArea.getLutemonInHome(getNumber1()).getLutemonAttack() + "], [Def: " + HomeArea.getLutemonInHome(getNumber1()).getLutemonDefence() + "], [EXP: " + HomeArea.getLutemonInHome(getNumber1()).getLutemonExperience() + "], [Health: " + Math.round(loopStartingHP1*100.0)/100.0 + "/" + HomeArea.getLutemonInHome(getNumber1()).getLutemonMaxHealth() + "] \n"));
            concatenatingIntermediateString.append(("Nro: 2: " + HomeArea.getLutemonInHome(getNumber2()).getLutemonColor() + " (" + HomeArea.getLutemonInHome(getNumber2()).getLutemonName() + ") ; [Att: " + HomeArea.getLutemonInHome(getNumber2()).getLutemonAttack() + "], [Def: " + HomeArea.getLutemonInHome(getNumber2()).getLutemonDefence() + "], [EXP: " + HomeArea.getLutemonInHome(getNumber2()).getLutemonExperience() + "], [Health: " + Math.round(loopStartingHP2*100.0)/100.0 + "/" + HomeArea.getLutemonInHome(getNumber2()).getLutemonMaxHealth() + "] \n"));
            // First actual attack:
            concatenatingIntermediateString.append("Nro: 1: ("+HomeArea.getLutemonInHome(getNumber1()).getLutemonName()+"), Puolustautuu lutemonia: ("+HomeArea.getLutemonInHome(getNumber2()).getLutemonName()+") vastaan... \n");
            loopStartingHP1 = loopStartingHP1 - constantAttackDamage2*constantDefenceMultiplier1; // 2 attacks 1, reduces 1's HP by 2's attack value multiplied by 1's defence constant.
            // If first attack was lethal or not:
            if (loopStartingHP1 <= 0) { // Checking if 1 took too much damage from 2
                concatenatingIntermediateString.append(("Nro: 1, Lutemon: " + HomeArea.getLutemonInHome(getNumber1()).getLutemonName() + " kuoli! \n"));

                HomeArea.deleteLutemonInHome(getNumber1()); // Deleting the dead lutemon.
                activity_homelist.deleteCheckBox(getNumber1()); // Deleting associated checkbox.

                int EXP=HomeArea.getLutemonInHome(getNumber2()).getLutemonExperience(); // Increasing winner's exp and attack.
                int ATTACK=HomeArea.getLutemonInHome(getNumber2()).getLutemonAttack();
                EXP++;
                ATTACK++;
                HomeArea.getLutemonInHome(getNumber2()).setLutemonExperience(EXP);
                HomeArea.getLutemonInHome(getNumber2()).setLutemonAttack(ATTACK);

                break; // Breaking from the loop, if lutemon 1 died.

            } else { // If lutemon 1 survived.
                concatenatingIntermediateString.append("Nro: 1, Lutemon: " + HomeArea.getLutemonInHome(getNumber1()).getLutemonName() + " selvisi hyökkäyksen! \n");
            }
            // End of first attack ^

            concatenatingIntermediateString.append("\n");

            // Second attack within the same loop iteration:
            concatenatingIntermediateString.append(("Nro: 1: " + HomeArea.getLutemonInHome(getNumber1()).getLutemonColor() + " (" + HomeArea.getLutemonInHome(getNumber1()).getLutemonName() + ") ; [Att: " + HomeArea.getLutemonInHome(getNumber1()).getLutemonAttack() + "], [Def: " + HomeArea.getLutemonInHome(getNumber1()).getLutemonDefence() + "], [EXP: " + HomeArea.getLutemonInHome(getNumber1()).getLutemonExperience() + "], [Health: " + Math.round(loopStartingHP1*100.0)/100.0 + "/" + HomeArea.getLutemonInHome(getNumber1()).getLutemonMaxHealth() + "] \n"));
            concatenatingIntermediateString.append(("Nro: 2: " + HomeArea.getLutemonInHome(getNumber2()).getLutemonColor() + " (" + HomeArea.getLutemonInHome(getNumber2()).getLutemonName() + ") ; [Att: " + HomeArea.getLutemonInHome(getNumber2()).getLutemonAttack() + "], [Def: " + HomeArea.getLutemonInHome(getNumber2()).getLutemonDefence() + "], [EXP: " + HomeArea.getLutemonInHome(getNumber2()).getLutemonExperience() + "], [Health: " + Math.round(loopStartingHP2*100.0)/100.0 + "/" + HomeArea.getLutemonInHome(getNumber2()).getLutemonMaxHealth() + "] \n"));
            // Second actual attack:
            concatenatingIntermediateString.append("Nro: 2: ("+HomeArea.getLutemonInHome(getNumber2()).getLutemonName()+"), Puolustautuu lutemonia: ("+HomeArea.getLutemonInHome(getNumber1()).getLutemonName()+") vastaan... \n");
            loopStartingHP2 = loopStartingHP2 - constantAttackDamage1*constantDefenceMultiplier2; // 1 attacks 2, reduces 2's HP by 1's attack value multiplied by 2's defence constant.
            // If second attack was lethal or not:
            if (loopStartingHP2 <= 0) { // Checking if 2 took too much damage from 1.
                concatenatingIntermediateString.append(("Nro: 2, Lutemon: " + HomeArea.getLutemonInHome(getNumber2()).getLutemonName() + " kuoli! \n"));

                HomeArea.deleteLutemonInHome(getNumber2()); // Deleting the dead lutemon.
                activity_homelist.deleteCheckBox(getNumber2()); // Deleting associated checkbox.

                int EXP=HomeArea.getLutemonInHome(getNumber1()).getLutemonExperience(); // Increasing winner's exp and attack.
                int ATTACK=HomeArea.getLutemonInHome(getNumber1()).getLutemonAttack();
                EXP++;
                ATTACK++;
                HomeArea.getLutemonInHome(getNumber1()).setLutemonExperience(EXP);
                HomeArea.getLutemonInHome(getNumber1()).setLutemonAttack(ATTACK);

                break; // Breaking from the loop, if lutemon 2 died.

            } else { // If lutemon 2 survived.
                concatenatingIntermediateString.append(("Nro: 2, Lutemon: " + HomeArea.getLutemonInHome(getNumber2()).getLutemonName() + " selvisi hyökkäyksen! \n"));
            }
            concatenatingIntermediateString.append("\n");
            concatenatingIntermediateString.append("Uusi kierros alkaa! \n");
            // End of loop iteration.
        }
        // End of while loop.

        concatenatingIntermediateString.append(("Taistelu on loppunut. \n"));
        concatenatingIntermediateString.append(("\n"));
        boxText=concatenatingIntermediateString.toString();

        BattleTextOutput.setText(boxText); // Setting textbox view.
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) { // For when the user is done looking at the BattleTextOutput and tries to go back to the previous activity, they will be redirected directly to the MainActivity.
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;
    }
    public static void setNumber1(int lutemonID1) { // Getters/Setters for the first and the second lutemon selected back in activity_homelist.
        activity_fightlutemons.lutemonID1 = lutemonID1;
    }

    public static int getNumber1() {
        return lutemonID1;
    }

    public static void setNumber2(int lutemonID2) {
        activity_fightlutemons.lutemonID2 = lutemonID2;
    }

    public static int getNumber2() {
        return lutemonID2;
    }
}
