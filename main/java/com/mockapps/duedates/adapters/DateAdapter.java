package com.mockapps.duedates.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.mockapps.duedates.R;
import com.mockapps.duedates.datepicker.DateSettings;
import com.mockapps.duedates.managers.TaskManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DateAdapter extends ArrayAdapter<Date> {

    public DateAdapter(Context context, List<Date> dates) {
        super(context, R.layout.date_row, dates);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customView = myInflater.inflate(R.layout.date_row, parent, false);

        Date date = getItem(position);

        TextView dateText = (TextView) customView.findViewById(R.id.due_date_row_text);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd, yyyy (EEEE)");
        dateText.setText(simpleDateFormat.format(date));

        TextView untilDueText = (TextView) customView.findViewById(R.id.until_due_text);
        untilDueText.setText(DateSettings.findDateDistance(date));

        LinearLayout linearLayout = (LinearLayout) customView.findViewById(R.id.linear_layout);
        ListAdapter taskAdapter = new TaskAdapter(this.getContext(),
                TaskManager.taskMap.get(date));

        final int adapterCount = taskAdapter.getCount();
        for (int i = 0; i < adapterCount; i++) {
            View item = taskAdapter.getView(i, null, null);
            linearLayout.addView(item);
        }

        return customView;
    }

}
