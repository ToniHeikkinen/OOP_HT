package com.example.bootlegmon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private EditText textNameInput;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    public int idCounter=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textNameInput=findViewById(R.id.editnameTxt);
        radioGroup=findViewById(R.id.radioGroup);
    }

    public void addLutemon(View view) {
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioID);
        String lutemonName = textNameInput.getText().toString(); // Name of lutemon
        String lutemonColorType = String.valueOf(radioButton.getText()); // Color of lutemon
        idCounter=idCounter+1; // Increasing ID
        int lutemonID=idCounter;

        if (lutemonColorType.equals("Valkoinen")){
            HomeArea.addLutemonInHome(lutemonID, new lutemonWhite(lutemonName, lutemonColorType,5,4,0,20,20));
        }
        if (lutemonColorType.equals("Vihreä")){
            HomeArea.addLutemonInHome(lutemonID, new lutemonGreen(lutemonName, lutemonColorType,6,3,0,19,19));
        }
        if (lutemonColorType.equals("Pinkki")){
            HomeArea.addLutemonInHome(lutemonID, new lutemonPink(lutemonName, lutemonColorType,7,2,0,18,18));
        }
        if (lutemonColorType.equals("Oranssi")){
            HomeArea.addLutemonInHome(lutemonID, new lutemonOrange(lutemonName, lutemonColorType,8,1,0,17,17));
        }
        if (lutemonColorType.equals("Musta")){
            HomeArea.addLutemonInHome(lutemonID, new lutemonBlack(lutemonName, lutemonColorType,9,0,0,16,16));
        }

    }

    public void moveFromHome(View view) {
        Intent homelist = new Intent(this, activity_homelist.class);
        startActivity(homelist);
    }

    public void moveFromTraining(View view){
        Intent traininglist = new Intent(this, activity_traininglist.class);
        startActivity(traininglist);
    }

}