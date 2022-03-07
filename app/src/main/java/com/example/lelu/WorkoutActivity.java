package com.example.lelu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WorkoutActivity extends AppCompatActivity {

    //push up buttons
    Button addOnePushUp, addFivePushUp, addTenPushUp, addCustomPushUp, extendActivityPushUps, shrinkActivityPushUps;
    //push up textview
    TextView pushUpCount, txtPushUps;
    //push up edittext
    EditText numberOfCustomPushUps;
    //layouts
    ConstraintLayout pushUpsLayout;



    //helping variables
    String helper;
    int[] flag;
    int i;
    private static final int workoutType = 3;//types of workouts(push ups, pull ups, squats, etc.)


    //objects of classes
    PullUps pullUps;
    PushUps pushUps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        addOnePushUp=(Button)findViewById(R.id.btnPushUpAddOne);
        addFivePushUp=(Button)findViewById(R.id.btnPushUpAddFive);
        addTenPushUp=(Button)findViewById(R.id.btnPushUpAddTen);
        addCustomPushUp=(Button)findViewById(R.id.btnPushUpAdd);
        extendActivityPushUps=(Button)findViewById(R.id.OpenSection);
        shrinkActivityPushUps=(Button)findViewById(R.id.CloseSection);

        pushUpsLayout=(ConstraintLayout)findViewById(R.id.PushUpsLayout);

        pushUpCount=(TextView)findViewById(R.id.txtPushUpsCount);
        txtPushUps=(TextView)findViewById(R.id.txtPushUps);
        numberOfCustomPushUps=(EditText)findViewById(R.id.edtxtPushUpsCustomNumber);

        //set ups
        for(i=0;i<workoutType;i++) flag[i]=0;


        //remove action bar
        getSupportActionBar().hide();
        //transparent status bar
        Window window = WorkoutActivity.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.black));

    }



    //pushUps methods
    public void BtnExtendActivity(View view){
        flag[0]++;
        if(flag[0]==1){pushUpsLayout.setVisibility(View.VISIBLE);shrinkActivityPushUps.setVisibility(View.VISIBLE);}
        if(flag[0]==2){extendActivityPushUps.setVisibility(View.INVISIBLE);}
    }
    public void BtnShrinkActivity(View view){

    }
    public void BtnAddOnePushUp(View view) {
        pushUps.AddOne();
        pushUpCount.setText(""+pushUps.GetCounter());
        pushUps.Check();
    }
    public void BtnAddFivePushUps(View view) {
        pushUps.AddFive();
        pushUpCount.setText(""+pushUps.GetCounter());
        pushUps.Check();
    }
    public void BtnAddTenPushUps(View view) {

        pushUps.AddTen();
        pushUpCount.setText(""+pushUps.GetCounter());
        pushUps.Check();
    }
    public void BtnAddCustomPushUps(View view) {
        helper=numberOfCustomPushUps.getText().toString();
        if(helper.matches("")){helper="0";}
        pushUps.AddCustom(Integer.parseInt(helper));
        pushUps.Check();
        pushUpCount.setText(""+pushUps.GetCounter());
    }
    public void BtnResetPushUpCount(View view) {
        pushUps.SetCounter(0);
        pushUpCount.setText(""+pushUps.GetCounter());
    }


    //pullUps methods
    public void BtnAddOnePullUps(View view){
        pullUps.AddOne();
        pullUps.Check();
    }
    public void BtnAddFivePullUps(View view){
        pullUps.AddFive();
        pullUps.Check();
    }
    public void BtnAddTenPullUps(View view){
        pullUps.AddTen();
        pullUps.Check();
    }
    public void BtnAddCustomPullUps(View view){
        helper=numberOfCustomPushUps.getText().toString();
        if(helper.matches("")){helper="0";}
        pullUps.AddCustom(Integer.parseInt(helper));
        pullUps.Check();
    }
    public void BtnResetPullUpCount(View view){
        pullUps.SetCounter(0);
    }
}


/*

added:
-WorkoutTracker class + all methods in it
-startup images
-app icon
-DatabaseHelper class + methods(not all)
-pull ups section
-advance push ups section
-overall improvement in push ups section
-disabled overflow in number (can't go over size of int)
-styles: LeLuBackgroundImages, WrapContent, MatchParent

*/
