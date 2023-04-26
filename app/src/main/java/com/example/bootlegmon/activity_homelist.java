package com.example.bootlegmon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.HashMap;

public class activity_homelist extends AppCompatActivity {
    private static HashMap<Integer, CheckBox> checkBoxMapHome = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homelist);

        for(int i = 1; i<= HomeArea.sizeOfHome(); i++){ // Home.sizeOfHome, returns largest key value
            if(HomeArea.getLutemonInHome(i)==null){
                // Skip ID-less lutemons, (if already moved to different storage)
            }
            else {
                addTextBoxHome(i);
            }
        }
    }
    public void addTextBoxHome(int i){ // onCreate's for loop calls this function if some number is found in HashMap: checkBoxMapHome
        LinearLayout layout= findViewById(R.id.rootlayout);
        CheckBox newCh = new CheckBox(this);  // Dynamically creates checkbox and associated text
        newCh.setText("Nimi: "+ HomeArea.getLutemonInHome(i).getLutemonName()+"\n Väri: "+HomeArea.getLutemonInHome(i).getLutemonColor()+"\n Hyökkäys: "+HomeArea.getLutemonInHome(i).getLutemonAttack()+"\n Puolustus: "+HomeArea.getLutemonInHome(i).getLutemonDefence()+"\n Elämä: "+HomeArea.getLutemonInHome(i).getLutemonHealth()+"\n Kokemuspisteet: "+HomeArea.getLutemonInHome(i).getLutemonExperience());
        newCh.setId(i);
        checkBoxMapHome.put(i,newCh);
        layout.addView(newCh);
    }

    public static void deleteCheckBox(int ID){ // Called when a pokemon dies during a fight.
        checkBoxMapHome.remove(ID);
    }

    public void isBTNCheckedHome(View view){
        for(int i = 1; i<= HomeArea.sizeOfHome(); i++){
            if(checkBoxMapHome.get(i)==null){
                // Skip ID-less checkboxes
            }else {
                if (checkBoxMapHome.get(i).isChecked()) { // Move lutemons from home area to training area if user checks out given checkboxes.
                    TrainingArea.addLutemonInTraining(i, HomeArea.getLutemonInHome(i));
                    HomeArea.deleteLutemonInHome(i);
                    checkBoxMapHome.remove(i);
                    finish();
                }
            }
        }
    }
    public void battleLutemons(View view){
        int chosenID1=0, chosenID2=0;
        int totalCheckedBoxes=0;
        for(int i = 1; i<= HomeArea.sizeOfHome(); i++){
            if(checkBoxMapHome.get(i)==null){
                // Skip ID-less checkboxes
            }else {
                if (checkBoxMapHome.get(i).isChecked()) { // If user checks a given checkbox:
                    chosenID1=chosenID2;
                    chosenID2=i;
                    totalCheckedBoxes++;
                }
            }
        }

        if(totalCheckedBoxes<2){
            Toast.makeText(getApplicationContext(),"Valitse kaksi lutemonia!", Toast.LENGTH_LONG).show();
        }
        if(totalCheckedBoxes==2){
            activity_fightlutemons.setNumber1(chosenID1);
            activity_fightlutemons.setNumber2(chosenID2);
            Intent battleIntent = new Intent(this, activity_fightlutemons.class); // Starts new activity for fighting the chosen lutemons
            startActivity(battleIntent);
            finish(); // For moving back directly to main, forcing user to reload activity_homelist to view it again.
        }
        if(totalCheckedBoxes>2){
            Toast.makeText(getApplicationContext(),"Valitsit liikaa lutemoneja!", Toast.LENGTH_LONG).show();
        }

    }

}
