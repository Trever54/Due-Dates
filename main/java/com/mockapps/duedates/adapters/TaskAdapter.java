package com.mockapps.duedates.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mockapps.duedates.R;
import com.mockapps.duedates.activities.MainActivity;
import com.mockapps.duedates.datepicker.DateSettings;
import com.mockapps.duedates.managers.TaskManager;
import com.mockapps.duedates.objects.Task;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {

    Context context;

    public TaskAdapter(Context context, List<Task> tasks) {
        super(context, R.layout.task_row, tasks);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customView = myInflater.inflate(R.layout.task_row, parent, false);

        final Task task = getItem(position);

        RelativeLayout taskLayout = (RelativeLayout) customView.findViewById(R.id.task_layout);
        taskLayout.setBackgroundColor(task.getCategory().getBgColor());

        final TextView nameText = (TextView) customView.findViewById(R.id.task_name_row_text);
        nameText.setText(task.getTaskName());
        nameText.setTextColor(task.getCategory().getTextColor());

        final TextView categoryText = (TextView) customView.findViewById(R.id.category_row_text);
        categoryText.setText(task.getCategory().getCategoryName());
        categoryText.setTextColor(task.getCategory().getTextColor());

        final CheckBox checkBox = (CheckBox) customView.findViewById(R.id.completed_checkbox);
        if (task.isComplete()) {
            checkBox.setChecked(true);
            nameText.setPaintFlags(nameText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            categoryText.setPaintFlags(categoryText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            checkBox.setChecked(false);
            nameText.setPaintFlags(nameText.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
            categoryText.setPaintFlags(categoryText.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        if ((task.getDueDate().compareTo(DateSettings.getTodaysDate()) == -1) && task.isComplete()) {
            //TODO: MOVE TO TRASH IF PAST DUE AND COMPLETE
        }
        checkBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    nameText.setPaintFlags(nameText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    categoryText.setPaintFlags(categoryText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    task.setComplete(true);
                } else {
                    nameText.setPaintFlags(nameText.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    categoryText.setPaintFlags(categoryText.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    task.setComplete(false);
                }
                if ((task.getDueDate().compareTo(DateSettings.getTodaysDate()) == -1) && task.isComplete()) {
                    // TODO: MOVE TO TRASH IF PAST DUE AND COMPLETE
                }
            }
        });

        FloatingActionButton deleteFab = (FloatingActionButton) customView.findViewById(R.id.deleteFab);
        deleteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskManager.moveTaskToTrash(task);
                Toast.makeText(view.getContext(), "Task '" + task.getTaskName() + "' was moved to the trash", Toast.LENGTH_LONG).show();
                Intent i = new Intent(context, MainActivity.class);
                context.startActivity(i);
                ((MainActivity) context).finish();
            }
        });
        return customView;
    }

}
