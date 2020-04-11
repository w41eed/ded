package com.hfad.ded;

import java.util.Hashtable;


public class resourcesClass {


    private Hashtable<String, Integer> letterImages;


    public resourcesClass(){
        letterImages = new Hashtable<>();
        initializeHash();
    }


   //Initializes the hash tables
    private void initializeHash(){

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



}
