package com.example.lelu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WorkoutActivity extends AppCompatActivity {

    //push up buttons
    Button addOnePushUp;
    Button addFivePushUp;
    Button addTenPushUp;
    Button extendActivityPushUps;
    Button shrinkActivityPushUps;
    //push up textview
    TextView pushUpCount, txtPushUps;
    //push up edittext
    EditText numberOfCustomPushUps;
    //layouts
    ConstraintLayout pushUpsLayout;

    Context context;

    //helping variables
    String helper;


    //objects of classes
    PullUps pullUps= new PullUps(0);
    PushUps pushUps= new PushUps(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        addOnePushUp= findViewById(R.id.btnPushUpAddOne);
        addFivePushUp= findViewById(R.id.btnPushUpAddFive);
        addTenPushUp= findViewById(R.id.btnPushUpAddTen);
        shrinkActivityPushUps= findViewById(R.id.CloseSection);
        extendActivityPushUps = findViewById(R.id.OpenSection);
        pushUpsLayout= findViewById(R.id.PushUpsLayout);

        pushUpCount= findViewById(R.id.txtPushUpsCount);
        txtPushUps= findViewById(R.id.txtPushUps);
        numberOfCustomPushUps= findViewById(R.id.edtxtPushUpsCustomNumber);

        context=this;

        //remove action bar
        getSupportActionBar().hide();
        //transparent status bar
        Window window = WorkoutActivity.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.black));

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
