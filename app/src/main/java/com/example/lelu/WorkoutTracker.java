package com.example.lelu;


public class WorkoutTracker {

    private int counter;
    private int clicks=0;
    private int overflowCounter=0;

    public WorkoutTracker(int counter) { this.counter = 0; }

    public int GetCounter() { return counter; }

    public int GetClicks() { return clicks; }

    public int GetOverflow() { return overflowCounter; }

    public void SetCounter(int number) { counter=number; }

    public void ResetClicks() { clicks=0; }

    public void ResetOverflowCounter() { overflowCounter=0; }

    public void AddOne(){
        counter++;
        clicks++;
    }

    public void AddFive(){
        counter+=5;
        clicks++;
    }

    public void AddTen(){
        counter+=10;
        clicks++;
    }

    public void AddCustom(int number){
        counter += Math.min(number, 100);
        /*
        if(number>100)counter+=100;
        else counter+=number;
        */
        clicks++;
    }

    public void Check() {
        if(counter>2147483000){
            overflowCounter++;
            counter-=2147483000;
        }
    }

}

class PullUps extends WorkoutTracker {

    int[] pullUp;

    public PullUps(int counter) { super(counter); }

    public void AddWideGrip(int number){ pullUp[0]+=number; }
    public void AddNarrowGrip(int number){ pullUp[1]+=number; }
    public void AddChinUp(int number){ pullUp[2]+=number; }
    public void AddWideGripChinUp(int number){ pullUp[3]+=number; }
    public void AddNarrowGripChinUp(int number){ pullUp[4]+=number; }
    public void AddClap(int number){ pullUp[8]+=number; }
    public void AddArcher(int number){ pullUp[5]+=number; }
    public void AddOneHand(int number){ pullUp[6]+=number; }
    public void AddMuscleUp(int number){ pullUp[7]+=number; }
    public void ResetPullUps() {
        int i;
        for(i = 0; i < 8; i++)
            pullUp[i]=0;
    }

}

class PushUps extends WorkoutTracker {

    int[] pushUp;

    public PushUps(int counter) { super(counter); }

    public PushUps(int counter, int[] pushUp) {
        super(counter);
        this.pushUp = pushUp;
    }

    public void AddWide(int number) { pushUp[0]+=number; }
    public void AddOneHand(int number) { pushUp[1]+=number; }
    public void AddHandStand(int number) { pushUp[2]+=number; }
    public void AddIncline(int number) { pushUp[3]+=number; }
    public void AddDecline(int number) { pushUp[4]+=number; }
    public void AddPlank(int number) { pushUp[5]+=number; }
    public void AddDiamond(int number) { pushUp[6]+=number; }
    public void AddHindu(int number) { pushUp[7]+=number; }
    public void AddClap(int number) { pushUp[8]+=number; }
    public void ResetPushUps() {
        int i;
        for(i = 0; i < 9; i++)
            pushUp[i]=0;
    }

}

class Squats extends  WorkoutTracker {

    public Squats(int counter) { super(counter); }
}









