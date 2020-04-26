package com.hfad.ded;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ActivityInfo;
import android.os.Bundle;


//This class instantiates when the application is first launched
// and exists throughout the lifespan of the app
public class dedApp extends Application {


    private String gender;
    private String roundNum;
    private String highScore;
    private String keyboardStyle;
    private boolean sound;





    @Override
    public void onCreate(){
        super.onCreate();
    }




    //Get and set gender
    public void setGender(String gender){
        this.gender = gender;
    }

    public String getGender(){
        return gender;
    }


    //Get and set roundNum for when user reopens app
    public void setRoundNum(String round){
        roundNum = round;
    }

    public String getRoundNum(){
        return roundNum;
    }



    //Get and set highscore
    public void setHighScore(String score){
        highScore = score;
    }

    public String getHighScore(){
        return highScore;
    }



    //Get and set keyboard preference
    public void setKeyboard(String keyboard){
        this.keyboardStyle = keyboard;
    }

    public String getKeyboard(){
        return keyboardStyle;
    }


    //get and set sound preference
    public void setSound(boolean sound){
        this.sound = sound;
    }

    public boolean getSound(){
        return sound;
    }



}


