package com.example.lelu;


public class WorkoutTracker {

/*--------------------------------------------------declaration and initialization--------------------------------------------------*/
    //counter counts how many sets is in workout
    private int clicks = 0;
    //this counter counts only pull ups
    int counter = 0;
    //counter for how many times you exceeded int size for pull ups
    int overflow = 0;
    //string that says what object is this (what workout)
    String workout = "";

/*------------------------------------------------------------methods------------------------------------------------------------*/
    public void AddClick() { clicks++; }
    public boolean Check (int x) { return x > 2147483000; }
    public void Add(int x) {
        counter += x;
        AddClick();
    }

/*--------------------------------------------------getters--------------------------------------------------*/
    public int GetCounter() { return counter; }
    public int GetClicks() { return clicks; }

/*--------------------------------------------------reset/setters--------------------------------------------------*/
    public void ResetClicks() { clicks=0; }
    public void Reset(){
        counter = 0;
        overflow = 0;
    }

}
