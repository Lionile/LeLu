package com.example.lelu;


public class WorkoutTracker {

    //counter counts how many reps is in workout overall (basically it's a sum of all workout counters)
    private int clicks = 0;

    public void AddClick() { clicks++; }

    //still useless methods
    /*
    public int GetClicks() { return clicks; }

    public int GetOverflow() { return overflowCounter; }

    public void ResetClicks() { clicks=0; }

    public void ResetOverflowCounter() { overflowCounter=0; }
    */

    public boolean Check (int x) {
        if (x > 2147483000) {
            return true;
        }
        return false;
    }
}



class PullUps extends WorkoutTracker {

    //this counter counts only pull ups
    int counter = 0;
    //counter for how many times you exceeded int size ofr pull ups
    int overflow = 0;

    public void Add(int x) {
        counter += x;//adding to pull ups
        super.AddClick();
    }

    public boolean Check() {
        return super.Check(counter);
    }

    public void Reset(){
        counter = 0;
        overflow = 0;
    }

    public int GetCounter(){
        return counter;
    }
}

class PushUps extends WorkoutTracker {

    //this counter counts only push ups
    int counter = 0;
    //counter for how many times you exceeded int size for push ups
    int overflow = 0;

    public void Add(int x){
        counter += x;//adding to push ups
        super.AddClick();
    }

    public boolean Check() {
        return super.Check(counter);
    }

    public void Reset(){
        counter = 0;
        overflow = 0;
    }

    public int GetCounter(){
        return counter;
    }
}

class Squats extends  WorkoutTracker {

    int counter = 0;
    int overflow = 0;

    public  void Add(int x){
        counter += x;
        super.AddClick();
    }

    public boolean Check() {
        return super.Check(counter);
    }

    public void Reset(){
        counter = 0;
        overflow = 0;
    }

    public int GetCounter(){
        return counter;
    }
}

class Dips extends  WorkoutTracker {

    int counter = 0;
    int overflow = 0;

    public  void Add(int x){
        counter += x;
        super.AddClick();
    }

    public boolean Check() {
        return super.Check(counter);
    }

    public void Reset(){
        counter = 0;
        overflow = 0;
    }

    public int GetCounter(){
        return counter;
    }
}









