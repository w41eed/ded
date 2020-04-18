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
    private ArrayList<Integer> indexOfOccurence;
    private ArrayList<ImageView> dashLetters;
    private ArrayList<View> lettersUsed;
    private ArrayList<ImageView> correctsUsed;
    private ArrayList<ImageView> wrongsUsed;

    private String letterChosen;
    private String currWord;

    private int wordLen;
    private int currLen;
    private int chancesLeft;
    private int score;


    private Context context;
    private Activity activity;

    private resourcesClass resource;


    public GameLogic(ArrayList<String> words, Context con, Activity act){


        context = con;
        activity = act;
        //Initialize wordList
        wordList = words;
        //Initialize wordLen
        wordLen = 0;
        //initialize chancesLeft
        chancesLeft = 6;
        //initialize score
        score = 0;
        //initialize index of occurrences
        indexOfOccurence = new ArrayList<>();

        lettersUsed = new ArrayList<>();
        correctsUsed = new ArrayList<>();
        wrongsUsed = new ArrayList<>();


        //Get Random Word
        currWord = randomWord(wordList);
        wordLen = currWord.length();
        currLen = wordLen;
        createDashes(wordLen);

        resource = new resourcesClass();


    }

    //resets everything for a new round
    private void resetGame(){


        //Make all letters clickable again
        for(int i =0; i<lettersUsed.size(); i++){
            lettersUsed.get(i).setClickable(true);
        }

        //Make all correct images invisible
        for(int i=0; i<correctsUsed.size(); i++){
             correctsUsed.get(i).setVisibility(View.INVISIBLE);
        }

        //Make all wrong images invisible
        for(int i=0; i<wrongsUsed.size(); i++){
            wrongsUsed.get(i).setVisibility(View.INVISIBLE);
        }

        //

        //Reset the tracking arrayLists
        lettersUsed = new ArrayList<>();
        correctsUsed = new ArrayList<>();
        wrongsUsed = new ArrayList<>();

        //Reset indexOfOccurrences
        indexOfOccurence = new ArrayList<>();

        //Get New Random Word
        currWord = randomWord(wordList);
        wordLen = currWord.length();
        currLen = wordLen;

        //Create Dashes
        createDashes(wordLen);

        //reset Chances
        chancesLeft = 6;
        chancesLeft(chancesLeft);


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

        //FOR TESTING
        TextView test = activity.findViewById(R.id.test);
        test.setText(words.get(randNum));

        return words.get(randNum);
    }


    //when user chooses a letter
    public void chooseALetter(View view){

        //retrieves view Id in string format
        letterChosen = context.getResources().getResourceEntryName(view.getId());

        //Letter is not clickable now
        view.setClickable(false);

        //Add letter to keep track
        lettersUsed.add(view);

        //Check if letter is in the word
        indexOfOccurence = whereIsLetterInWord(letterChosen, currWord);

        if(indexOfOccurence != null){
            //Letter is correct

            //Correct image over letter
            isCorrect(view);

            for(int i=0; i<indexOfOccurence.size(); i++){

                dashLetters.get(indexOfOccurence.get(i)).setImageResource(resource.getLetterSrc(letterChosen));
            }

            currLen = currLen - indexOfOccurence.size();
            if(currLen == 0){
                resetGame();
            }


        } else {
            //Letter is wrong
            isWrong(view);
            chancesLeft--;
            chancesLeft(chancesLeft);

            if(chancesLeft == 0){


                //for testing
                resetGame();
                //gameOver();
            }


        }


    }

    //If chosen letter is correct then do this
    public void isCorrect(View view){
        String correct = "correct";
        String tag = String.valueOf(view.getTag());
        correct = correct + tag;
        int resId = context.getResources().getIdentifier(correct,"id", context.getPackageName());
        ImageView correctView = activity.findViewById(resId);
        correctView.setImageResource(R.drawable.correct_anim2);
        correctView.setVisibility(View.VISIBLE);

        //keep track of correct images used
        correctsUsed.add(correctView);

    }

    //If chosen letter is wrong then do this
    public void isWrong(View view){
        String wrong = "wrong";
        String tag = String.valueOf(view.getTag());
        wrong = wrong + tag;
        int resId = context.getResources().getIdentifier(wrong,"id", context.getPackageName());
        ImageView wrongView = activity.findViewById(resId);
        wrongView.setImageResource(R.drawable.wrong_anim2);

        wrongView.setVisibility(View.VISIBLE);

        //keep track of wrong images used
        wrongsUsed.add(wrongView);
    }


    //Creates a linear layout with 2 image views in it
    private void createDashes(int blocks){

        dashLetters = new ArrayList<>();

        ViewGroup cl = activity.findViewById(R.id.classicLayout);

        LayoutInflater inflater = activity.getLayoutInflater();
        FlexboxLayout flex =  cl.findViewById(R.id.dashLayout);//inflater.inflate(R.layout.letter_dashes_layout,null);

        //Remove previous views
        flex.removeAllViews();

        for (int i = 0; i < blocks; i++) {

            //Get a Clone of the Linear Layout with the ImageViews
            LinearLayout child = (LinearLayout) inflater.inflate(R.layout.dashes_and_letters, null);
            ImageView let =  child.findViewById(R.id.correct_letter);

            //Add image view to dashLetters to keep track of them.
            dashLetters.add(let);

            /*let.setImageResource(R.drawable.a);*/

            //Add view to flex Layout
            flex.addView(child);

        }
    }


    //Takes in the number of chances left and changes
    // the hangman imageView to reflect chances left
    private void chancesLeft(int chanceNum){

        //Get Hangman view
        ImageView hangMan = activity.findViewById(R.id.hangMan);

        //Change Image based on strike num
        hangMan.setImageResource(resource.getHangManSrc(chanceNum));

    }



}
