package com.mockapps.duedates.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mockapps.duedates.R;
import com.mockapps.duedates.adapters.TrashAdapter;
import com.mockapps.duedates.managers.TrashManager;
import com.mockapps.duedates.objects.Task;

public class TrashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trash);

        ListAdapter myAdapter = new TrashAdapter(this, TrashManager.trashList);
        ListView myListView = (ListView) findViewById(R.id.task_list_view);
        myListView.setAdapter(myAdapter);

        FloatingActionButton restoreFab = (FloatingActionButton) findViewById(R.id.restoreFab);
        restoreFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: CURRENTLY NOT IMPLEMENTED (RESTORE SELECTED)
                Toast.makeText(view.getContext(), "Selected trash items have been restored", Toast.LENGTH_LONG).show();
                refresh();
            }
        });

        FloatingActionButton mainMenuFab = (FloatingActionButton) findViewById(R.id.mainMenuFab);
        mainMenuFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void refresh() {
        Intent i = new Intent(this, TrashActivity.class);
        startActivity(i);
        finish();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (Task task : TrashManager.trashList) {
            task.setSelected(false);
        }
    }

}
