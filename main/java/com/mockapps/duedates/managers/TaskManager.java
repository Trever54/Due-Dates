package com.mockapps.duedates.managers;

import com.mockapps.duedates.objects.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class TaskManager {

    public static HashMap<Date, ArrayList<Task>> taskMap;
    public static ArrayList<Date> dateList;

    public TaskManager() {
        taskMap = new HashMap<Date, ArrayList<Task>>();
        dateList = new ArrayList<Date>();
    }

    public void addTask(Task task) {
        addToTaskMap(task);
        addDate(task);
    }

    public static void moveTaskToTrash(Task task) {
        Date date = task.getDueDate();
        if (dateList.contains(date)) {
            if (taskMap.get(date).size() == 1) {
                dateList.remove(date);
                taskMap.remove(date);
            } else {
                ArrayList<Task> tasks = taskMap.get(date);
                tasks.remove(task);
                taskMap.put(date, tasks);
            }
            TrashManager.addToTrash(task);
        } else {
            System.err.println("ERROR: tasks date did not exist");
            System.exit(0);
        }
    }

    public void addDate(Task task) {
        if (!dateList.contains(task.getDueDate())) {
            if (dateList.isEmpty()) {
                dateList.add(task.getDueDate());
            } else {
                boolean lessThan = false;
                boolean greaterThan = false;
                int indexPosition = -9;
                int comparison;
                Date date = task.getDueDate();
                for (int i = 0; i < dateList.size(); i++) {
                    comparison = date.compareTo(dateList.get(i));
                    // base case
                    if (comparison == -1 && !greaterThan && !lessThan) {
                        lessThan = true;
                    }
                    if (comparison == 1 && !greaterThan && !lessThan) {
                        greaterThan = true;
                    }
                    // changes from -1 to 1
                    if (comparison == -1 && greaterThan) {
                        indexPosition = i;
                        break;
                    }
                    if (comparison == 1 && lessThan) {
                        indexPosition = i;
                        break;
                    }
                    // equal date case
                    if (comparison == 0) {
                        indexPosition = i;
                        break;
                    }
                }
                // only one item in list case
                if (indexPosition == -9 && lessThan) {
                    dateList.add(0, task.getDueDate());
                }
                if (indexPosition == -9 && greaterThan){
                    dateList.add(task.getDueDate());
                }
                if (indexPosition != -9) {
                    dateList.add(indexPosition, task.getDueDate());
                }
            }
        }
    }

    public void addToTaskMap(Task task) {
        ArrayList taskList = new ArrayList<Task>();
        if (taskMap.containsKey(task.getDueDate())) {
            taskList = taskMap.get(task.getDueDate());
            taskList.add(task);
            taskMap.put(task.getDueDate(), taskList);
        } else {
            taskList.add(task);
            taskMap.put(task.getDueDate(), taskList);
        }
    }
}
