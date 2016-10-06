package com.mockapps.duedates.datepicker;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.Toast;

import com.mockapps.duedates.activities.AddTaskActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateSettings implements DatePickerDialog.OnDateSetListener {

    Context context;
    public DateSettings(Context context) {
        this.context = context;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        String dateString = createDateString(year, monthOfYear, dayOfMonth);
        AddTaskActivity.dueDateSelection.setText(dateString);
        Toast.makeText(context, "Selected Date: " + dateString, Toast.LENGTH_LONG).show();
    }

    /**
     * returns a String of the distance between todays date and the
     * passed parameter date
     */
    public static String findDateDistance(Date date) {
        String result = "";
        Date todaysDate = getTodaysDate();
        if (date.compareTo(todaysDate) == 0) {
            result = "DUE TODAY";
        } else if (date.compareTo(todaysDate) == -1) {
            result = "PAST DUE";
        }
        return result;
    }

    /**
     * returns a Date object for todays date
     */
    public static Date getTodaysDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return convertStringToDate(DateSettings.createDateString(year, month, day));
    }

    public static String getTodaysDateString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd, yyyy (EEEE)");
        Date date = getTodaysDate();
        return simpleDateFormat.format(date);
    }

    /**
     * Takes a year, month, and day and returns a String in the following form:
     * "January 12, 2016 (Saturday)"
     */
    public static String createDateString(int year, int monthOfYear, int dayOfMonth) {
        String dateString = "";
        switch (monthOfYear) {
            case 0: dateString = "January"; break;
            case 1: dateString = "February"; break;
            case 2: dateString = "March"; break;
            case 3: dateString = "April"; break;
            case 4: dateString = "May"; break;
            case 5: dateString = "June"; break;
            case 6: dateString = "July"; break;
            case 7: dateString = "August"; break;
            case 8: dateString = "September"; break;
            case 9: dateString = "October"; break;
            case 10: dateString = "November"; break;
            case 11: dateString = "December"; break;
            default: dateString = "ERROR";
        }
        dateString = dateString + " " + dayOfMonth + ", " + year;
        monthOfYear += 1;
        String dow = "";
        String inputDate = dayOfMonth + "/" + monthOfYear + "/" + year;
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date myDate = newDateFormat.parse(inputDate);
            newDateFormat.applyPattern("EEEE");
            dow = newDateFormat.format(myDate);
        } catch (Exception ParseException) {
            System.err.println("ERROR: Could not parse date for day of week");
            System.exit(0);
        }
        dateString = dateString + " (" + dow + ")";
        return dateString;
    }

    /**
     * Converts a string of the following format: "January 12, 2016 (Saturday)"
     * to a Date object.
     */
    public static Date convertStringToDate(String s) {
        Date myDate = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd, yyyy (EEEE)");
        try {
            myDate = simpleDateFormat.parse(s);
        } catch (Exception ParseException) {
            System.err.println("ERROR: Could not parse date in conversion of string to date");
            System.exit(0);
        }
        return myDate;
    }
}
