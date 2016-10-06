package com.mockapps.duedates.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.mockapps.duedates.R;
import com.mockapps.duedates.adapters.DateAdapter;
import com.mockapps.duedates.managers.CategoryManager;
import com.mockapps.duedates.managers.TaskManager;
import com.mockapps.duedates.managers.TrashManager;

public class MainActivity extends AppCompatActivity {

    public static Context masterContext;
    public static boolean listsLoaded = false;
    public static TaskManager taskManager;
    public static CategoryManager categoryManager;
    public static TrashManager trashManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        masterContext = this;

        if (!listsLoaded) {
            taskManager = new TaskManager();
            categoryManager = new CategoryManager();
            trashManager = new TrashManager();
            listsLoaded = true;
        }

        ListAdapter myAdapter = new DateAdapter(this, TaskManager.dateList);
        ListView myListView = (ListView) findViewById(R.id.task_list_view);
        myListView.setAdapter(myAdapter);

        FloatingActionButton addFab = (FloatingActionButton) findViewById(R.id.addFab);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddTaskActivity.class);
                startActivity(intent);
                finish();
            }
        });

        FloatingActionButton trashFab = (FloatingActionButton) findViewById(R.id.trashFab);
        trashFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TrashActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
