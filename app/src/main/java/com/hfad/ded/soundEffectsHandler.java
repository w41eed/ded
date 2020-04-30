package com.hfad.ded;


import android.content.Context;
import android.media.MediaPlayer;

//This class deals with playing sound effects associated with certain touch interactions.
public class soundEffectsHandler {

    private MediaPlayer mp;
    private Context context;
    private String sound;


    public soundEffectsHandler(Context context){
        this.context = context;
    }

    public void setSoundToggle(String sound){
        this.sound = sound;
    }


    //plays the button press
    public void playButtonPress(){

        if(sound.equals("sound_on")) {
            mp = MediaPlayer.create(context, R.raw.button_press_sound);
            mp.start();
        }

    }

    //plays the correct guess sound
    public void playCorrectGuess(){

        if(sound.equals("sound_on")) {
            mp = MediaPlayer.create(context, R.raw.correct_guess_sound);
            mp.start();
        }

    }


    //plays the Game Over sound
    public void playGameOver(){

        if(sound.equals("sound_on")) {
            mp = MediaPlayer.create(context, R.raw.game_over_sound);
            mp.start();
        }

    }


    //plays sound when user taps keu on keuboard
    public void playKeyPress(){

        if(sound.equals("sound_on")) {
            mp = MediaPlayer.create(context, R.raw.key_press_bubble_sound);
            mp.start();
        }

    }


    //plays the sound of pencil when user guesses wrong letter
    public void playPencilStrike(){

        if(sound.equals("sound_on")) {
            mp = MediaPlayer.create(context, R.raw.pencil_sound);
            mp.start();
        }

    }


}
