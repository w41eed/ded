package com.hfad.ded;


import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;


public class classicModeActivity extends AppCompatActivity {

    private ArrayList<String> wordList;
    private String currWord;
    private int wordLen;
    private int strikeNum;
    private int score;
    private ArrayList<Integer> indexOfOccurence;
    private String letterChosen;
    private GameLogic gameLogic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classic_screen_layout);


        initVariables();
        getWordList();

        gameLogic = new GameLogic(wordList, this, this);


    }


    //Force user to use home icon
    @Override
    public void onBackPressed() {

    }


    //Initialize variables
    private void initVariables(){

        //Initialize wordList
        wordList = new ArrayList<>();
        //Initialize wordLen
        wordLen = 0;
        /*//initialize strikeNum
        strikeNum = 0;
        //initialize score
        score = 0;
        //initialize index of occurrences
        indexOfOccurence = new ArrayList<>();*/
    }



    @SuppressWarnings("unchecked")
    //gets the word list from intent
    private void getWordList(){

        //Get extra from intent
        wordList = (ArrayList<String>) getIntent().getSerializableExtra("WORDS");

    }








    //sets up game for a new round
    private void newRound(){


        //get new word

        //make all strikes invisible

        //make all correct invisible

        //make all letters clickable

        //reset hangman

        //reset strike number

        //make new occurrence array

        //change round number

        //call function to set number of dashes

    }



    //when user chooses a letter
    public void chooseLetter(View view){

        gameLogic.chooseALetter(view);

    }









}
