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

//PLEASE NOTE: still working on error control and refactoring on this activity. Never the less still works when app used properly.
public class Subjects extends AppCompatActivity implements OnClickListener
{
    //create array of edittexts to record amount edittexts specified in last activity 
    ArrayList<EditText> inputs = new ArrayList<EditText>();
    //button to calculate grade
    Button calculate;
    //scrollview in case number of subjects specified is big number and doesn't fit on mobile screen 
    ScrollView sv;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //because some students will have diiferent number of subject i'll have to create the user interface programmatically with java
        //depending on the number of subjects they have 
        super.onCreate(savedInstanceState);
        //create linear layout 
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        //create layout parameters for edittexts to follow when created 
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.setMargins(20,0,0,0);
        LinearLayout.LayoutParams calParams = new LinearLayout.LayoutParams(
                FILL_PARENT,
                LayoutParams.WRAP_CONTENT);
        //initilise scrollview        
        sv = new ScrollView(this);
        //make layout for instructions at top of activity 
        LinearLayout top = new LinearLayout(this);
        top.setLayoutParams(params);
        //create instructions on top of activity for users to follow and add it to view
        TextView instructions = new TextView(this);
        top.addView(instructions);
        instructions.setText("Enter your results along with the corresponding credits for each module (60 credits in total)");
        instructions.setGravity(Gravity.CENTER);
        layout.addView(top);
        //initilise button 
        calculate = new Button(this);
        calculate.setText("Calculate");
        calculate.setLayoutParams(calParams);
        //get numOfSubjects integer from previous activity 
        Bundle extra = getIntent().getExtras();
        int result = extra.getInt("result");
        //for loop to generate subject fields to enter grades 
        for (int i=0; i < result; i++)
        {
            //make a new layout for each row 
            LinearLayout row = new LinearLayout(this);
            //textview for each number of subjects 
            TextView subject = new TextView(this);
            //make edittexts, set their input type to number, and add them to array 
            EditText numResult = new EditText(Subjects.this);
            numResult.setInputType(InputType.TYPE_CLASS_NUMBER);
            inputs.add(numResult);
            numResult.setGravity(Gravity.CENTER);
            EditText gradeResult = new EditText(Subjects.this);
            gradeResult.setInputType(InputType.TYPE_CLASS_NUMBER);
            inputs.add(gradeResult);
            //set the gravity of the edittexts to center 
            gradeResult.setGravity(Gravity.CENTER);
            numResult.setLayoutParams(params);
            gradeResult.setLayoutParams(params);
            //set hints for each edittexts 
            numResult.setHint("Result");
            gradeResult.setHint("Credit");
            //add the edittexts and the textviews for subjects to the row view then add the row view to the main layout 
            row.addView(subject);
            subject.setText("Subject " + (i+1) + ":");
            row.addView(numResult);
            row.addView(gradeResult);
            layout.addView(row);
        }
        //make a layout for the calculate button 
        LinearLayout buttonRow = new LinearLayout(this);
        buttonRow.addView(calculate);
        layout.addView(buttonRow);
        calculate.setOnClickListener((OnClickListener) this);
        //add the main layout to the scrollview and set the content view to the scrollview 
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
        //make arrayliset to hold failed subjects, use this later to calculate compensation 
        ArrayList<Float> failed = new ArrayList<>();
        //use check for faileded subject 
        int check = 0;
        boolean failCheck = true;
        for (int i = 0; i < inputs.size(); i = i + 2)
        {
            num = Float.parseFloat(inputs.get(i).getText().toString());
            cred = Float.parseFloat(inputs.get(i + 1).getText().toString());
            //if subject is failed set checks and add subject and credit to arraylist 
            if (num <= 39.0)
            {
                check = 1;
                failCheck = false;
                failed.add(num);
                failed.add(cred);
            }
            //multiply grade by corresponding credit and add it to total 
            total = num * cred;
            grade += total;
        }
        //divide total by 60 to get average grade 
        grade = grade / 60;
        //use checkComp function to see if can pass by compensation 
        if ((!failCheck) && (checkComp(failed, grade)))
        {
            check = 2;
        }
        //send results to next activity 
        Intent i = new Intent(this, Result.class);
        i.putExtra("grade", grade);
        i.putExtra("check", check);
        startActivity(i);

    }
    //function to check compensation, 
    public boolean checkComp(ArrayList<Float> subjects, float grade)
    {
        //need min average of 45 to pass by compensation
        if(grade <= 44.5)
            return false;
        for(int i = 0; i < subjects.size(); i=i+2)
        {
            //failed subject has to be min 35
            if(subjects.get(i) < 35.0)
                return false;
        }
        //number of credits failed has to be maximum 10 
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
