package com.example.lelu;

import static com.example.lelu.R.layout.activity_workout;
import static com.example.lelu.R.layout.workout_layer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.invoke.ConstantCallSite;

public class WorkoutActivity extends AppCompatActivity {

/*-------------------------------------------------declaration global variables-------------------------------------------------*/
    Context context;
    MyWorkoutActivity myWorkoutActivity;

    @RequiresApi(api = Build.VERSION_CODES.Q)//some methods doesn't work on lower APIs
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

/*--------------------changing from where basic/main/root layout will be loaded upon switching to this activity--------------------*/
        //setContentView(R.layout.activity_workout); //basic layout -> this is automatically added
        //creating instance to new layout file
        myWorkoutActivity = new MyWorkoutActivity(this);
        //setting new file from where layout will be loaded
        setContentView(myWorkoutActivity);

        //setting what is main context (actually important)
        context=this;

/*--------------------------------------------------setting look of an app--------------------------------------------------*/
        //remove action bar
        getSupportActionBar().hide();
        //transparent status bar
        Window window = WorkoutActivity.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.black));
    }

/*-------------------------methods for 'OnClick', each method call same named method in class for layout-------------------------*/
    public void StartWorkout(View view) { myWorkoutActivity.StartWorkout(this); }
    public void AddWorkout(View view) { myWorkoutActivity.AddWorkout(this); }

    public void OnClickSaveData(View view) { myWorkoutActivity.OnClickSaveData(this); }
    public void OnClickResetAll(View view) { myWorkoutActivity.OnClickResetAll(this); }

    public void pauseChronometer(View view) { myWorkoutActivity.pauseChronometer(); }
    public void resetChronometer(View view) { myWorkoutActivity.resetChronometer(); }
    public void startChronometer(View view) { myWorkoutActivity.startChronometer(); }



/*--------------------------------------------------ignore this--------------------------------------------------*/

    //this method is called when you set it from xml file (android:onClick="onClick")
//    public void onClick (View view) {
//        String tag = view.getTag().toString();
//        int number = Integer.parseInt(tag.split(",")[0]);
//        String choice = (tag.split(",")[1]);
//        this.onClick(number, choice);
//    }

    //if number 1,5,10 -> adds 1,5,10 push ups
    //if number -1 -> adds custom
    //if number 0 -> reset or save data
    //string declares what object is used, can be: pushup, pullup, squat, dip, all
//    public void onClick(int number, String string){
//
//        if(string.equals("pushup")){
//            switch (number) {
//                case 1:
////                    pushUps.Add(1);
////                    pushUpCount.setText(""+pushUps.GetCounter());
////                    pushUps.Check();
//                    break;
//                case 5:
////                    pushUps.Add(5);
////                    pushUpCount.setText(""+pushUps.GetCounter());
////                    pushUps.Check();
//                    break;
//                case 10:
////                    pushUps.Add(10);
////                    pushUpCount.setText(""+pushUps.GetCounter());
////                    pushUps.Check();
//                    break;
//                case -1:
////                    helper=numberOfCustomPushUps.getText().toString();
////                    if (helper.matches("")) {
////                        helper = "0";
////                    }
////                    pushUps.Add(Integer.parseInt(helper));
////                    pushUps.Check();
////                    pushUpCount.setText(""+pushUps.GetCounter());
//                    break;
//                case 0:
//                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                    builder.setCancelable(true);
//                    builder.setTitle("Confirmation dialog");
//                    builder.setMessage("Data isn't saved.\nDo you want do delete your workout?");
//                    AlertDialog.Builder builder1 = builder.setPositiveButton(R.string.Confirm, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
////                            pushUps.Reset();
////                            pushUpCount.setText("" + pushUps.GetCounter());
//                        }
//                    });
//                    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                        }
//                    });
//                    AlertDialog dialog = builder.create();
//                    dialog.show();
//                    break;
//                default:
//                    break;
//            }
//        }
//
//        else if(string.equals("pullup")){
//            switch (number){
//                case 1:
////                    pullUps.Add(1);
////                    pullUpCount.setText(""+pullUps.GetCounter());
////                    pullUps.Check();
//                    break;
//                case 5:
////                    pullUps.Add(5);
////                    pullUpCount.setText(""+pullUps.GetCounter());
////                    pullUps.Check();
//                    break;
//                case 10:
////                    pullUps.Add(10);
////                    pullUpCount.setText(""+pullUps.GetCounter());
////                    pullUps.Check();
//                    break;
//                case -1:
//                    helper = numberOfCustomPullUps.getText().toString();
//                    if (helper.matches("")) {
//                        helper = "0";
//                    }
////                    pullUps.Add(Integer.parseInt(helper));
////                    pullUpCount.setText(""+pullUps.GetCounter());
////                    pullUps.Check();
//                    break;
//                case 0:
//                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                    builder.setCancelable(true);
//                    builder.setTitle("Confirmation dialog");
//                    builder.setMessage("Data isn't saved.\nDo you want do delete your workout?");
//                    AlertDialog.Builder builder1 = builder.setPositiveButton(R.string.Confirm, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
////                            pullUps.Reset();
////                            pullUpCount.setText(""+pullUps.GetCounter());
//                        }
//                    });
//                    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                        }
//                    });
//                    AlertDialog dialog = builder.create();
//                    dialog.show();
//                    break;
//                default:
//                    break;
//            }
//        }
//        else if(string.equals("all")){
//
//        }
//
//        else if(string.equals("SaveData")){
//
//        }
//    }
//
    //methods for chronometer (https://www.youtube.com/watch?v=LPjhP9D3pm8)

//
//
//    //if 1 -> expanding, if 0 -> retracting
//    //string represents which sector
//    public void Expand(View view){
//        String tag = view.getTag().toString();
//        boolean flag = Boolean.parseBoolean(tag.split(",")[0]);
//        String choice = (tag.split(",")[1]);
//        this.Expand(flag, choice);
//    }
//    public void Expand(boolean flag, String string){
//        if(flag){
//            switch(string){
//                case "pushup":
//                    pushUpsLayout.setVisibility(View.VISIBLE);
//                    break;
//                case "pullup":
//                    pullUpsLayout.setVisibility(View.VISIBLE);
//                    break;
//                case "squat":
//                    squatsLayout.setVisibility(View.VISIBLE);
//                    break;
//                case "dip":
//                    dipsLayout.setVisibility(View.VISIBLE);
//                    break;
//                default:
//                    break;
//            }
//        }
//        else{
//            switch(string){
//                case "pushup":
//                    pushUpsLayout.setVisibility(View.GONE);
//                    break;
//                case "pullup":
//                    pullUpsLayout.setVisibility(View.GONE);
//                    break;
//                case "squat":
//                    squatsLayout.setVisibility(View.GONE);
//                    break;
//                case "dip":
//                    dipsLayout.setVisibility(View.GONE);
//                    break;
//                default:
//                    break;
//            }
//        }
//    }
//
//    //methods for adding end starting workout

}

/* changes
added xml files: workout_sector,
opening_screen, main_menu, border_selector_black, workout_menu
added methods: AddWorkout,StartWorkout
code rewrite
*/
