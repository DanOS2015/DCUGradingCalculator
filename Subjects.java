package com.example.dan.dcugradingcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.content.Intent;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import com.example.dan.dcugradingcalculator.Result;
import static android.view.ViewGroup.LayoutParams.FILL_PARENT;
import java.util.ArrayList;
import android.text.TextUtils;


public class Subjects extends AppCompatActivity implements OnClickListener
{

    EditText [] inputs;
    Button calculate;
    ScrollView sv;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.setMargins(20,0,0,0);
        LinearLayout.LayoutParams calParams = new LinearLayout.LayoutParams(
                FILL_PARENT,
                LayoutParams.WRAP_CONTENT);
        sv = new ScrollView(this);
        LinearLayout top = new LinearLayout(this);
        top.setLayoutParams(params);
        TextView instructions = new TextView(this);
        top.addView(instructions);
        instructions.setText("Enter your results along with the corresponding credits for each module (60 credits in total)");
        instructions.setGravity(Gravity.CENTER);
        layout.addView(top);
        calculate = new Button(this);
        calculate.setText("Calculate");
        calculate.setLayoutParams(calParams);
        Bundle extra = getIntent().getExtras();
        int result = extra.getInt("result");
        result = result * 2;
        inputs = new EditText[result];
        int j = 1;
        int index = 0;
        for (int i=0 ;i < result; i = i + 2)
        {
            LinearLayout row = new LinearLayout(this);
            TextView subject = new TextView(this);
            EditText numResult = new EditText(Subjects.this);
            numResult.setInputType(InputType.TYPE_CLASS_NUMBER);
            inputs[index] = numResult;
            index++;
            numResult.setGravity(Gravity.CENTER);
            EditText gradeResult = new EditText(Subjects.this);
            gradeResult.setInputType(InputType.TYPE_CLASS_NUMBER);
            inputs[index] = gradeResult;
            index++;
            gradeResult.setGravity(Gravity.CENTER);
            numResult.setLayoutParams(params);
            gradeResult.setLayoutParams(params);
            numResult.setHint("Result");
            gradeResult.setHint("Credit");
            row.addView(subject);
            subject.setText("Subject " + j + ":");
            j++;
            row.addView(numResult);
            row.addView(gradeResult);
            layout.addView(row);
        }
        LinearLayout buttonRow = new LinearLayout(this);
        buttonRow.addView(calculate);
        layout.addView(buttonRow);
        calculate.setOnClickListener((OnClickListener) this);
        sv.addView(layout);
        setContentView(sv);
    }

    @Override
    public void onClick(View v)
    {
        float num = 0;
        float cred = 0;
        float total = 0;
        float grade = 0;
        ArrayList<Float> failed = new ArrayList<>();
        int check = 0;
        boolean failCheck = true;
        for (int i = 0; i < inputs.length; i = i + 2)
        {
            num = Float.parseFloat(inputs[i].getText().toString());
            cred = Float.parseFloat(inputs[i + 1].getText().toString());
            if (num <= 39.0)
            {
                check = 1;
                failCheck = false;
                failed.add(num);
                failed.add(cred);
            }
            total = num * cred;
            grade += total;
        }
        grade = grade / 60;
        if ((!failCheck) && (checkComp(failed, grade)))
        {
            check = 2;
        }
        Intent i = new Intent(this, Result.class);
        i.putExtra("grade", grade);
        i.putExtra("check", check);
        startActivity(i);

    }

    public boolean checkComp(ArrayList<Float> subjects, float grade)
    {
        if(grade <= 44.5)
            return false;
        for(int i = 0; i < subjects.size()-1; i=i+2)
        {
            if(subjects.get(i) <= 34.0)
                return false;
        }
        int credSum = 0;
        for(int i = 1; i < subjects.size(); i=i+2)
        {
            credSum += subjects.get(i);
        }
        if(credSum > 10.0)
            return false;
        return true;
    }


}
