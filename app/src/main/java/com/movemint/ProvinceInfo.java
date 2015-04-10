package com.movemint;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class ProvinceInfo extends ActionBarActivity {

    Spinner selectedProvince;
    TextView total_expenditure;
    TextView food;
    TextView shelter;
    TextView clothing;
    TextView transportation;
    TextView health_care;
    TextView recreation;
    TextView education;
    TextView tobacco_alcohol;

    public void updateProvince() {
        //Retrieve the province based on which one is selected in the dropdown.
        ExpenditureDBHandler dbHandler = new ExpenditureDBHandler(getApplicationContext(), "expenditureDB");
        Province province = dbHandler.getCountry(selectedProvince.getSelectedItem().toString());

        //Populate the textboxes based on the info in the province selected.
        total_expenditure.setText("Average Total Expenditure: $" + province.getTotal_expenditure());
        food.setText("Average Food: $" + province.getFood());
        shelter.setText("Average Shelter: $" + province.getShelter());
        clothing.setText("Average Clothing: $" + province.getClothing());
        transportation.setText("Average Transportation: $" + province.getTransportation());
        health_care.setText("Average Health Care: $" + province.getHealth_care());
        recreation.setText("Average Recreation: $" + province.getRecreation());
        education.setText("Average Education: $" + province.getEducation());
        tobacco_alcohol.setText("Average Tobacco/Alcohol: $" + province.getTobacco_alcohol());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_province_info);

        total_expenditure = (TextView) findViewById(R.id.textView31);
        food = (TextView) findViewById(R.id.textView32);
        shelter = (TextView) findViewById(R.id.textView33);
        clothing = (TextView) findViewById(R.id.textView34);
        transportation = (TextView) findViewById(R.id.textView35);
        health_care = (TextView) findViewById(R.id.textView36);
        recreation = (TextView) findViewById(R.id.textView37);
        education = (TextView) findViewById(R.id.textView38);
        tobacco_alcohol = (TextView) findViewById(R.id.textView39);
        selectedProvince = (Spinner) findViewById(R.id.spinner6);

        //When we first hit this screen and no province is selected, start with blank fields.
        total_expenditure.setText("");
        food.setText("");
        shelter.setText("");
        clothing.setText("");
        transportation.setText("");
        health_care.setText("");
        recreation.setText("");
        education.setText("");
        tobacco_alcohol.setText("");

        //Populate the dropdown.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.provinces_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectedProvince.setAdapter(adapter);

        //A listener for when the province selected in the dropdown changes.
        selectedProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //When there is a change, update the province being viewed.
                updateProvince();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Nothing selected isn't applicable. Blank for now.
            }

        });
    }
}
