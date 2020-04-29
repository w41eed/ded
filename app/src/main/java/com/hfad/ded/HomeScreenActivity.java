package com.hfad.ded;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;




public class HomeScreenActivity extends AppCompatActivity {

    private ArrayList<String> wordList;
    private BufferedReader reader;

    private storageHandler storage;
    private String gender, sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_layout);


        //Initialize BufferReader
        reader = null;

        //Initialize word Lists
        wordList = new ArrayList<>();

        //Load Words from txt files
        loadData();

        storage = new storageHandler(this);
        setHomeAnim(storage.getGender());
        sound = storage.getSound();

    }


    //Force user to use home icon
    @Override
    public void onBackPressed() {

    }



    //Loads the words from file to wordList
    private void loadData(){

        try {
            //Open file
            reader = new BufferedReader( new InputStreamReader(getAssets().open("words.txt")));
            //Go through file and add word to corresponding List
            String mLine;
            while((mLine = reader.readLine()) != null){

                int len=mLine.length();
                //Add words with length 2 - 10
                if(len > 3 && len <= 10) {
                    //Add line to  Word list
                    wordList.add(mLine);
                }

            }
        } catch (IOException e) {
                //Log if exception TO-DO
        } finally {
            //close the file
            if (reader != null){
                try{
                    reader.close();
                } catch (IOException e){

                }
            }
        }


    }


    //Takes user to classic mode screen
    public void classicMode(View view){

        Intent intent = new Intent(getApplicationContext(), classicModeActivity.class);
        intent.putExtra("WORDS", wordList);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }



    //Takes user to settings page screen
    public void settingsPage(View view){
        Intent intent = new Intent(getApplicationContext(), settingsActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void setHomeAnim(String gender){

        ImageView anim = findViewById(R.id.hanging_anim);

        if(gender.equals("male")){
            anim.setImageResource(R.drawable.male_hanging_anim);
        } else {
            anim.setImageResource(R.drawable.female_hanging_anim);
        }
    }

}
