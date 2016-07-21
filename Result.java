package com.example.dan.dcugradingcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;
import java.lang.Math;

public class Result extends AppCompatActivity
{
    TextView numResult;
    TextView gradeResult;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle extra = getIntent().getExtras();
        float result = extra.getFloat("grade");
        int check = extra.getInt("check");
        result = (float) (Math.round(result * 100.0) / 100.0);
        numResult = (TextView) findViewById(R.id.numResult);
        gradeResult = (TextView) findViewById(R.id.gradeResult);
        numResult.setGravity(Gravity.CENTER);
        gradeResult.setGravity(Gravity.CENTER);
        numResult.setText(result + "");
        if(check == 1)
            gradeResult.setText("Fail, You don't qualify for compensation");
        else if(check == 2)
        {
            if (result >= 69.5)
                gradeResult.setText("First Class Honours (1:1) (Compenstaion) ");
            else if (result >= 59.5)
                gradeResult.setText("Second Class Honours Grade 1 (2:1) (Compenstaion)");
            else if (result >= 49.5)
                gradeResult.setText("Second Class Honours Grade 2 (2:2) (Compensation)");
            else
                gradeResult.setText("Third Class Honours (Compensation)");
        }
        else
        {
            if (result >= 69.5)
                gradeResult.setText("First Class Honours (1:1)");
            else if (result >= 59.5)
                gradeResult.setText("Second Class Honours Grade 1 (2:1)");
            else if (result >= 49.5)
                gradeResult.setText("Second Class Honours Grade 2 (2:2)");
            else
                gradeResult.setText("Third Class Honours");
        }
    }
}
