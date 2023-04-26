package com.example.bootlegmon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class activity_trainlutemon extends AppCompatActivity {
    private static int lutemonIDfromTrainingList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainlutemon);
    }
    public void trainingPress(View view){
        int EXP=TrainingArea.getLutemonInTraining(getNumber()).getLutemonExperience();
        int ATTACK=TrainingArea.getLutemonInTraining(getNumber()).getLutemonAttack();
        EXP++;
        ATTACK++;
        TrainingArea.getLutemonInTraining(getNumber()).setLutemonExperience(EXP);
        TrainingArea.getLutemonInTraining(getNumber()).setLutemonAttack(ATTACK);
        Toast.makeText(getApplicationContext(),"Lutemon "+TrainingArea.getLutemonInTraining(getNumber()).getLutemonName()+" on nyt kokemustasolla: "+EXP, Toast.LENGTH_SHORT).show();
    }

    public static void setNumber(int lutemonIDfromTrainingList) {
        activity_trainlutemon.lutemonIDfromTrainingList = lutemonIDfromTrainingList;
    }

    public static int getNumber() {
        return lutemonIDfromTrainingList;
    }
}