package com.hfad.ded;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;


public class HomeScreenActivity extends AppCompatActivity {

    private List<String> longWords;
    private List<String> mediumWords;
    private List<String> shortWords;

    //Constants
    private final int LONG_WORDS = 1;
    private final int MEDIUM_WORDS = 2;
    private final int SHORT_WORDS = 3;

    private BufferedReader reader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_layout);


        //Initialize BufferReader
        reader = null;

        //Initialize word Lists
        longWords = new ArrayList<>();
        mediumWords = new ArrayList<>();
        shortWords = new ArrayList<>();

        //Load Words from txt files
        loadData("long.txt",LONG_WORDS);
        loadData("medium.txt", MEDIUM_WORDS);
        loadData("short.txt", SHORT_WORDS);


    }

    //Loads the words from file into longWords, mediumWords or shortWords
    private void loadData(String file, int key){

        try {

            //Open file
            reader = new BufferedReader( new InputStreamReader(getAssets().open(file)));

            //Go through file and add word to corresponding List
            String mLine;
            while((mLine = reader.readLine()) != null){

                //Add line to corresponding Word list
                switch(key) {

                    case LONG_WORDS:
                        longWords.add(mLine);
                        break;
                    case MEDIUM_WORDS:
                        mediumWords.add(mLine);
                        break;
                    case SHORT_WORDS:
                        shortWords.add(mLine);
                        break;
                }
            }
        } catch (IOException e) {

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





}
