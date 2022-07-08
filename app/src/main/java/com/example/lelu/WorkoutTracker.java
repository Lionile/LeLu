package com.example.lelu;


public class WorkoutTracker {

    //counter counts how many sets is in workout
        private int clicks = 0;
    //this counter counts only pull ups
        int counter = 0;
    //counter for how many times you exceeded int size for pull ups
        int overflow = 0;
    //string that says what object is this (what workout)
        String workout = "";

    public void AddClick() {
        clicks++;
    }

    public boolean Check (int x) {
        if (x > 2147483000) {
            return true;
        }
        return false;
    }

    public void Add(int x) {
        counter += x;
        AddClick();
    }

    public boolean Check() {
        return Check(counter);
    }

    //getters
    public int GetCounter() { return counter; }
    public int GetClicks() { return clicks; }

    //resets
    public void ResetClicks() { clicks=0; }
    public void Reset(){
        counter = 0;
        overflow = 0;
    }

}
