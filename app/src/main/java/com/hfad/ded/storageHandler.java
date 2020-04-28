package com.hfad.ded;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class storageHandler {

    //This class stores user preferences using shard preferences


    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public storageHandler(Context context){

        sharedPref = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

    }



    //Get and set gender
    public void setGender(String gender){
        editor.putString("gender", gender);
        editor.apply();
    }

    public String getGender(){
        return sharedPref.getString("gender", "male");
    }



    //Get and set keyboard preference
    public void setKeyboard(String keyboard){
        editor.putString("keyboard", keyboard);
        editor.apply();
    }

    public String getKeyboard(){
        return sharedPref.getString("keyboard", "qwerty");
    }


    //get and set sound preference
    public void setSound(String sound){
        editor.putString("sound", sound);
        editor.apply();
    }

    public String getSound(){
        return sharedPref.getString("sound", "sound_on");
    }


    //Get and set roundNum for when user reopens app
    public void setRoundNum(int round){
        editor.putInt("round", round);
        editor.apply();
    }

    public int getRoundNum(){
        return sharedPref.getInt("round", 1);
    }



    //Get and set highscore
    public void setHighScore(int score){
        editor.putInt("highScore", score);
        editor.apply();
    }

    public int getHighScore(){
        return sharedPref.getInt("highScore", 1);
    }



}
