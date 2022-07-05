package com.example.lelu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

public class WorkoutActivity extends AppCompatActivity {

    //textview
    TextView pushUpCount;
    TextView pullUpCount;
    TextView squatsCount;
    TextView dipsCount;

    //edittext
    EditText numberOfCustomPushUps;
    EditText numberOfCustomPullUps;
    EditText numberOfCustomSquats;
    EditText numberOfCustomDips;

    //layouts
    ConstraintLayout pushUpsLayout;
    ConstraintLayout pullUpsLayout;
    ConstraintLayout squatsLayout;
    ConstraintLayout dipsLayout;

    Context context;

    //chronometer
    Chronometer chronometer;
    private long pauseOffset;
    private boolean running;

    //flags
    boolean pullUpFlag=false,pushUpFlag=false;

    //helping variables
    String helper;


    //objects of classes
    PullUps pullUps= new PullUps();
    PushUps pushUps= new PushUps();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        //push ups declaration
        pushUpsLayout= findViewById(R.id.PushUpsLayout);
        pushUpCount= findViewById(R.id.txtPushUpsCount);
        numberOfCustomPushUps= findViewById(R.id.edtxtPushUpsCustomNumber);

        //pull ups declaration
        pullUpsLayout= findViewById(R.id.PullUpsLayout);
        pullUpCount= findViewById(R.id.txtPullUpsCount);
        numberOfCustomPullUps= findViewById(R.id.edtxtPullUpsCustomNumber);

        //squats declaration
        squatsLayout= findViewById(R.id.SquatsLayout);
        squatsCount= findViewById(R.id.txtSquatCount);
        numberOfCustomSquats= findViewById(R.id.edtxtSquatCustomNumber);

        //dips declaration
        dipsLayout= findViewById(R.id.DipsLayout);
        dipsCount= findViewById(R.id.txtDipsCount);
        numberOfCustomDips= findViewById(R.id.edtxtDipCustomNumber);

        //stopwatch declaration
        chronometer=(Chronometer)findViewById(R.id.chronometer);
        chronometer.setFormat("%s");


        context=this;

        //remove action bar
        getSupportActionBar().hide();
        //transparent status bar
        Window window = WorkoutActivity.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.black));

    }

    //this method is called when you set it from xml file (android:onClick="onClick")
    public void onClick (View view) {
        String tag = view.getTag().toString();
        int number = Integer.parseInt(tag.split(",")[0]);
        String choice = (tag.split(",")[1]);
        this.onClick(number, choice);
    }

    //if number 1,5,10 -> adds 1,5,10 push ups
    //if number -1 -> adds custom
    //if number 0 -> reset or save data
    //string declares what object is used, can be: pushup, pullup, squat, dip, all
    public void onClick(int number, String string){

        if(string.equals("pushup")){
            switch (number) {
                case 1:
                    pushUps.Add(1);
                    pushUpCount.setText(""+pushUps.GetCounter());
                    pushUps.Check();
                    break;
                case 5:
                    pushUps.Add(5);
                    pushUpCount.setText(""+pushUps.GetCounter());
                    pushUps.Check();
                    break;
                case 10:
                    pushUps.Add(10);
                    pushUpCount.setText(""+pushUps.GetCounter());
                    pushUps.Check();
                    break;
                case -1:
                    helper=numberOfCustomPushUps.getText().toString();
                    if (helper.matches("")) {
                        helper = "0";
                    }
                    pushUps.Add(Integer.parseInt(helper));
                    pushUps.Check();
                    pushUpCount.setText(""+pushUps.GetCounter());
                    break;
                case 0:
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setCancelable(true);
                    builder.setTitle("Confirmation dialog");
                    builder.setMessage("Data isn't saved.\nDo you want do delete your workout?");
                    AlertDialog.Builder builder1 = builder.setPositiveButton(R.string.Confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            pushUps.Reset();
                            pushUpCount.setText("" + pushUps.GetCounter());
                        }
                    });
                    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    break;
                default:
                    break;
            }
        }

        else if(string.equals("pullup")){
            switch (number){
                case 1:
                    pullUps.Add(1);
                    pullUpCount.setText(""+pullUps.GetCounter());
                    pullUps.Check();
                    break;
                case 5:
                    pullUps.Add(5);
                    pullUpCount.setText(""+pullUps.GetCounter());
                    pullUps.Check();
                    break;
                case 10:
                    pullUps.Add(10);
                    pullUpCount.setText(""+pullUps.GetCounter());
                    pullUps.Check();
                    break;
                case -1:
                    helper = numberOfCustomPullUps.getText().toString();
                    if (helper.matches("")) {
                        helper = "0";
                    }
                    pullUps.Add(Integer.parseInt(helper));
                    pullUpCount.setText(""+pullUps.GetCounter());
                    pullUps.Check();
                    break;
                case 0:
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setCancelable(true);
                    builder.setTitle("Confirmation dialog");
                    builder.setMessage("Data isn't saved.\nDo you want do delete your workout?");
                    AlertDialog.Builder builder1 = builder.setPositiveButton(R.string.Confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            pullUps.Reset();
                            pullUpCount.setText(""+pullUps.GetCounter());
                        }
                    });
                    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    break;
                default:
                    break;
            }
        }

        else if(string.equals("squat")){
            switch (number){
                case 1:
                    break;
                case 5:
                    break;
                case 10:
                    break;
                case -1:
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        }

        else if(string.equals("dip")){
            switch (number){
                case 1:
                    break;
                case 5:
                    break;
                case 10:
                    break;
                case -1:
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        }

        else if(string.equals("all")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Confirmation dialog");
            builder.setMessage("Data isn't saved.\nDo you want do delete your workout?");
            AlertDialog.Builder builder1 = builder.setPositiveButton(R.string.Confirm, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    pushUps.Reset();
                    pullUps.Reset();
                    pushUpCount.setText(""+pushUps.GetCounter());
                    pullUpCount.setText(""+pullUps.GetCounter());
                }
            });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        else if(string.equals("SaveData")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Information");
            builder.setMessage("Unfortunately this feature doesn't work\nWe are hoping that it will be fixed soon");
            AlertDialog.Builder builder1 = builder.setPositiveButton(R.string.Confirm, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
//            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                }
//            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    //methods for chronometer (https://www.youtube.com/watch?v=LPjhP9D3pm8)
    public void startChronometer(View view){
        if(!running){
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }

    public void pauseChronometer(View view){
        if(running){
            chronometer.stop();
            pauseOffset=SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

    public void resetChronometer(View view){
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }

    //if 1 -> expanding, if 0 -> retracting
    //string represents which sector
    public void Expand(View view){
        String tag = view.getTag().toString();
        boolean flag = Boolean.parseBoolean(tag.split(",")[0]);
        String choice = (tag.split(",")[1]);
        this.Expand(flag, choice);
    }

    public void Expand(boolean flag, String string){
        if(flag){
            switch(string){
                case "pushup":
                    pushUpsLayout.setVisibility(View.VISIBLE);
                    break;
                case "pullup":
                    pullUpsLayout.setVisibility(View.VISIBLE);
                    break;
                case "squat":
                    squatsLayout.setVisibility(View.VISIBLE);
                    break;
                case "dip":
                    dipsLayout.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        }
        else{
            switch(string){
                case "pushup":
                    pushUpsLayout.setVisibility(View.GONE);
                    break;
                case "pullup":
                    pullUpsLayout.setVisibility(View.GONE);
                    break;
                case "squat":
                    squatsLayout.setVisibility(View.GONE);
                    break;
                case "dip":
                    dipsLayout.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }
    }
}


/* changes

*/
