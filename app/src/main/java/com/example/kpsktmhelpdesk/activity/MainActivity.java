package com.example.kpsktmhelpdesk.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kpsktmhelpdesk.R;
import com.example.kpsktmhelpdesk.adapter.ClientListAdapter;
import com.example.kpsktmhelpdesk.constants.ClientConstant;
import com.example.kpsktmhelpdesk.model.entity.Client;
import com.example.kpsktmhelpdesk.util.Utils;
import com.example.kpsktmhelpdesk.viewmodel.ClientViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ClientViewModel clientViewModel;
    public static final int ADD_CLIENT_REPORT = 1;
    public static final int EDIT_CLIENT_REPORT = 2;
    FloatingActionButton floatingActionButton;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);

        floatingActionButton = findViewById(R.id.button_add_client);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ClientActivity.class);
                startActivityForResult(i,ADD_CLIENT_REPORT);
            }
        });

        //set RecyclerView
        RecyclerView recyclerView = findViewById(R.id.clientRecView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //SetAdapter for recyclerview
        final ClientListAdapter clientListAdapter = new ClientListAdapter();
        recyclerView.setAdapter(clientListAdapter);

        clientViewModel = ViewModelProviders.of(this).get(ClientViewModel.class);
        clientViewModel.getAllClient().observe(this, new Observer<List<Client>>() {
            @Override
            public void onChanged(@Nullable List<Client> clients) {
                clientListAdapter.setClients(clients);
            }
        });

        clientListAdapter.setCustomOnItemClickListener(new ClientListAdapter.CustomOnItemClickListener() {
            @Override
            public void onItemClick(Client client,View v) {
                if(client.getReportStatus().equals(ClientConstant.CLIENT_REPORT_COMPLETED)) {
                    v.setClickable(false);
                    v.setBackgroundResource(R.color.input_register);
                    Toast.makeText(MainActivity.this, "Report already completed.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i = new Intent(MainActivity.this, ClientActivity.class);
                    i.putExtra(ClientConstant.EXTRA_ID, client.getClientId());
                    i.putExtra(ClientConstant.EXTRA_APPLICANT_NAME, client.getClientName());
                    i.putExtra(ClientConstant.EXTRA_DEPT_LIST, client.getDepartment());
                    i.putExtra(ClientConstant.EXTRA_POSITION, client.getPosition());
                    i.putExtra(ClientConstant.EXTRA_PROB_SPEC, client.getProbSpec());
                    i.putExtra(ClientConstant.EXTRA_PROB_DETAILS, client.getProbDetails());
                    startActivityForResult(i, EDIT_CLIENT_REPORT);
                }
                v.setClickable(true);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == ADD_CLIENT_REPORT && resultCode == RESULT_OK) {
                String applicName = data.getStringExtra(ClientConstant.EXTRA_APPLICANT_NAME);
                String departName = data.getStringExtra(ClientConstant.EXTRA_DEPT_LIST);
                String pos = data.getStringExtra(ClientConstant.EXTRA_POSITION);
                String probSpec = data.getStringExtra(ClientConstant.EXTRA_PROB_SPEC);
                String probDetails = data.getStringExtra(ClientConstant.EXTRA_PROB_DETAILS);
                Client client = new Client(applicName, departName, pos, probSpec, probDetails, Utils.getCurrentDate(), ClientConstant.CLIENT_REPORT_NEW);
                clientViewModel.insert(client);

                Toast.makeText(this, "Client report saved.", Toast.LENGTH_SHORT).show();
            } else if (requestCode == EDIT_CLIENT_REPORT && resultCode == RESULT_OK) {
                String applicName = data.getStringExtra(ClientConstant.EXTRA_APPLICANT_NAME);
                String departName = data.getStringExtra(ClientConstant.EXTRA_DEPT_LIST);
                String pos = data.getStringExtra(ClientConstant.EXTRA_POSITION);
                String probSpec = data.getStringExtra(ClientConstant.EXTRA_PROB_SPEC);
                String probDetails = data.getStringExtra(ClientConstant.EXTRA_PROB_DETAILS);
                int id = data.getIntExtra(ClientConstant.EXTRA_ID, -1);

                if (id == -1) {
                    Toast.makeText(this, "Client report can't be modified.", Toast.LENGTH_SHORT).show();
                } else {
                    Client client = new Client(applicName, departName, pos, probSpec, probDetails, Utils.getCurrentDate(), ClientConstant.CLIENT_REPORT_UPDATED);
                    client.setClientId(id);
                    clientViewModel.update(client);
                    Toast.makeText(this, "Client report modified.", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Client report not saved.", Toast.LENGTH_SHORT).show();
            }

    }
}
