package com.hfad.ded;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;


import androidx.appcompat.app.AppCompatActivity;

public class classicModeActivity extends AppCompatActivity {

    private ArrayList<String> wordList;
    private String currWord;
    private int wordLen;
    private int strikeNum;
    private int score;
    private ArrayList<Integer> indexOfOccurence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classic_screen_layout);


        initVariables();
        getWordList();

    }


    //Initialize variables
    private void initVariables(){

        //Initialize wordList
        wordList = new ArrayList<>();
        //Initialize wordLen
        wordLen = 0;
        //initialize strikeNum
        strikeNum = 0;
        //initialize score
        score = 0;
        //initialize index of occurences
        indexOfOccurence = new ArrayList<>();
    }



    @SuppressWarnings("unchecked")
    //gets the word list from intent
    private void getWordList(){

        //Get extra from intent
        wordList = (ArrayList<String>) getIntent().getSerializableExtra("WORDS");

    }


    //Checks If letter selected is in the current selected string
    //if letter is present, adds on top of dash in corresponding indices
    //if letter is absent then adds a strike
    private ArrayList<Integer> whereIsLetterInWord(String letter, String word){

        ArrayList<Integer> indices = new ArrayList<>();
        for(int i=0; i<word.length(); i++){
            //If character at i position of Word is equal to the letter
            //which is charAt(0) to use equality sign
            if(word.charAt(i) == letter.charAt(0)){
                indices.add(i);
            }
        }
        if(indices.isEmpty()){
            //If indices is empty, it means no occurrence of letter
            //so return null
            return null;
        } else {
            //return list of indices where letter appears
            return indices;
        }
    }


    //returns random word from wordlist
    private String randomWord(ArrayList<String> words){
        Random rand = new Random();
        int upperBound = words.size();
        int randNum = rand.nextInt(upperBound);
        return words.get(randNum);
    }


    //sets up game for a new round
    private void newRound(){


    }


    //when user chooses a letter
    public void chooseLetter(View view){
    }



}
