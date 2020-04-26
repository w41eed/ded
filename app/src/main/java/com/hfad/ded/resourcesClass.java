package com.hfad.ded;

import java.util.Hashtable;


public class resourcesClass {


    private Hashtable<String, Integer> letterImages;
    private Hashtable<String, Integer> RedLetterImages;
    private Hashtable<String, Integer> GreenLetterImages;
    private Hashtable<Integer, Integer> hangManImages, hangWomanImages;

    private final String MALE = "m";
    private final String FEMALE = "f";

    public resourcesClass(){
        letterImages = new Hashtable<>();
        RedLetterImages = new Hashtable<>();
        GreenLetterImages = new Hashtable<>();
        hangManImages = new Hashtable<>();
        hangWomanImages = new Hashtable<>();
        initializeHash();
    }


   //Initializes the hash tables
    private void initializeHash(){


        //Initialize RedLetterImages hash
        RedLetterImages.put("a", R.drawable.reda);
        RedLetterImages.put("b", R.drawable.redb);
        RedLetterImages.put("c", R.drawable.redc);
        RedLetterImages.put("d", R.drawable.redd);
        RedLetterImages.put("e", R.drawable.rede);
        RedLetterImages.put("f", R.drawable.redf);
        RedLetterImages.put("g", R.drawable.redg);
        RedLetterImages.put("h", R.drawable.redh);
        RedLetterImages.put("i", R.drawable.redi);
        RedLetterImages.put("j", R.drawable.redj);
        RedLetterImages.put("k", R.drawable.redk);
        RedLetterImages.put("l", R.drawable.redl);
        RedLetterImages.put("m", R.drawable.redm);
        RedLetterImages.put("n", R.drawable.redn);
        RedLetterImages.put("o", R.drawable.redo);
        RedLetterImages.put("p", R.drawable.redp);
        RedLetterImages.put("q", R.drawable.redq);
        RedLetterImages.put("r", R.drawable.redr);
        RedLetterImages.put("s", R.drawable.reds);
        RedLetterImages.put("t", R.drawable.redt);
        RedLetterImages.put("u", R.drawable.redu);
        RedLetterImages.put("v", R.drawable.redv);
        RedLetterImages.put("w", R.drawable.redw);
        RedLetterImages.put("x", R.drawable.redx);
        RedLetterImages.put("y", R.drawable.redy);
        RedLetterImages.put("z", R.drawable.redz);



        //Initialize GreenLetterImages hash
        GreenLetterImages.put("a", R.drawable.greena);
        GreenLetterImages.put("b", R.drawable.greenb);
        GreenLetterImages.put("c", R.drawable.greenc);
        GreenLetterImages.put("d", R.drawable.greend);
        GreenLetterImages.put("e", R.drawable.greene);
        GreenLetterImages.put("f", R.drawable.greenf);
        GreenLetterImages.put("g", R.drawable.greeng);
        GreenLetterImages.put("h", R.drawable.greenh);
        GreenLetterImages.put("j", R.drawable.greenj);
        GreenLetterImages.put("k", R.drawable.greenk);
        GreenLetterImages.put("i", R.drawable.greeni);
        GreenLetterImages.put("l", R.drawable.greenl);
        GreenLetterImages.put("m", R.drawable.greenm);
        GreenLetterImages.put("n", R.drawable.greenn);
        GreenLetterImages.put("o", R.drawable.greeno);
        GreenLetterImages.put("p", R.drawable.greenp);
        GreenLetterImages.put("q", R.drawable.greenq);
        GreenLetterImages.put("r", R.drawable.greenr);
        GreenLetterImages.put("s", R.drawable.greens);
        GreenLetterImages.put("t", R.drawable.greent);
        GreenLetterImages.put("u", R.drawable.greenu);
        GreenLetterImages.put("v", R.drawable.greenv);
        GreenLetterImages.put("w", R.drawable.greenw);
        GreenLetterImages.put("x", R.drawable.greenx);
        GreenLetterImages.put("y", R.drawable.greeny);
        GreenLetterImages.put("z", R.drawable.greenz);



        //Initialize letterImages hash
        letterImages.put("a", R.drawable.a);
        letterImages.put("b", R.drawable.b);
        letterImages.put("c", R.drawable.c);
        letterImages.put("d", R.drawable.d);
        letterImages.put("e", R.drawable.e);
        letterImages.put("f", R.drawable.f);
        letterImages.put("g", R.drawable.g);
        letterImages.put("h", R.drawable.h);
        letterImages.put("i", R.drawable.i);
        letterImages.put("j", R.drawable.j);
        letterImages.put("k", R.drawable.k);
        letterImages.put("l", R.drawable.l);
        letterImages.put("m", R.drawable.m);
        letterImages.put("n", R.drawable.n);
        letterImages.put("o", R.drawable.o);
        letterImages.put("p", R.drawable.p);
        letterImages.put("q", R.drawable.q);
        letterImages.put("r", R.drawable.r);
        letterImages.put("s", R.drawable.s);
        letterImages.put("t", R.drawable.t);
        letterImages.put("u", R.drawable.u);
        letterImages.put("v", R.drawable.v);
        letterImages.put("w", R.drawable.w);
        letterImages.put("x", R.drawable.x);
        letterImages.put("y", R.drawable.y);
        letterImages.put("z", R.drawable.z);



        //Initialize Hangman Hash
        //First integer is Chances Left
        //Seconf Integer is Strike num resource file
        hangManImages.put(6, R.drawable.stage);
        hangManImages.put(5, R.drawable.mstrike1);
        hangManImages.put(4, R.drawable.mstrike2);
        hangManImages.put(3, R.drawable.mstrike3);
        hangManImages.put(2, R.drawable.mstrike4);
        hangManImages.put(1, R.drawable.mstrike5);
        hangManImages.put(0, R.drawable.mstrike6);



        //Initialize HangWoman Hash
        //First integer is Chances Left
        //Seconf Integer is Strike num resource file
        hangWomanImages.put(6, R.drawable.stage);
        hangWomanImages.put(5, R.drawable.fstrike1);
        hangWomanImages.put(4, R.drawable.fstrike2);
        hangWomanImages.put(3, R.drawable.fstrike3);
        hangWomanImages.put(2, R.drawable.fstrike4);
        hangWomanImages.put(1, R.drawable.fstrike5);
        hangWomanImages.put(0, R.drawable.fstrike6);




    }


    //Get the corresponding resource file to the letter
    public int getLetterSrc(String letter){

        //If return is -1 then error occurred
        int temp_int = -1;

        //Get Value
        Integer temp_object  = letterImages.get(letter);

        //To avoid null object exception
        if(temp_object != null) {
            temp_int = temp_object;
        }

        //Returns Resource ID if lookup successful, else returns -1
        return temp_int ;
    }




    //Get the corresponding resource file to the letter
    public int getRedLetterSrc(String letter){

        //If return is -1 then error occurred
        int temp_int = -1;

        //Get Value
        Integer temp_object  = RedLetterImages.get(letter);

        //To avoid null object exception
        if(temp_object != null) {
            temp_int = temp_object;
        }

        //Returns Resource ID if lookup successful, else returns -1
        return temp_int ;
    }



    //Get the corresponding resource file to the letter
    public int getGreenLetterSrc(String letter){

        //If return is -1 then error occurred
        int temp_int = -1;

        //Get Value
        Integer temp_object  = GreenLetterImages.get(letter);

        //To avoid null object exception
        if(temp_object != null) {
            temp_int = temp_object;
        }

        //Returns Resource ID if lookup successful, else returns -1
        return temp_int ;
    }



    //Get the corresponding resource file to the strike num and gender
    public int getHangManSrc(int chancesLeft, String gender){

        //If return is -1 then error occurred
        int temp_int = -1;
        Integer temp_object = null;

        switch (gender) {
            case MALE:
                //Get Value
                temp_object = hangManImages.get(chancesLeft);
                break;
            case FEMALE:
                //Get Value
                temp_object = hangWomanImages.get(chancesLeft);
                break;
        }


        //To avoid null object exception
        if(temp_object != null) {
            temp_int = temp_object;
        }

        //Returns Resource ID if lookup successful, else returns -1
        return temp_int ;
    }


}
