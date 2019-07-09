package com.elior.mvcapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvTask;
    private Button btnNewTask;
    private EditText etNewTask;
    private MVCController controller;
    private ArrayList<String> tasks;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

        initUI();
        populateTasks();
        btnAddTask();
    }

    private void initUI() {
        lvTask = findViewById(R.id.lvTask);
        btnNewTask = findViewById(R.id.btNewTask);
        etNewTask = findViewById(R.id.etNewTask);

        controller = new MVCController(this);
    }

    private void populateTasks() {
        tasks = controller.getTasks();
        lvTask.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks.toArray(new String[]{})));
        lvTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
                final TextView v = (TextView) view;
                controller.deleteTask(v.getText().toString());
                populateTasks();
            }
        });
    }

    private void btnAddTask() {
        btnNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.addTask(etNewTask.getText().toString());
                populateTasks();
            }
        });
    }

}
