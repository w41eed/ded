package com.hfad.ded;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class settingsActivity extends AppCompatActivity {

    private String gender, keyboardStyle, sound;
    private ImageView male, female, qwerty, abcdef, sound_on, sound_off;
    private storageHandler storage;

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
        Intent intent = new Intent(getApplicationContext(), HomeScreenActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }






    //Sets user's character choice
    public void chooseCharacter(View view) {

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

    }


}
