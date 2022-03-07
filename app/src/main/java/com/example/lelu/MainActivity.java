package com.example.lelu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ConstraintLayout mainLayout =(ConstraintLayout)findViewById(R.id.mainlayout);
        ConstraintLayout firstScreen = (ConstraintLayout)findViewById(R.id.firstscreen);
        ConstraintLayout menu = (ConstraintLayout)findViewById(R.id.menu);

        //startup
        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.setVisibility(View.VISIBLE);
                firstScreen.setVisibility(View.GONE);
            }
        });


        getSupportActionBar().hide();//remove action bar

        //change color of status bar
        Window window = MainActivity.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.black));


        //tried :(
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }


    public void BtnOnClkFuel(View v){
        Intent intent = new Intent(this, FuelActivity.class);
        startActivity(intent);
    }

    public void BtnOnClkWorkout(View view) {
        Intent switchActivityIntent = new Intent(this, WorkoutActivity.class);
        startActivity(switchActivityIntent);
    }

}