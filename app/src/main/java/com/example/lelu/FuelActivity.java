package com.example.lelu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FuelActivity extends AppCompatActivity {

    Float fedttxtFuelPrice = 0f;
    Float fedttxtFuelConsumption = 0f;
    Float fedttxtDistance = 0f;

    EditText edttxtFuelPrice;
    EditText edttxtFuelConsumption;
    EditText edttxtDistance;

    TextView txtPriceValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel);

        edttxtFuelPrice = (EditText) (findViewById(R.id.edttxtFuelPrice));
        edttxtFuelConsumption = (EditText) (findViewById(R.id.edttxtFuelConsumption));
        edttxtDistance = (EditText) (findViewById(R.id.edttxtDistance));

        txtPriceValue = (TextView)findViewById(R.id.txtPriceValue);
    }

    public void BtnOnClkCalculate(View v)
    {
        fedttxtFuelPrice =       Float.parseFloat (edttxtFuelPrice.getText().toString()) ;
        fedttxtFuelConsumption = Float.parseFloat (edttxtFuelConsumption.getText().toString());
        fedttxtDistance =        Float.parseFloat (edttxtDistance.getText().toString());


        Float result;
        result = (fedttxtDistance / 100) * fedttxtFuelConsumption * fedttxtFuelPrice;

        txtPriceValue.setText(Float.toString(result));
    }

}