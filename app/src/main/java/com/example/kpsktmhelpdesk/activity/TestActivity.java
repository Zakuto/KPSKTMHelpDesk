package com.example.kpsktmhelpdesk.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kpsktmhelpdesk.R;

public class TestActivity extends AppCompatActivity {
    String COUNTIRES [] = new String[]{"Country 1","Country 2","Country 3"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),R.layout.dropdownmenu_popup_item,COUNTIRES);

        //AutoCompleteTextView editedTextFilledExposedDown = findViewById(R.id.departmentList);
        //editedTextFilledExposedDown.setAdapter(adapter);
    }
}
