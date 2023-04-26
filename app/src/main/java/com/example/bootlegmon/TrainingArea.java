package com.example.bootlegmon;

import java.util.Collections;
import java.util.HashMap;

public class TrainingArea{
    private static HashMap<Integer, Lutemon> lutemonsInTraining = new HashMap();

    public static void deleteLutemonInTraining(int ID){
        lutemonsInTraining.remove(ID);
    }
    public static void addLutemonInTraining(int ID, Lutemon Lutemon){
        lutemonsInTraining.put(ID,Lutemon);
    }

    public static Lutemon getLutemonInTraining(int ID) {
        return (lutemonsInTraining.get(ID));
    }

    public static int sizeOfTrainingArea(){
        int k;
        if(lutemonsInTraining.size()==0){
            k=0;
        }
        else{
            k= Collections.max(lutemonsInTraining.keySet());
        }
        return k;
    }

}
