package com.example.lelu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

        //remove action bar
        getSupportActionBar().hide();
        //change color of status bar
        Window window = FuelActivity.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.black));
        //tried :(
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

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