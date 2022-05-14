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

    //pushUps methods
    public void BtnAddOnePushUp(View view) {
        pushUps.Add(1);
        pushUpCount.setText(""+pushUps.GetCounter());
        pushUps.Check();
    }
    public void BtnAddFivePushUps(View view) {
        pushUps.Add(5);
        pushUpCount.setText(""+pushUps.GetCounter());
        pushUps.Check();
    }
    public void BtnAddTenPushUps(View view) {
        pushUps.Add(10);
        pushUpCount.setText(""+pushUps.GetCounter());
        pushUps.Check();
    }
    public void BtnAddCustomPushUps(View view) {
        helper=numberOfCustomPushUps.getText().toString();
        if (helper.matches("")) {
            helper = "0";
        }
        pushUps.Add(Integer.parseInt(helper));
        pushUps.Check();
        pushUpCount.setText(""+pushUps.GetCounter());
    }
    public void BtnResetPushUpCount(View view) {
        pushUps.Reset();
        pushUpCount.setText(""+pushUps.GetCounter());
    }


    //pullUps methods
    public void BtnAddOnePullUps(View view){
        pullUps.Add(1);
        pullUpCount.setText(""+pullUps.GetCounter());
        pullUps.Check();
    }
    public void BtnAddFivePullUps(View view){
        pullUps.Add(5);
        pullUpCount.setText(""+pullUps.GetCounter());
        pullUps.Check();
    }
    public void BtnAddTenPullUps(View view){
        pullUps.Add(10);
        pullUpCount.setText(""+pullUps.GetCounter());
        pullUps.Check();
    }
    public void BtnAddCustomPullUps(View view){
        helper = numberOfCustomPullUps.getText().toString();
        if (helper.matches("")) {
            helper = "0";
        }
        pullUps.Add(Integer.parseInt(helper));
        pullUpCount.setText(""+pullUps.GetCounter());
        pullUps.Check();
    }
    public void BtnResetPullUpCount(View view){
        pullUps.Reset();
        pullUpCount.setText(""+pullUps.GetCounter());
    }
}


/*
changed workout tracker class
changed workout activity
pull ups now work
*/
