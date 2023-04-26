package com.example.bootlegmon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.HashMap;

public class activity_traininglist extends AppCompatActivity {
    private static HashMap<Integer, CheckBox> checkBoxMapTraining = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traininglist);

        for(int i = 1; i<= TrainingArea.sizeOfTrainingArea(); i++){ // Home.sizeOfHome, returns largest key value
            if(TrainingArea.getLutemonInTraining(i)==null){
                // Skip ID-less lutemons, (if already moved to different storage)
            }
            else {
                addTextBoxTraining(i);
            }
        }
    }
    public void addTextBoxTraining(int i){ // onCreate's for loop calls this function if some number is found in HashMap: checkBoxMapTraining
        LinearLayout layout= findViewById(R.id.rootlayout2);
        CheckBox newCh = new CheckBox(this); // Dynamically creating checkbox and text
        newCh.setText("Nimi: "+ TrainingArea.getLutemonInTraining(i).getLutemonName()+"\n Väri: "+TrainingArea.getLutemonInTraining(i).getLutemonColor()+"\n Hyökkäys: "+TrainingArea.getLutemonInTraining(i).getLutemonAttack()+"\n Puolustus: "+TrainingArea.getLutemonInTraining(i).getLutemonDefence()+"\n Elämä: "+TrainingArea.getLutemonInTraining(i).getLutemonHealth()+"\n Kokemuspisteet: "+TrainingArea.getLutemonInTraining(i).getLutemonExperience());
        newCh.setId(i);
        checkBoxMapTraining.put(i,newCh);
        layout.addView(newCh);
    }

    public void isBTNCheckedTraining(View view){
        for(int i = 1; i<= TrainingArea.sizeOfTrainingArea(); i++){
            if(checkBoxMapTraining.get(i)==null){
                // Skip ID-less checkboxes
            }else {
                if (checkBoxMapTraining.get(i).isChecked()) { // Move lutemons from training area to home area if user checks out given checkboxes.
                    HomeArea.addLutemonInHome(i, TrainingArea.getLutemonInTraining(i));
                    TrainingArea.deleteLutemonInTraining(i);
                    checkBoxMapTraining.remove(i);
                    finish();
                }
            }
        }
    }
    public void isBTNCheckedTrainingOneSpecificLutemon(View view){
        int chosenID = 0;
        int totalCheckedBoxes=0;
        for(int i = 1; i<= TrainingArea.sizeOfTrainingArea(); i++){
            if(checkBoxMapTraining.get(i)==null){
                // Skip ID-less checkboxes
            }else {
                if (checkBoxMapTraining.get(i).isChecked()) { // If user checks a given checkbox:
                    chosenID=i;
                    totalCheckedBoxes++;
                }
            }
        }
        if(totalCheckedBoxes==0){
            Toast.makeText(getApplicationContext(),"Valitse vain yksi lutemoni!", Toast.LENGTH_LONG).show();
        }
        if(totalCheckedBoxes==1){
            activity_trainlutemon.setNumber(chosenID);
            Intent intent = new Intent(this, activity_trainlutemon.class); // Starts new activity for training the chosen lutemon
            startActivity(intent);
        }
        if(totalCheckedBoxes>1){
            Toast.makeText(getApplicationContext(),"Valitsit liikaa lutemoneja!", Toast.LENGTH_LONG).show();
        }
    }
}