package com.mockapps.duedates.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mockapps.duedates.R;
import com.mockapps.duedates.activities.EditCatActivity;
import com.mockapps.duedates.activities.MainActivity;
import com.mockapps.duedates.managers.CategoryManager;
import com.mockapps.duedates.objects.Category;
import com.mockapps.duedates.objects.Task;

import java.util.List;

public class EditCategoryAdapter extends ArrayAdapter<Category> {

    Context context;

    public EditCategoryAdapter(Context context, List<Category> categories) {
        super(context, R.layout.edit_cat_row, categories);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customView = myInflater.inflate(R.layout.edit_cat_row, parent, false);

        final Category cat = getItem(position);
        TextView nameText = (TextView) customView.findViewById(R.id.category_name_text);
        nameText.setText(cat.getCategoryName());

        // set colors
        nameText.setTextColor(cat.getTextColor());
        RelativeLayout layout = (RelativeLayout) customView.findViewById(R.id.relative_layout);
        layout.setBackgroundColor(cat.getBgColor());


        Button removeButton = (Button) customView.findViewById(R.id.remove_button);
        removeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (CategoryManager.categories.size() == 1) {
                    Toast.makeText(v.getContext(), "Could not remove: \nThere must be at least 1 category.", Toast.LENGTH_LONG).show();
                } else {
                    CategoryManager.removeCategory(cat);
                    Toast.makeText(v.getContext(), "Category '" + cat.getCategoryName() + "' was removed", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(context, EditCatActivity.class);
                    context.startActivity(i);
                    ((EditCatActivity) context).finish();
                }
            }
        });

        return customView;
    }
}
