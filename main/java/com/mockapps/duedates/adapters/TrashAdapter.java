package com.mockapps.duedates.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mockapps.duedates.R;
import com.mockapps.duedates.objects.Task;

import java.text.SimpleDateFormat;
import java.util.List;

public class TrashAdapter extends ArrayAdapter<Task> {

    public TrashAdapter(Context context, List<Task> tasks) {
        super(context, R.layout.trash_row, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customView = myInflater.inflate(R.layout.trash_row, parent, false);

        final Task task = getItem(position);

        TextView dateText = (TextView) customView.findViewById(R.id.trash_date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd, yyyy (EEEE)");
        dateText.setText(simpleDateFormat.format(task.getDueDate()));

        TextView nameText = (TextView) customView.findViewById(R.id.trash_name);
        nameText.setText(task.getTaskName());

        TextView catText = (TextView) customView.findViewById(R.id.trash_category);
        catText.setText(task.getCategory().getCategoryName());

        RelativeLayout layout = (RelativeLayout) customView.findViewById(R.id.trash_layout);
        TextView completedText = (TextView) customView.findViewById(R.id.trash_completed);

        if (task.isComplete()) {
            completedText.setText("Complete");
        } else {
            completedText.setText("Incomplete");
        }
        layout.setBackgroundColor(task.getCategory().getBgColor());
        completedText.setTextColor(task.getCategory().getTextColor());

        final CheckBox checkBox = (CheckBox) customView.findViewById(R.id.selected_checkbox);

        checkBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    task.setSelected(true);
                } else {
                    task.setSelected(false);
                }
            }
        });
        return customView;
    }
}
