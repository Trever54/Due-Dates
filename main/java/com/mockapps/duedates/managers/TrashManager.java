package com.mockapps.duedates.managers;

import com.mockapps.duedates.objects.Task;

import java.util.ArrayList;
import java.util.List;

public class TrashManager {

    public static ArrayList<Task> trashList;

    public TrashManager() {
        trashList = new ArrayList<Task>();
    }

    public static void addToTrash(Task task) {
        trashList.add(0, task);
    }

    public static void clearAll() {
        trashList.clear();
    }

    public static void clearSelected(List<Task> tasks) {
        for (Task task : tasks) {
            if (trashList.contains(task)) {
                trashList.remove(task);
            }
        }
    }

}
