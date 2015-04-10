package com.movemint;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class ComparisonResults extends ActionBarActivity {

    TextView originText;
    TextView salaryCalcText;
    TextView destinationText;
    TextView totalExpenditureText;
    TextView foodText;
    TextView shelterText;
    TextView clothingText;
    TextView transportationText;
    TextView healthCareText;
    TextView recreationText;
    TextView educationText;
    TextView tobaccoAndAlcoholText;
    TextView taxesText;

    Spinner destinationSpinner;

    public void refreshResults() {
        Double salary = getIntent().getExtras().getDouble("salary");

        ExpenditureDBHandler dbHandler = new ExpenditureDBHandler(getApplicationContext(), "expenditureDB");
        Province origin = dbHandler.getCountry(getIntent().getExtras().getString("origin"));
        Province destination = dbHandler.getCountry(destinationSpinner.getSelectedItem().toString());

        //Calculate all our percent changes, to determine the difference between the provinces.
        Double newSalary = salary * (origin.getTotal_expenditure() / destination.getTotal_expenditure());
        Double totalExpenditurePercent = (destination.getTotal_expenditure() / origin.getTotal_expenditure() * 100);
        Double foodPercent = (destination.getFood() / origin.getFood() * 100);
        Double shelterPercent = (destination.getShelter() / origin.getShelter() * 100);
        Double clothingPercent = (destination.getClothing() / origin.getClothing() * 100);
        Double transportationPercent = (destination.getTransportation() / origin.getTransportation() * 100);
        Double healthCarePercent = (destination.getHealth_care() / origin.getHealth_care() * 100);
        Double recreationPercent = (destination.getRecreation() / origin.getRecreation() * 100);
        Double educationPercent = (destination.getEducation() / origin.getEducation() * 100);
        Double tobaccoAndAlcoholPercent = (destination.getTobacco_alcohol() / origin.getTobacco_alcohol() * 100);

        //Begin taxation calcs. Exact same as code on TaxationResults. Please see that page for further comments on this code block.
        //This block calculates the taxes of the origin province.
        Double originTaxableRemainder = salary;
        Double originTax = 0.0;
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
        originTax = originTax + (originTaxableRemainder * ((origin.getTax_rate_6() == null) ? 0 : origin.getTax_rate_6()));

        //Destination Tax Calculation
        Double destinationTaxableRemainder = salary;
        Double destinationTax = 0.0;
        if ((destination.getThreshold1() != null) && (destination.getThreshold1() != 0.0)) {
            if (destinationTaxableRemainder <= destination.getThreshold1()) {
                destinationTax = destinationTax + (destination.getTax_rate_1() * destinationTaxableRemainder);
                destinationTaxableRemainder = 0.0;
            }
            else {
                destinationTax = destinationTax + (destination.getTax_rate_1() * destination.getThreshold1());
                destinationTaxableRemainder = destinationTaxableRemainder - destination.getThreshold1();
            }
        }
        else {
            destinationTax = destinationTax + (destinationTaxableRemainder * ((destination.getTax_rate_1() == null) ? 0 : destination.getTax_rate_1()));
            destinationTaxableRemainder = 0.0;
        }
        if ((destination.getThreshold2() != null) && (destination.getThreshold2() != 0.0)) {
            if (destinationTaxableRemainder <= destination.getThreshold2()) {
                destinationTax = destinationTax + (destination.getTax_rate_2() * destinationTaxableRemainder);
                destinationTaxableRemainder = 0.0;
            }
            else {
                destinationTax = destinationTax + (destination.getTax_rate_2() * destination.getThreshold2());
                destinationTaxableRemainder = destinationTaxableRemainder - destination.getThreshold2();
            }
        }
        else {
            destinationTax = destinationTax + (destinationTaxableRemainder * ((destination.getTax_rate_2() == null) ? 0 : destination.getTax_rate_2()));
            destinationTaxableRemainder = 0.0;
        }
        if ((destination.getThreshold3() != null) && (destination.getThreshold3() != 0.0)) {
            if (destinationTaxableRemainder <= destination.getThreshold3()) {
                destinationTax = destinationTax + (destination.getTax_rate_3() * destinationTaxableRemainder);
                destinationTaxableRemainder = 0.0;
            }
            else {
                destinationTax = destinationTax + (destination.getTax_rate_3() * destination.getThreshold3());
                destinationTaxableRemainder = destinationTaxableRemainder - destination.getThreshold3();
            }
        }
        else {
            destinationTax = destinationTax + (destinationTaxableRemainder * ((destination.getTax_rate_3() == null) ? 0 : destination.getTax_rate_3()));
            destinationTaxableRemainder = 0.0;
        }
        if ((destination.getThreshold4() != null) && (destination.getThreshold4() != 0.0)) {
            if (destinationTaxableRemainder <= destination.getThreshold4()) {
                destinationTax = destinationTax + (destination.getTax_rate_4() * destinationTaxableRemainder);
                destinationTaxableRemainder = 0.0;
            }
            else {
                destinationTax = destinationTax + (destination.getTax_rate_4() * destination.getThreshold4());
                destinationTaxableRemainder = destinationTaxableRemainder - destination.getThreshold4();
            }
        }
        else {
            destinationTax = destinationTax + (destinationTaxableRemainder * ((destination.getTax_rate_4() == null) ? 0 : destination.getTax_rate_4()));
            destinationTaxableRemainder = 0.0;
        }
        if ((destination.getThreshold5() != null) && (destination.getThreshold5() != 0.0)) {
            if (destinationTaxableRemainder <= destination.getThreshold5()) {
                destinationTax = destinationTax + (destination.getTax_rate_5() * destinationTaxableRemainder);
                destinationTaxableRemainder = 0.0;
            }
            else {
                destinationTax = destinationTax + (destination.getTax_rate_5() * destination.getThreshold5());
                destinationTaxableRemainder = destinationTaxableRemainder - destination.getThreshold5();
            }
        }
        else {
            destinationTax = destinationTax + (destinationTaxableRemainder * ((destination.getTax_rate_5() == null) ? 0 : destination.getTax_rate_5()));
            destinationTaxableRemainder = 0.0;
        }
        destinationTax = destinationTax + (destinationTaxableRemainder * ((destination.getTax_rate_6() == null) ? 0 : destination.getTax_rate_6()));

        //Now that we have both taxrates, calculate the percentage difference. Default to zero if either are missing.
        Double taxesPercent = ((destinationTax != 0.0) && (originTax != 0.0)) ? (((destinationTax / originTax) * 100)) : 0;

        //Display the results to the user.
        originText.setText("$" + String.format("%.2f", salary) + " in " + origin.getProvinceName() + " is worth");
        salaryCalcText.setText("$" + String.format("%.2f", newSalary));

        if (newSalary > salary) {
            salaryCalcText.setTextColor(Color.rgb(0, 200, 0));
        }
        else if (salary > newSalary) {
            salaryCalcText.setTextColor(Color.rgb(255, 0, 0));
        }
        else {
            salaryCalcText.setTextColor(Color.rgb(128,128,128));
        }

        destinationText.setText("in " + destination.getProvinceName());
        totalExpenditureText.setText("Total Expenditure: " + String.format("%.2f", Math.abs(totalExpenditurePercent)) + "% ($" + String.format("%.0f", origin.getTotal_expenditure()) + " vs $" + String.format("%.0f", destination.getTotal_expenditure()) + ")");
        foodText.setText("Food: " + String.format("%.2f", Math.abs(foodPercent)) + "%" + " ($" + String.format("%.0f", origin.getFood()) + " vs $" + String.format("%.0f", destination.getFood()) + ")");
        shelterText.setText("Shelter: " + String.format("%.2f", Math.abs(shelterPercent)) + "%" + " ($" + String.format("%.0f", origin.getShelter()) + " vs $" + String.format("%.0f", destination.getShelter()) + ")");
        clothingText.setText("Clothing: " + String.format("%.2f", Math.abs(clothingPercent)) + "%" + " ($" + String.format("%.0f", origin.getClothing()) + " vs $" + String.format("%.0f", destination.getClothing()) + ")");
        transportationText.setText("Transportation: " + String.format("%.2f", Math.abs(transportationPercent)) + "%" + " (" + String.format("%.0f", origin.getTransportation()) + " vs $" + String.format("%.0f", destination.getTransportation()) + ")");
        healthCareText.setText("Health Care: " + String.format("%.2f", Math.abs(healthCarePercent)) + "%" + " ($" + String.format("%.0f", origin.getHealth_care()) + " vs $" + String.format("%.0f", destination.getHealth_care()) + ")");
        recreationText.setText("Recreation: " + String.format("%.2f", Math.abs(recreationPercent)) + "%" + " ($" + String.format("%.0f", origin.getRecreation()) + " vs $" + String.format("%.0f", destination.getRecreation()) + ")");
        educationText.setText("Education: " + String.format("%.2f", Math.abs(educationPercent)) + "%" + " ($" + String.format("%.0f", origin.getEducation()) + " vs $" + String.format("%.0f", destination.getEducation()) + ")");
        tobaccoAndAlcoholText.setText("Tobacco and Alcohol: " + String.format("%.2f", Math.abs(tobaccoAndAlcoholPercent)) + "%" + " ($" + String.format("%.0f", origin.getTobacco_alcohol()) + " vs $" + String.format("%.0f", destination.getTobacco_alcohol()) + ")");
        if (taxesPercent != 0.0) taxesText.setText("Provincial Taxes: " + String.format("%.2f", Math.abs(taxesPercent)) + "%" + " ($" + String.format("%.0f", originTax) + " vs $" + String.format("%.0f", destinationTax) + ")");
        else taxesText.setText("Provincial Taxes: Not Available");

        //Begin colocoding. If something is cheaper, it is green. If it is more expensive, red.
        if (origin.getTotal_expenditure() > destination.getTotal_expenditure()) {
            totalExpenditureText.setTextColor(Color.rgb(0, 200, 0));
        }
        else if (destination.getTotal_expenditure() > origin.getTotal_expenditure()) {
            totalExpenditureText.setTextColor(Color.rgb(255,0,0));
        }
        else {
            totalExpenditureText.setTextColor(Color.rgb(128,128,128));
        }

        if (origin.getFood() > destination.getFood()) {
            foodText.setTextColor(Color.rgb(0, 200, 0));
        }
        else if (destination.getFood() > origin.getFood()) {
            foodText.setTextColor(Color.rgb(255,0,0));
        }
        else {
            foodText.setTextColor(Color.rgb(128,128,128));
        }
        if (origin.getShelter() > destination.getShelter()) {
            shelterText.setTextColor(Color.rgb(0, 200, 0));
        }
        else if (destination.getShelter() > origin.getShelter()) {
            shelterText.setTextColor(Color.rgb(255,0,0));
        }
        else {
            shelterText.setTextColor(Color.rgb(128,128,128));
        }
        if (origin.getClothing() > destination.getClothing()) {
            clothingText.setTextColor(Color.rgb(0, 200, 0));
        }
        else if (destination.getClothing() > origin.getClothing()) {
            clothingText.setTextColor(Color.rgb(255, 0, 0));
        }
        else {
            clothingText.setTextColor(Color.rgb(128,128,128));
        }
        if (origin.getTransportation() > destination.getTransportation()) {
            transportationText.setTextColor(Color.rgb(0, 200, 0));
        }
        else if (destination.getTransportation() > origin.getTransportation()) {
            transportationText.setTextColor(Color.rgb(255,0,0));
        }
        else {
            transportationText.setTextColor(Color.rgb(128,128,128));
        }
        if (origin.getHealth_care() > destination.getHealth_care()) {
            healthCareText.setTextColor(Color.rgb(0, 200, 0));
        }
        else if (destination.getHealth_care() > origin.getHealth_care()) {
            healthCareText.setTextColor(Color.rgb(255,0,0));
        }
        else {
            healthCareText.setTextColor(Color.rgb(128,128,128));
        }
        if (origin.getRecreation() > destination.getRecreation()) {
            recreationText.setTextColor(Color.rgb(0, 200, 0));
        }
        else if (destination.getRecreation() > origin.getRecreation()) {
            recreationText.setTextColor(Color.rgb(255,0,0));
        }
        else {
            recreationText.setTextColor(Color.rgb(128,128,128));
        }
        if (origin.getEducation() > destination.getEducation()) {
            educationText.setTextColor(Color.rgb(0, 200, 0));
        }
        else if (destination.getEducation() > origin.getEducation()) {
            educationText.setTextColor(Color.rgb(255,0,0));
        }
        else {
            educationText.setTextColor(Color.rgb(128,128,128));
        }
        if (origin.getTobacco_alcohol() > destination.getTobacco_alcohol()) {
            tobaccoAndAlcoholText.setTextColor(Color.rgb(0, 200, 0));
        }
        else if (destination.getTobacco_alcohol() > origin.getTobacco_alcohol()) {
            tobaccoAndAlcoholText.setTextColor(Color.rgb(255,0,0));
        }
        else {
            tobaccoAndAlcoholText.setTextColor(Color.rgb(128,128,128));
        }
        if ((originTax > destinationTax) && (taxesPercent != 0.0)) {
            taxesText.setTextColor(Color.rgb(0, 200, 0));
        }
        else if ((destinationTax > originTax) && (taxesPercent != 0.0)) {
            taxesText.setTextColor(Color.rgb(255,0,0));
        }
        else {
            taxesText.setTextColor(Color.rgb(128,128,128));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparison_results);

        destinationSpinner = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.provinces_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        destinationSpinner.setAdapter(adapter);

        originText = (TextView) findViewById(R.id.textView4);
        salaryCalcText = (TextView) findViewById(R.id.textView5);
        destinationText = (TextView) findViewById(R.id.textView6);
        totalExpenditureText = (TextView) findViewById(R.id.textView7);
        foodText = (TextView) findViewById(R.id.textView8);
        shelterText = (TextView) findViewById(R.id.textView9);
        clothingText = (TextView) findViewById(R.id.textView10);
        transportationText = (TextView) findViewById(R.id.textView11);
        healthCareText = (TextView) findViewById(R.id.textView12);
        recreationText = (TextView) findViewById(R.id.textView13);
        educationText = (TextView) findViewById(R.id.textView14);
        tobaccoAndAlcoholText = (TextView) findViewById(R.id.textView15);
        taxesText = (TextView) findViewById(R.id.textView16);

        for (int i = 0; i < destinationSpinner.getCount(); i++) {
            if (destinationSpinner.getItemAtPosition(i).toString().equalsIgnoreCase(getIntent().getExtras().getString("destination"))){
                destinationSpinner.setSelection(i);
                break;
            }
        }

        refreshResults();

        //Spinner listener, in case the user wants to compare to another province easily.
        destinationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                refreshResults();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
}
