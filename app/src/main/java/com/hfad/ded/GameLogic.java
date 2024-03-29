package com.hfad.ded;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.Random;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.transition.Slide;
import androidx.transition.Transition;


public class GameLogic {


    private ArrayList<String> wordList;
    private ArrayList<Integer> indexOfOccurence;
    private ArrayList<ImageView> dashLetters, dashDashes, correctsUsed, wrongsUsed;
    private ArrayList<View> lettersUsed;

    private String currWord;

    private int wordLen, currLen, chancesLeft, score, highScore;

    private Context context;
    private Activity activity;

    private resourcesClass resource;

    private LinearLayout gameOver_dialog;
    private LinearLayout win_dialog;

    private ViewFlipper keyboard;

    //Settings
    private String gender, keyboardStyle, sound;
    private storageHandler storage;

    //Audio
    private soundEffectsHandler audio;

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

        setKeyboardClickable(true);

        //set all listeners
        setListeners();


        //get dialog id
        gameOver_dialog = activity.findViewById(R.id.classic_game_over);
        win_dialog = activity.findViewById(R.id.classic_win);
        // move dialog down
        slideDown(gameOver_dialog, 0);
        slideDown(win_dialog, 0);


        //Get settings
        storage = new storageHandler(context);
        gender = storage.getGender();
        keyboardStyle = storage.getKeyboard();
        sound = storage.getSound();
        score = storage.getRoundNum();
        setScore(score);
        highScore = storage.getHighScore();
        setHighScore(highScore);


        //set sound
        //create audio handler
        audio = new soundEffectsHandler(context);
        audio.setSoundToggle(sound);


        //Set keyboard
        keyboard = activity.findViewById(R.id.keyboards);
        if(keyboardStyle.equals("qwerty")){
            keyboard.setDisplayedChild(0);
        } else{
            keyboard.setDisplayedChild(1);
        }


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

        //Make keyboard clickable again
        setKeyboardClickable(true);

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

        /*//FOR TESTING
        TextView test = activity.findViewById(R.id.test);
        test.setText(words.get(randNum));*/

        return words.get(randNum);
    }


    //when user chooses a letter
    public void chooseALetter(View view){


        //Play key pressed
        audio.playKeyPress();

        //retrieves view Id in string format
        String letterChosen = context.getResources().getResourceEntryName(view.getId());

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

                //show full word in green
                showGreenWord();
                //update score but dont display yet
                score++;
                storage.setRoundNum(score);
                //Make keyboard un clickable
                setKeyboardClickable(false);

                //Play correct guess sound
                audio.playCorrectGuess();

                //show next round dialog //Score view gets updated after this is visible
                setCorrectWordVisibility(true);

            }


        } else {
            //Letter is wrong

            //Play wrong pencil strike sound
            audio.playPencilStrike();

            //Show wrong view
            isWrong(view);
            chancesLeft--;
            chancesLeft(chancesLeft);

            if(chancesLeft == 0){

                //check if new highscore
                //current round not included in the score
                if(score > highScore){
                    highScore = score;
                    setHighScore(highScore);
                    setNewRecordVisibiltiy(true);
                }
                //Show full word in red
                showRedWord();
                //Make keyboard un clickable
                setKeyboardClickable(false);
                //for testing
                score = 0;
                storage.setRoundNum(score);

                //Play game over sound
                audio.playGameOver();

                setGameOverVisibility(true);
                //resetGame();
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
        String wrong = "correct";
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
        dashDashes = new ArrayList<>();

        ViewGroup cl = activity.findViewById(R.id.classicLayout);

        LayoutInflater inflater = activity.getLayoutInflater();
        FlexboxLayout flex =  cl.findViewById(R.id.dashLayout);//inflater.inflate(R.layout.letter_dashes_layout,null);

        //Remove previous views
        flex.removeAllViews();

        for (int i = 0; i < blocks; i++) {

            //Get a Clone of the Linear Layout with the ImageViews
            LinearLayout child = (LinearLayout) inflater.inflate(R.layout.dashes_and_letters, null);
            ImageView let =  child.findViewById(R.id.correct_letter);
            ImageView dash = child.findViewById(R.id.dash);
            //Add image view to dashLetters to keep track of them.
            dashLetters.add(let);
            dashDashes.add(dash);
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
        hangMan.setImageResource(resource.getHangManSrc(chanceNum, gender));

    }






    //Shows full word in Green after users gets it correct
    private void showGreenWord(){

        //Change all dashes to green
        for(int i=0; i<dashDashes.size(); i++){
            dashDashes.get(i).setImageResource(R.drawable.greendash);
        }

        //Show all letters in green
        for(int i=0; i<currWord.length(); i++){

            dashLetters.get(i).setImageResource(resource.getGreenLetterSrc(String.valueOf(currWord.charAt(i))));

        }
    }


    //Shows full word in red after users gets it wrong
    private void showRedWord(){

        //Change dashes to red
        for(int i=0; i<dashDashes.size(); i++){
            dashDashes.get(i).setImageResource(R.drawable.reddash);
        }

        //Show all letters in red
        for(int i=0; i<currWord.length(); i++){

            dashLetters.get(i).setImageResource(resource.getRedLetterSrc(String.valueOf(currWord.charAt(i))));

        }

    }







    //toggles keyboard clickability
    //turns off keyboard touch when user ends a round
    //turns on keyboard touch at the start of the round
    private void setKeyboardClickable(boolean toggle){

        ConstraintLayout k_layout = activity.findViewById(R.id.keyboard_layout);

        for(char keys='a'; keys <= 'z'; keys++){

            int resId = context.getResources().getIdentifier(String.valueOf(keys),"id", context.getPackageName());
            ImageView keyView= activity.findViewById(resId);

            if(toggle){
                //Turn on touch
                keyView.setClickable(true);

                //Make keyboard visible
                k_layout.setVisibility(View.VISIBLE);

            } else {
                //turn off touch
                keyView.setClickable(false);

                //make keyboard invisible
                k_layout.setVisibility(View.INVISIBLE);
            }
        }


    }






    //set all listeners
    private void setListeners(){

        //set Gamebar home button listener
        ImageView homeButtonGameBar = activity.findViewById(R.id.go_home_gamebar);

        homeButtonGameBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //play button press
                audio.playButtonPress();

                //go to home activity
                context.startActivity(new Intent(context, HomeScreenActivity.class));
                activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });


        //set listener for home icon on game over dialog
        ImageView homeButton_gameOver = activity.findViewById(R.id.go_home_wrong);

        homeButton_gameOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //play button press
                audio.playButtonPress();

                score = 0;
                storage.setRoundNum(score);
                storage.setHighScore(highScore);

                context.startActivity(new Intent(context, HomeScreenActivity.class));
                activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                setNewRecordVisibiltiy(false);
                //make dialog invisible
                setGameOverVisibility(false);
            }
        });


        //set listener for play again icon on game over dialog
        ImageView play_again = activity.findViewById(R.id.play_again);

        play_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //play button press
                audio.playButtonPress();

                score = 0;
                setScore(score);
                setHighScore(highScore);
                resetGame();

                setNewRecordVisibiltiy(false);

                //make dialog invisible
                setGameOverVisibility(false);
            }
        });

        //set listener for next round icon on game over dialog
        ImageView next_round = activity.findViewById(R.id.next_round);

        next_round.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //play button press
                audio.playButtonPress();

                setScore(score);
                resetGame();

                //make dialog invisible
                setCorrectWordVisibility(false);
            }
        });

    }






    //sets the score
    private void setScore(int newScore){
        TextView scoreView = activity.findViewById(R.id.current_score);
        scoreView.setText(String.valueOf(newScore));
        storage.setRoundNum(newScore);
    }


    //sets highScore
    private void setHighScore(int newScore){
        TextView scoreView = activity.findViewById(R.id.high_score_num);
        scoreView.setText(String.valueOf(newScore));
        storage.setHighScore(newScore);
    }




    //shows highscore if new one has been made
    private void setNewRecordVisibiltiy(boolean visible){

        LinearLayout newRecord = activity.findViewById(R.id.new_record);

        if(visible){
            TextView record = activity.findViewById(R.id.new_record_text);
            record.setText(String.valueOf(highScore));
            newRecord.setVisibility(View.VISIBLE);
        } else {
            newRecord.setVisibility(View.GONE);
        }
    }



    //Set visibility of win dialog
    private void setCorrectWordVisibility(boolean visible){

        if(visible){

            win_dialog.setVisibility(View.VISIBLE);
            slideUp(win_dialog, 500);

        } else {
            slideDown(win_dialog, 500);
            win_dialog.setVisibility(View.INVISIBLE);
        }
    }


    //Set visibility of gameOver dialog
    private void setGameOverVisibility(boolean visible){

        if(visible){
            gameOver_dialog.setVisibility(View.VISIBLE);
            slideUp(gameOver_dialog, 500);

        } else {
            slideDown(gameOver_dialog, 500);
            gameOver_dialog.setVisibility(View.INVISIBLE);
        }
    }


    // slide the view from below itself to the current position
    public void slideUp(View view, int duration){
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                view.getHeight() *2,  // fromYDelta
                0);                // toYDelta
        animate.setDuration(duration);
        view.startAnimation(animate);
    }

    // slide the view from its current position to below itself
    public void slideDown(View view, int duration){
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                view.getHeight() *2 ); // toYDelta
        animate.setDuration(duration);
        view.startAnimation(animate);
    }




}
