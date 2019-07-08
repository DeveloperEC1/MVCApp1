package com.elior.mvcapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class MVCController {

    private MVCSQLite dataBase;
    private ArrayList<String> tasks;

    public MVCController(Context app_context) {
        tasks = new ArrayList<String>();
        dataBase = new MVCSQLite(app_context);
    }

    public void addTask(final String title) {
        final ContentValues data = new ContentValues();
        data.put("title", title);
        dataBase.addTask(data);
    }

    public void deleteTask(final String title) {
        dataBase.deleteTask("title='" + title + "'");
    }

    public void deleteTask(final long id) {
        dataBase.deleteTask("id='" + id + "'");
    }

    public void deleteAllTask() {
        dataBase.deleteTask(null);
    }

    public ArrayList<String> getTasks() {
        Cursor c = dataBase.loadAllTasks();
        tasks.clear();
        if (c != null) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                tasks.add(c.getString(0));
                c.moveToNext();
            }
            c.close();
        }
        return tasks;
    }

}
