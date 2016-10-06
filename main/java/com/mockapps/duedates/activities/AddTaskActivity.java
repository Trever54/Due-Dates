package com.mockapps.duedates.activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mockapps.duedates.datepicker.DateSettings;
import com.mockapps.duedates.datepicker.PickerDialogs;
import com.mockapps.duedates.R;
import com.mockapps.duedates.managers.CategoryManager;
import com.mockapps.duedates.objects.Category;
import com.mockapps.duedates.objects.Task;

import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {

    public static TextView dueDateSelection;
    private EditText taskNameInput;
    private String taskName;
    private Context context;

    private RadioGroup categoryButtonGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        context = this;
        taskNameInput = (EditText) findViewById(R.id.task_name_input);
        taskName = null;

        // initial selection for the datepicker
        dueDateSelection = (TextView) findViewById(R.id.selected_date_text);
        dueDateSelection.setText(DateSettings.getTodaysDateString());

        // radio buttons
        categoryButtonGroup = (RadioGroup) findViewById(R.id.radio_group);
        int i = 0;
        for (Category cat : CategoryManager.categories) {
            RadioButton rb = new RadioButton(context);
            rb.setText(cat.getCategoryName());
            rb.setId(i);
            categoryButtonGroup.addView(rb);
            i++;
        }
        categoryButtonGroup.check(0);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PickerDialogs pickerDialogs = new PickerDialogs();
                pickerDialogs.show(getSupportFragmentManager(), "date_picker");
            }
        });

    }

    public void createButtonClicked(View view) {
        taskName = taskNameInput.getText().toString().trim();
        if (taskName == null || taskName.equals("")) {
            Toast.makeText(context, "A Task Name is Required", Toast.LENGTH_LONG).show();
        } else {
            Date dueDate = DateSettings.convertStringToDate(dueDateSelection.getText().toString());
            String categoryName = ((RadioButton) findViewById(categoryButtonGroup.getCheckedRadioButtonId())).getText().toString();
            Category category = null;
            for (Category cat : CategoryManager.categories) {
                if (cat.getCategoryName().equals(categoryName)) {
                    category = cat;
                }
            }
            if (category == null) {
                System.err.println("ERROR: Category not identified");
                System.exit(0);
            }
            MainActivity.taskManager.addTask(new Task(taskName, dueDate, category));
            Toast.makeText(context, "TASK CREATED"
                    + "\n" + taskName
                    + "\n" + "Due " + dueDateSelection.getText(), Toast.LENGTH_LONG).show();
            Intent i = new Intent(view.getContext(), MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    public void editCategoriesClicked(View view) {
        Intent intent = new Intent(view.getContext(), EditCatActivity.class);
        startActivity(intent);
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

}
