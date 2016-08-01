package com.example.dan.dcugradingcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity implements OnClickListener
{
    //button, edittext and textview created in xml 
    Button done;
    EditText input;
    TextView error;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //find button, edittext and textview by finding their ids
        done = (Button) findViewById(R.id.done);
        input = (EditText) findViewById(R.id.numOfSubjects);
        error = (TextView) findViewById(R.id.errorMessage);
        //set on click listener on the button 
        done.setOnClickListener((OnClickListener)this);
    }

    @Override
    public void onClick(View v)
    {
        //if the text field is empty and button is clicked set message and do nothing 
        if(TextUtils.isEmpty(input.getText().toString()))
        {
            error.setText("ERROR: Please enter a number before continuing");
            return;
        }
        //get value from edittext and covert it to an integer 
        int numOfSubjects = Integer.parseInt(input.getText().toString());
        Intent i = new Intent(this, Subjects.class);
        //send numOfSubjects to next activity
        i.putExtra("result", numOfSubjects);
        //start next activity 
        startActivity(i);
    }
}

