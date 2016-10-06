package com.mockapps.duedates.managers;

import android.graphics.Color;

import com.mockapps.duedates.objects.Category;

import java.util.ArrayList;

public class CategoryManager {

    public static ArrayList<Category> categories;

    public CategoryManager() {
        categories = new ArrayList<Category>();

        // TODO: method to delete later. Currently used to test a full list of categories
        addMockCategories();
    }

    public static void addCategory(Category category) {
        categories.add(category);
    }

    public static void removeCategory(Category category) {
        if (categories.contains(category)) {
            categories.remove(category);
            categories.trimToSize();
        } else {
            System.err.println("ERROR: category did not exist");
            System.exit(0);
        }
    }

    /**
     * if categories are full (size >=6) return true; otherwise false
     */
    public static boolean categoriesFull() {
        if (categories.size() >= 6) {
            return true;
        }
        return false;
    }

    public void addMockCategories() {
        categories.add(new Category("General", Color.GREEN, Color.WHITE));
        categories.add(new Category("MTH2140 - Computational Matrix Algebra", Color.LTGRAY, Color.WHITE));
        categories.add(new Category("CS3600 - Operating Systems", Color.BLUE, Color.WHITE));
        categories.add(new Category("CS4250 - Software Engineering Principle", Color.DKGRAY, Color.WHITE));
        categories.add(new Category("MTH4150 - Elementary Number Theory", Color.RED, Color.WHITE));
        categories.add(new Category("ANT2330 - Cross-Cultural Communication", Color.MAGENTA, Color.WHITE));
    }

}
