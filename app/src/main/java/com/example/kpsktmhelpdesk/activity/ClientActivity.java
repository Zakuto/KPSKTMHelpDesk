package com.example.kpsktmhelpdesk.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kpsktmhelpdesk.R;
import com.example.kpsktmhelpdesk.constants.ClientConstant;
import com.example.kpsktmhelpdesk.constants.ReportConstant;
import com.google.android.material.textfield.TextInputEditText;

public class ClientActivity extends AppCompatActivity {

    public static final String TAG = "ClientActivity";

    private TextInputEditText applicantName;
    private TextInputEditText position;
    private TextInputEditText problemDetails;
    Button saveClientButton;
    String probSpec,deptName;
    int probSpecPos,deptNamePos;
    Spinner deptSpinner;
    Spinner probSpecSpinner;
    //Button viewReportButton;

    private void saveReport(String deptName,String probSpec){
        String applicName = applicantName.getText().toString();
        String pos = position.getText().toString();
        String probDetails = problemDetails.getText().toString();

        if(applicName.trim().isEmpty() || deptName.trim().isEmpty() || pos.trim().isEmpty()
        || probSpec.trim().isEmpty() || probDetails.trim().isEmpty()){
            Toast.makeText(this,"Please fill all the information given",Toast.LENGTH_SHORT).show();
            return;
        }

        Intent i = new Intent();
        i.putExtra(ClientConstant.EXTRA_APPLICANT_NAME,applicName);
        i.putExtra(ClientConstant.EXTRA_DEPT_LIST,deptName);
        i.putExtra(ClientConstant.EXTRA_POSITION,pos);
        i.putExtra(ClientConstant.EXTRA_PROB_SPEC,probSpec);
        i.putExtra(ClientConstant.EXTRA_PROB_DETAILS,probDetails);
        //For edit purpose
        int id = getIntent().getIntExtra(ClientConstant.EXTRA_ID,-1);
        Log.d(TAG,"id is :" + id);
        if(id!= -1){
            i.putExtra(ClientConstant.EXTRA_ID,id);
        }
        setResult(RESULT_OK,i);
        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        final ArrayAdapter<CharSequence> deptListAdapter = ArrayAdapter.createFromResource(this,R.array.dept_names,R.layout.dropdownmenu_popup_item);
        final ArrayAdapter<CharSequence> probSpecAdapter = ArrayAdapter.createFromResource(this,R.array.prob_specs,R.layout.dropdownmenu_popup_item);

        deptListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        probSpecAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //for spinner
        deptSpinner = findViewById(R.id.deptList2);
        probSpecSpinner = findViewById(R.id.probSpec2);

        deptSpinner.setAdapter(deptListAdapter);
        probSpecSpinner.setAdapter(probSpecAdapter);

        deptSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                deptName = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        probSpecSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                probSpec = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        applicantName = findViewById(R.id.applicant_name);
        position = findViewById(R.id.position);
        problemDetails = findViewById(R.id.problemDetails);
        saveClientButton = findViewById(R.id.saveReport);
        //viewReportButton = findViewById(R.id.viewReport);

        saveClientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    saveReport(deptName,probSpec);
            }
        });

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_chevron_left_white_24dp);

        Intent i = getIntent();
        if(i.hasExtra(ClientConstant.EXTRA_ID)){

            deptNamePos = deptListAdapter.getPosition(i.getStringExtra(ClientConstant.EXTRA_DEPT_LIST));
            probSpecPos = probSpecAdapter.getPosition(i.getStringExtra(ClientConstant.EXTRA_PROB_SPEC));

            setTitle(ReportConstant.CLIENT_EDIT_REPORT);
            applicantName.setText(i.getStringExtra(ClientConstant.EXTRA_APPLICANT_NAME));
            deptSpinner.setSelection(deptNamePos);
            probSpecSpinner.setSelection(probSpecPos);
            position.setText(i.getStringExtra(ClientConstant.EXTRA_POSITION));
            problemDetails.setText(i.getStringExtra(ClientConstant.EXTRA_PROB_DETAILS));

        }else{
            setTitle(ReportConstant.CLIENT_ADD_REPORT);
        }
    }


}
