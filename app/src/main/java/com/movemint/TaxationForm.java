package com.movemint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class TaxationForm extends ActionBarActivity {
    Spinner origin;
    EditText salary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //A simple form... only has a single dropdown for the province requested, and the salary.
        setContentView(R.layout.activity_taxation_form);
        salary = (EditText) findViewById(R.id.editText2);
        origin = (Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.provinces_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        origin.setAdapter(adapter);

    }

    public void goPressed(View view) {
        //Prepare to move to the results page, and include the salary and province requested.
        if (!salary.getText().toString().equals("")) {
            Intent intent = new Intent(getBaseContext(), TaxationResults.class);
            intent.putExtra("province", origin.getSelectedItem().toString());
            intent.putExtra("salary", Double.parseDouble(salary.getText().toString()));
            startActivity(intent);
        }
    }
}
