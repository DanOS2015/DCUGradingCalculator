package com.example.dan.dcugradingcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class Menu extends AppCompatActivity implements OnClickListener
{
    //buttons to take you to different activites of the application
    Button start;
    Button cal;
    Button comp;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //initilise all buttons and set OnClickListener 
        start = (Button) findViewById(R.id.startButton);
        cal = (Button) findViewById(R.id.calButton);
        comp = (Button) findViewById(R.id.compButton);
        start.setOnClickListener((OnClickListener)this);
        cal.setOnClickListener((OnClickListener)this);
        comp.setOnClickListener((OnClickListener)this);
    }
    //set what each buttons do 
    @Override
    public void onClick(View v)
    {
        Intent i;
        if(v.getId() == R.id.startButton)
        {
            i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        else if(v.getId() == R.id.calButton)
        {
            i = new Intent(this, Calculation.class);
            startActivity(i);
        }
        else
        {
            i = new Intent(this, Compensation.class);
            startActivity(i);
        }
    }
}
