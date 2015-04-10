package com.movemint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class SalaryComparisonForm extends ActionBarActivity {

    Spinner origin;
    Spinner destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary_comparison_form);

        //Populate both the from and to drop downs with all the possible provinces.
        origin = (Spinner) findViewById(R.id.spinner);
        destination = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.provinces_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        origin.setAdapter(adapter);
        destination.setAdapter(adapter);
    }

    public void goPressed(View view) {
        Spinner origin = (Spinner) findViewById(R.id.spinner);
        Spinner destination = (Spinner) findViewById(R.id.spinner2);
        EditText salary = (EditText) findViewById(R.id.editText);

        if (!salary.getText().toString().equals("")) {
            //Prepare to navigate to the results page... pass along the from, to and salary.
            Intent intent = new Intent(getBaseContext(), ComparisonResults.class);
            intent.putExtra("origin", origin.getSelectedItem().toString());
            intent.putExtra("destination", destination.getSelectedItem().toString());
            intent.putExtra("salary", Double.parseDouble(salary.getText().toString()));
            startActivity(intent);
        }
    }
}
