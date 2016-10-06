package com.mockapps.duedates.objects;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {

    private String taskName;
    private Date dueDate;
    private Category category;
    private boolean complete;
    private boolean selected;

    public Task(String taskName, Date dueDate, Category category) {
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.category = category;
        this.complete = false;
        this.selected = false;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getTaskName() {
        return taskName;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isComplete() {
        return complete;
    }

    public boolean isSelected() {
        return selected;
    }

    public String toString() {
        return "Task Name: " + this.taskName + "| Due Date: " + this.dueDate.toString();
    }

}
