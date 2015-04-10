package com.movemint;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class TaxationResults extends ActionBarActivity {

    Spinner provinceSpinner;
    TextView originText;
    TextView taxesText;
    TextView postText;
    TextView range1;
    TextView range2;
    TextView range3;
    TextView range4;
    TextView range5;
    TextView range6;


    public void updateTaxes() {
        ExpenditureDBHandler dbHandler = new ExpenditureDBHandler(getApplicationContext(), "expenditureDB");
        Province origin = dbHandler.getCountry(provinceSpinner.getSelectedItem().toString());
        //Retrieve the salary that was passed in.
        double salary = getIntent().getExtras().getDouble("salary");
        //Incoming taxation calculations. Start with a taxable income variable that depletes as we tax it.
        Double originTaxableRemainder = salary;
        Double originTax = 0.0;
        //Check each threshold to see if it exists, and tax accordingly.
        if ((origin.getThreshold1() != null) && (origin.getThreshold1() != 0.0)) {
            if (originTaxableRemainder <= origin.getThreshold1()) {
                originTax = originTax + (origin.getTax_rate_1() * originTaxableRemainder);
                originTaxableRemainder = 0.0;
            }
            else {
                originTax = originTax + (origin.getTax_rate_1() * origin.getThreshold1());
                originTaxableRemainder = originTaxableRemainder - origin.getThreshold1();
            }
        }
        else {
            originTax = originTax + (originTaxableRemainder * ((origin.getTax_rate_1() == null) ? 0 : origin.getTax_rate_1()));
            originTaxableRemainder = 0.0;
        }
        if ((origin.getThreshold2() != null) && (origin.getThreshold2() != 0.0)) {
            if (originTaxableRemainder <= origin.getThreshold2()) {
                originTax = originTax + (origin.getTax_rate_2() * originTaxableRemainder);
                originTaxableRemainder = 0.0;
            }
            else {
                originTax = originTax + (origin.getTax_rate_2() * origin.getThreshold2());
                originTaxableRemainder = originTaxableRemainder - origin.getThreshold2();
            }
        }
        else {
            originTax = originTax + (originTaxableRemainder * ((origin.getTax_rate_2() == null) ? 0 : origin.getTax_rate_2()));
            originTaxableRemainder = 0.0;
        }
        if ((origin.getThreshold3() != null) && (origin.getThreshold3() != 0.0)) {
            if (originTaxableRemainder <= origin.getThreshold3()) {
                originTax = originTax + (origin.getTax_rate_3() * originTaxableRemainder);
                originTaxableRemainder = 0.0;
            }
            else {
                originTax = originTax + (origin.getTax_rate_3() * origin.getThreshold3());
                originTaxableRemainder = originTaxableRemainder - origin.getThreshold3();
            }
        }
        else {
            originTax = originTax + (originTaxableRemainder * ((origin.getTax_rate_3() == null) ? 0 : origin.getTax_rate_3()));
            originTaxableRemainder = 0.0;
        }
        if ((origin.getThreshold4() != null) && (origin.getThreshold4() != 0.0)) {
            if (originTaxableRemainder <= origin.getThreshold4()) {
                originTax = originTax + (origin.getTax_rate_4() * originTaxableRemainder);
                originTaxableRemainder = 0.0;
            }
            else {
                originTax = originTax + (origin.getTax_rate_4() * origin.getThreshold4());
                originTaxableRemainder = originTaxableRemainder - origin.getThreshold4();
            }
        }
        else {
            originTax = originTax + (originTaxableRemainder * ((origin.getTax_rate_4() == null) ? 0 : origin.getTax_rate_4()));
            originTaxableRemainder = 0.0;
        }
        if ((origin.getThreshold5() != null) && (origin.getThreshold5() != 0.0)) {
            if (originTaxableRemainder <= origin.getThreshold5()) {
                originTax = originTax + (origin.getTax_rate_5() * originTaxableRemainder);
                originTaxableRemainder = 0.0;
            }
            else {
                originTax = originTax + (origin.getTax_rate_5() * origin.getThreshold5());
                originTaxableRemainder = originTaxableRemainder - origin.getThreshold5();
            }
        }
        else {
            originTax = originTax + (originTaxableRemainder * ((origin.getTax_rate_5() == null) ? 0 : origin.getTax_rate_5()));
            originTaxableRemainder = 0.0;
        }
        //If there is anything remaining, tax it at the final rate.
        originTax = originTax + (originTaxableRemainder * ((origin.getTax_rate_6() == null) ? 0 : origin.getTax_rate_6()));

        //Display the results to the user.
        originText.setText("In " + origin.getProvinceName() + " and with an income of " + String.format("%.2f", salary) + " you would owe");
        taxesText.setText("$" + String.format("%.2f", originTax));
        postText.setText("in provincial taxes.");
        //Blank all fields out, just in case.
        range1.setText("");
        range2.setText("");
        range3.setText("");
        range4.setText("");
        range5.setText("");
        range6.setText("");
        //Sequentially fill in each of the taxation thresholds for the user's information.
        if (origin.getThreshold1() != null) {
            if (origin.getThreshold1() != 0) {
                range1.setText("From $0 to $" + origin.getThreshold1() + ": " + origin.getTax_rate_1() + "%");
                if (origin.getThreshold2() != 0) {
                    range2.setText("From $" + String.format("%.2f", origin.getThreshold1()) + " TO $" + String.format("%.2f", origin.getThreshold2()) + ": " + origin.getTax_rate_2() + "%");
                    if (origin.getThreshold3() != 0) {
                        range3.setText("From $" + String.format("%.2f", origin.getThreshold2()) + " TO $" + String.format("%.2f", origin.getThreshold3()) + ": " + origin.getTax_rate_3() + "%");
                        if (origin.getThreshold4() != 0) {
                            range4.setText("From $" + String.format("%.2f", origin.getThreshold3()) + " TO $" + String.format("%.2f", origin.getThreshold4()) + ": " + origin.getTax_rate_4() + "%");
                            if (origin.getThreshold5() != 0) {
                                range5.setText("From $" + String.format("%.2f", origin.getThreshold4()) + " TO $" + String.format("%.2f", origin.getThreshold5()) + ": " + origin.getTax_rate_5() + "%");
                                range6.setText("Over $" + String.format("%.2f", origin.getThreshold5()) + ": " + origin.getTax_rate_6() + "%");
                            }
                            else {
                                range5.setText("Over $" + String.format("%.2f", origin.getThreshold4()) + ": " + origin.getTax_rate_5() + "%");
                            }
                        }
                        else {
                            range4.setText("Over $" + String.format("%.2f", origin.getThreshold3()) + ": " + origin.getTax_rate_4() + "%");
                        }
                    }
                    else {
                        range3.setText("Over $" + String.format("%.2f", origin.getThreshold2()) + ": " + origin.getTax_rate_3() + "%");
                    }
                }
                else {
                    range2.setText("Over $" + String.format("%.2f", origin.getThreshold1()) + ": " + origin.getTax_rate_2() + "%");
                }
            }
            else {
                range1.setText("Over $0: " + origin.getTax_rate_1());
            }
        }
        else {
            range1.setText("No taxation info available.");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxation_results);
        originText = (TextView) findViewById(R.id.textView20);
        taxesText = (TextView) findViewById(R.id.textView21);
        postText = (TextView) findViewById(R.id.textView29);
        range1 = (TextView) findViewById(R.id.textView22);
        range2 = (TextView) findViewById(R.id.textView23);
        range3 = (TextView) findViewById(R.id.textView24);
        range4 = (TextView) findViewById(R.id.textView25);
        range5 = (TextView) findViewById(R.id.textView26);
        range6 = (TextView) findViewById(R.id.textView27);
        provinceSpinner = (Spinner) findViewById(R.id.spinner5);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.provinces_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinceSpinner.setAdapter(adapter);

        for (int i = 0; i < provinceSpinner.getCount(); i++) {
            if (provinceSpinner.getItemAtPosition(i).toString().equalsIgnoreCase(getIntent().getExtras().getString("province"))){
                provinceSpinner.setSelection(i);
                break;
            }
        }

        updateTaxes();

        //Spinner listener, in case they want to compare to another province easily.
        provinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updateTaxes();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
}
