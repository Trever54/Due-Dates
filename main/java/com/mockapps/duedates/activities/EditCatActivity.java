package com.mockapps.duedates.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.mockapps.duedates.R;
import com.mockapps.duedates.adapters.EditCategoryAdapter;
import com.mockapps.duedates.managers.CategoryManager;
import com.mockapps.duedates.objects.Category;

public class EditCatActivity extends AppCompatActivity {

    private EditText newCategory;
    private String newCategoryName;
    private Button addButton;
    private Button colorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cat);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
        ListAdapter editCategoryAdapter = new EditCategoryAdapter(this, CategoryManager.categories);

        final int adapterCount = editCategoryAdapter.getCount();
        for (int i = 0; i < adapterCount; i++) {
            View item = editCategoryAdapter.getView(i, null, null);
            linearLayout.addView(item);
        }

        newCategory = (EditText) findViewById(R.id.cat_edit_text);
        newCategoryName = null;
        addButton = (Button) findViewById(R.id.add_button);
        colorButton = (Button) findViewById(R.id.edit_color_button);

        if (CategoryManager.categoriesFull()) {
            newCategory.setVisibility(View.INVISIBLE);
            addButton.setVisibility(View.INVISIBLE);
            colorButton.setVisibility(View.INVISIBLE);
        } else {
            newCategory.setVisibility(View.VISIBLE);
            addButton.setVisibility(View.VISIBLE);
            colorButton.setVisibility(View.VISIBLE);
        }

    }

    public void addButtonClicked(View view) {
        if (CategoryManager.categoriesFull()) {
            return;
        }
        newCategoryName = newCategory.getText().toString().trim();
        if (newCategoryName == null || newCategoryName.equals("")) {
            Toast.makeText(this, "The Text Field is Empty", Toast.LENGTH_LONG).show();
        } else {
            CategoryManager.addCategory(new Category(newCategoryName, Color.BLACK, Color.CYAN));
            Toast.makeText(this, "Category '" + newCategoryName + "' was added", Toast.LENGTH_LONG).show();
            Intent i = new Intent(view.getContext(), EditCatActivity.class);
            startActivity(i);
            finish();
        }
    }

    public void backButtonClicked(View view) {
        Intent i = new Intent(view.getContext(), AddTaskActivity.class);
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
}
