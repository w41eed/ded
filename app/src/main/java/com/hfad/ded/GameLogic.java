package com.hfad.ded;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.Random;

public class GameLogic {


    private ArrayList<String> wordList;
    private String currWord;
    private int wordLen;
    private int strikeNum;
    private int score;
    private ArrayList<Integer> indexOfOccurence;
    private String letterChosen;

    private Context context;
    private Activity activity;


    public GameLogic(ArrayList<String> words, Context con, Activity act){


        context = con;
        activity = act;

        //Initialize wordList
        wordList = words;


        //Initialize wordLen
        wordLen = 0;


        //initialize strikeNum
        strikeNum = 0;


        //initialize score
        score = 0;


        //initialize index of occurrences
        indexOfOccurence = new ArrayList<>();


        createDashes(3);
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


    //when user chooses a letter
    public void chooseALetter(View view){

        //retrieves view Id in string format
        letterChosen = context.getResources().getResourceEntryName(view.getId());

        view.setClickable(false);

        String correct = "correct";
        String tag = String.valueOf(view.getTag());
        correct = correct + tag;
        int resId = context.getResources().getIdentifier(correct,"id", context.getPackageName());
        ImageView correctView = activity.findViewById(resId);
        correctView.setVisibility(View.VISIBLE);

        //FOR TESTING
        TextView test = activity.findViewById(R.id.test);
        test.setText(letterChosen);

    }



    //Creates a linear layout with 2 image views in it
    private void createDashes(int blocks){

        ViewGroup cl = activity.findViewById(R.id.classicLayout);

        LayoutInflater inflater = activity.getLayoutInflater();
        FlexboxLayout flex =  cl.findViewById(R.id.dashLayout);//inflater.inflate(R.layout.letter_dashes_layout,null);

        for (int i = 0; i < blocks; i++) {

            //Get a Clone of the Linear Layout with the ImageViews
            LinearLayout child = (LinearLayout) inflater.inflate(R.layout.dashes_and_letters, null);
            ImageView let =  child.findViewById(R.id.correct_letter);
            let.setImageResource(R.drawable.a);

            //Add view to flex Layout
            if(flex != null){
                flex.addView(child);
            }
        }
    }


    //When replacing blank image view with correctly selected letter,
    //get indices of occurrences of letter and replace letter at that spot
    //To check if word is complete, subtract remaining length of string with length of occurrence array


}
