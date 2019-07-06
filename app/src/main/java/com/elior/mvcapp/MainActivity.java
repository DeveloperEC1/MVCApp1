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

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvTask;
    private Button btnNewTask;
    private EditText etNewTask;
    private MVCController controller;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

        initUI();
        populateTasks();
    }

    private void initUI() {
        lvTask = findViewById(R.id.lvTask);
        btnNewTask = findViewById(R.id.btNewTask);
        etNewTask = findViewById(R.id.etNewTask);

        controller = new MVCController(this);

        btnNewTask.setOnClickListener(handleNewTaskEvent);
    }

    private void populateTasks() {
        final List<String> tasks = controller.getTasks();
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

    private final View.OnClickListener handleNewTaskEvent = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            controller.addTask(etNewTask.getText().toString());
            populateTasks();
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
