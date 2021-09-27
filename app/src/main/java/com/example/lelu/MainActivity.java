package com.example.lelu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void BtnOnClkFuel(View v)
    {
        Intent intent = new Intent(this, FuelActivity.class);
        startActivity(intent);
    }

    public void BtnOnClkWorkout(View view) {
        Intent switchActivityIntent = new Intent(this, WorkoutActivity.class);
        startActivity(switchActivityIntent);
    }
}