package com.elior.mvcapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

final class MVCSQLite {

    private static final String DB_NAME = "tasks_db";
    private static final String TABLE_NAME = "task";
    private static final int DB_VERSION = 1;
    private static final String DB_CREATE_QUERY = "CREATE TABLE " +
            MVCSQLite.TABLE_NAME + " (id integer primary key autoincrement, title text not null);";
    private final SQLiteDatabase database;
    private final SQLiteOpenHelper helper;

    public MVCSQLite(final Context ctx) {
        helper = new SQLiteOpenHelper(ctx, MVCSQLite.DB_NAME, null, MVCSQLite.DB_VERSION) {
            @Override
            public void onCreate(final SQLiteDatabase db) {
                db.execSQL(MVCSQLite.DB_CREATE_QUERY);
            }

            @Override
            public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + MVCSQLite.TABLE_NAME);
                onCreate(db);
            }
        };
        database = helper.getWritableDatabase();
    }

    public void addTask(ContentValues data) {
        database.insert(MVCSQLite.TABLE_NAME, null, data);
    }

    public void deleteTask(String field_params) {
        database.delete(MVCSQLite.TABLE_NAME, field_params, null);
    }

    public Cursor loadAllTasks() {
        final Cursor c = database.query(MVCSQLite.TABLE_NAME, new String[]{"title"}, null, null, null, null, null);
        return c;
    }

}
