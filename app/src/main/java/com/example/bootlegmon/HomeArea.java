package com.example.bootlegmon;

import java.util.Collections;
import java.util.HashMap;

public class HomeArea {
    private static HashMap<Integer, Lutemon> lutemonsInHome = new HashMap();

    public static void deleteLutemonInHome(int ID){
        lutemonsInHome.remove(ID);
    }

    public static void addLutemonInHome(int ID, Lutemon lutemon) {
        lutemonsInHome.put(ID,lutemon);
    }

    public static Lutemon getLutemonInHome(int ID) {
        return (lutemonsInHome.get(ID));
    }

    public static int sizeOfHome(){
        int k;
        if(lutemonsInHome.size()==0){
            k=0;
        }
        else{
            k=Collections.max(lutemonsInHome.keySet());
        }
        return k;
    }

}