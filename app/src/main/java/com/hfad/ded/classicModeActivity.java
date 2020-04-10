package com.hfad.ded;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class classicModeActivity extends AppCompatActivity {

    private ArrayList<String> wordList;
    private String currWord;
    private int wordLen;
    private int strikeNum;
    private int score;
    private ArrayList<Integer> indexOfOccurence;
    private String letterChosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classic_screen_layout);


        initVariables();
        getWordList();


        createDash(5);
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
        //initialize index of occurrences
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

        //retrieves view Id in string format
        letterChosen = getResources().getResourceEntryName(view.getId());

        view.setClickable(false);


        String correct = "correct";
        String tag = String.valueOf(view.getTag());
        correct = correct + tag;
        int resId = getResources().getIdentifier(correct,"id", getPackageName());
        ImageView correctView = findViewById(resId);
        correctView.setVisibility(View.VISIBLE);

        //FOR TESTING
        TextView test = findViewById(R.id.test);
        test.setText(letterChosen);

    }


    //When replacing blank imageviw with correectly selected lewtter,
    //get indices of occurrences of letter and replace letter at that spot
    //To check if word is complete, subtract remaining length of string with length of occurence array



    //Creates a linear layouyt with 2 imagviews in it
    private void createDash(int lines){

       ViewGroup cl = findViewById(R.id.classicLayout);

       LayoutInflater inflater = getLayoutInflater();
       FlexboxLayout fl =  cl.findViewById(R.id.dashLayout);//inflater.inflate(R.layout.letter_dashes_layout,null);


        for (int i = 0; i < lines; i++) {
            LinearLayout child = (LinearLayout) inflater.inflate(R.layout.dashes_and_letters, null);
            ImageView let =  child.findViewById(R.id.correct_letter);
            let.setImageResource(R.drawable.a);

            if(fl != null){
                fl.addView(child);
            }

        }
    }


}
