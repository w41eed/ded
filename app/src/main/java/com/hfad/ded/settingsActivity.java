package com.hfad.ded;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class settingsActivity extends AppCompatActivity {

    private String gender, keyboardStyle, sound;
    private ImageView male, female, qwerty, abcdef, sound_on, sound_off;
    private storageHandler storage;
    private soundEffectsHandler audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        qwerty = findViewById(R.id.qwerty);
        abcdef = findViewById(R.id.abcdef);
        sound_on = findViewById(R.id.sound_on);
        sound_off = findViewById(R.id.sound_off);


        storage = new storageHandler(this);

        gender = storage.getGender();
        keyboardStyle = storage.getKeyboard();
        sound = storage.getSound();

        //create audio handler
        audio = new soundEffectsHandler(this);
        audio.setSoundToggle(sound);

        setCharacter();
        setKeyboard();
        setSound();

    }


    //Force user to use home icon
    @Override
    public void onBackPressed() {

    }





    //Takes user to Home screen
    public void goHome(View view){

        //Play Sound
        audio.playButtonPress();
        //Call intent
        Intent intent = new Intent(getApplicationContext(), HomeScreenActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }






    //Sets user's character choice
    public void chooseCharacter(View view) {

        //Play Sound
        audio.playButtonPress();

        gender = String.valueOf(view.getTag());
        storage.setGender(gender);
        setCharacter();
    }

    //Sets user's character choice
    public void setCharacter(){

        switch (gender){
            case "male":
                male.setImageResource(R.drawable.male_selected);
                female.setImageResource(R.drawable.female_unselected);
                break;
            case "female":
                female.setImageResource(R.drawable.female_selected);
                male.setImageResource(R.drawable.male_unselected);
                break;
        }
    }






    //sets user's keyboard preference
    public void chooseKeyboard(View view) {

        //Play Sound
        audio.playButtonPress();

        keyboardStyle = String.valueOf(view.getTag());
        storage.setKeyboard(keyboardStyle);
        setKeyboard();
    }

    //sets user's keyboard preference
    public void setKeyboard(){

        switch (keyboardStyle){
            case "qwerty":
                qwerty.setImageResource(R.drawable.qwerty_selected);
                abcdef.setImageResource(R.drawable.abcdef_unselected);
                break;
            case "abcdef":
                abcdef.setImageResource(R.drawable.abcdef_selected);
                qwerty.setImageResource(R.drawable.qwerty_unselected);
                break;
        }
    }






    //turns sound on or off
    public void chooseSound(View view) {

        sound = String.valueOf(view.getTag());
        storage.setSound(sound);

        //Play Sound
        audio.setSoundToggle(sound);
        audio.playButtonPress();

        setSound();
    }

    //turns sound on or off
    public void setSound(){

        switch (sound){
            case "sound_on":
                sound_on.setImageResource(R.drawable.on_selected);
                sound_off.setImageResource(R.drawable.off_unselected);
                break;
            case "sound_off":
                sound_off.setImageResource(R.drawable.off_selected);
                sound_on.setImageResource(R.drawable.on_unselected);
                break;
        }
    }




    //Takes user to rate app
    public void rateApp(View view){

        Uri uri = Uri.parse("market://details?id=" + getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
        }


    }


}
