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
    Button done;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        done = (Button) findViewById(R.id.done);
        input = (EditText) findViewById(R.id.numOfSubjects);
        done.setOnClickListener((OnClickListener)this);
    }

    @Override
    public void onClick(View v)
    {
        if(TextUtils.isEmpty(input.getText().toString()))
            return;
        int numOfSubjects = Integer.parseInt(input.getText().toString());
        Intent i = new Intent(this, Subjects.class);
        i.putExtra("result", numOfSubjects);
        startActivity(i);
    }
}

