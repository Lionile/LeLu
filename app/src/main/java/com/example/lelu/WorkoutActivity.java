package com.example.lelu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WorkoutActivity extends AppCompatActivity {

    //textview
    TextView pushUpCount;
    TextView pullUpCount;

    //edittext
    EditText numberOfCustomPushUps;
    EditText numberOfCustomPullUps;

    //layouts
    ConstraintLayout pushUpsLayout;
    ConstraintLayout pullUpsLayout;

    Context context;

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


        context=this;

        //remove action bar
        getSupportActionBar().hide();
        //transparent status bar
        Window window = WorkoutActivity.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.black));

    }

    public void onClick (View view) {
        String tag = view.getTag().toString();
        int number = Integer.parseInt(tag.split(",")[0]);
        String choice = (tag.split(",")[1]);
        this.onClick(number, choice);
    }

    //if number 1,5,10 -> adds 1,5,10 push ups
    //if number -1 -> adds custom
    //if number 0 -> reset
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
    }

    //method for saving data from workout to DB
    public void SaveData(View view){

    }
}


/*
replaced add methods with onClick method
added some image buttons, chronometer

*/
