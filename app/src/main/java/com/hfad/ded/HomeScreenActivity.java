package com.hfad.ded;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;




public class HomeScreenActivity extends AppCompatActivity {

    private ArrayList<String> wordList;
    //private TextView classicBtn, dedBtn, settingsBtn;
    private BufferedReader reader;

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


    }

    //Loads the words from file to wordList
    private void loadData(){

        try {
            //Open file
            reader = new BufferedReader( new InputStreamReader(getAssets().open("wordList.txt")));
            //Go through file and add word to corresponding List
            String mLine;
            while((mLine = reader.readLine()) != null){
                //Add line to  Word list
                wordList.add(mLine);
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
    }


    //Takes user to ded mode screen
    public void dedMode(View view){

    }


    //Takes user to settings page screen
    public void settingsPage(View view){

    }
}
