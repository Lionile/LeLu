package com.example.lelu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class FuelActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    final float mileToKm = 1.609344f;
    final float UKGallonToLiter = 4.54609188f;
    final float USAGallonToLiter = 3.78541178f;
    final float convertMpgUK = mileToKm/UKGallonToLiter; // result - km/liter
    final float convertMpgUSA = mileToKm/USAGallonToLiter;  // result - km/liter
    // To get liter per 100 km - 100/(mpgUK * convertMpgUK)
    // or 100/(mpgUSA * convertMpgUSA)

    Float fedttxtFuelPrice = 0f;
    Float fedttxtFuelConsumption = 0f;
    Float fedttxtDistance = 0f;

    EditText edttxtFuelPrice;
    EditText edttxtFuelConsumption;
    EditText edttxtDistance;

    enum FuelConsumptionType {
        lp100km,
        mpgUK,
        mpgUSA
    }

    FuelConsumptionType fuelConsumptionType = FuelConsumptionType.lp100km;


    TextView txtPriceValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel);

        //remove action bar
        getSupportActionBar().hide();
        //change color of status bar
        Window window = FuelActivity.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.black));
        //tried :(
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        edttxtFuelPrice = (findViewById(R.id.edttxtFuelPrice));
        edttxtFuelConsumption = (findViewById(R.id.edttxtFuelConsumption));
        edttxtDistance = (findViewById(R.id.edttxtDistance));

        txtPriceValue = findViewById(R.id.txtPriceValue);

        Spinner spinner = findViewById(R.id.spnFuelConstumption);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spnFuelConsumption, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    public void BtnOnClkCalculate(View v)
    {

        if(!(edttxtFuelPrice.getText().toString().trim().length() == 0
                || edttxtFuelConsumption.getText().toString().trim().length() == 0
                || edttxtDistance.getText().toString().trim().length() == 0))
        {
            fedttxtFuelPrice =       Float.parseFloat (edttxtFuelPrice.getText().toString()) ;
            fedttxtFuelConsumption = Float.parseFloat (edttxtFuelConsumption.getText().toString());
            fedttxtDistance =        Float.parseFloat (edttxtDistance.getText().toString());

            float result;
            if(fuelConsumptionType == FuelConsumptionType.lp100km){
                result = (fedttxtDistance / 100) * fedttxtFuelConsumption * fedttxtFuelPrice;
            }
            else if(fuelConsumptionType == FuelConsumptionType.mpgUK){
                result = (fedttxtDistance / 100) * 100/(fedttxtFuelConsumption * convertMpgUK) * fedttxtFuelPrice;
            }
            else /* if(fuelConsumptionType == FuelConsumptionType.mpgUSA*/{
                result = (fedttxtDistance / 100) * 100/(fedttxtFuelConsumption * convertMpgUSA) * fedttxtFuelPrice;
            }

            txtPriceValue.setText(Float.toString(result));
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        if(i == 0) fuelConsumptionType = FuelConsumptionType.lp100km;
        else if(i == 1) fuelConsumptionType = FuelConsumptionType.mpgUK;
        else fuelConsumptionType = FuelConsumptionType.mpgUSA; // if(text == getResources().getStringArray(R.array.spnFuelConsumption)[2])

        Log.d("adapterview child count", String.valueOf(adapterView.getCount()));
        if(adapterView.getChildAt(0) != null){
            Log.d("adapterview child", "adapterview child is not null");
            // Causes program to crash when changing dark mode setting
            //---------------------------------------------------------------
            ((TextView) adapterView.getChildAt(0)).setTextColor(Color.parseColor("#8a000000"));
            ((TextView) adapterView.getChildAt(0)).setTextSize(20);
            ((TextView) adapterView.getChildAt(0)).setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            //---------------------------------------------------------------
        }
        else{
            Log.d("adapterview child", "adapterview child is null");
        }

        if(txtPriceValue.getText() != "")
            BtnOnClkCalculate(null);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}