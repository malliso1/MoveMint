package com.movemint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //These functions allow navigation to each of the views through the buttons.
    public void goToComparisonCalculator(View view) {
        Intent intent = new Intent(getBaseContext(), SalaryComparisonForm.class);
        startActivity(intent);
    }

    public void goToTaxCalculator(View view) {
        Intent intent = new Intent(getBaseContext(), TaxationForm.class);
        startActivity(intent);
    }

    public void goToProvinceInfo(View view) {
        Intent intent = new Intent(getBaseContext(), ProvinceInfo.class);
        startActivity(intent);
    }
}
