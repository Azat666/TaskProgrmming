package com.example.student.notifcationapptask.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.student.notifcationapptask.models.ModelTasks;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "notifications.db";
    private static final String TABLE_NAME = "userTask";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_ISCHEKID = "ischekid";
    public static  DbHelper db = null;

    private static final String CREATE_COMMAND =

            " CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_NAME + " TEXT, " + COLUMN_DATE + " TEXT, " + COLUMN_TIME + " TEXT, " +
                    COLUMN_IMAGE + " INTEGER, " + COLUMN_ISCHEKID + " INTEGER)";

    private static final String DROP_COMMAND = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_COMMAND);
        onCreate(sqLiteDatabase);
    }

    public long insertItem(ModelTasks modelTasks) {
        final SQLiteDatabase db = this.getReadableDatabase();
        final ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, modelTasks.getName());
        values.put(COLUMN_DATE, modelTasks.getDate());
        values.put(COLUMN_TIME, modelTasks.getTime());
        values.put(COLUMN_IMAGE, modelTasks.getImage());
        values.put(COLUMN_ISCHEKID, modelTasks.isIschekid());
        return db.insert(TABLE_NAME, null, values);
    }

    public ModelTasks getItem(int id) {
        final SQLiteDatabase db = this.getReadableDatabase();
        final String query = "SELECT * FROM " + TABLE_NAME;

        final Cursor cursor = db.rawQuery(query, null);
        if (cursor.isBeforeFirst()) {
            cursor.moveToFirst();
        }

        cursor.move(id);
        final String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        final String date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE));
        final String time = cursor.getString(cursor.getColumnIndex(COLUMN_TIME));
        final int image = cursor.getInt(cursor.getColumnIndex(COLUMN_IMAGE));
        final int ischekid = cursor.getInt(cursor.getColumnIndex(COLUMN_ISCHEKID));
        return new ModelTasks(name, time, date, image, ischekid);
    }

    public int getTaskCount() {
        final String query = "SELECT * FROM " + TABLE_NAME;
        final SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.rawQuery(query, null);
        final int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public static DbHelper getInstance(Context context) {
        if (db == null) {
            db = new DbHelper(context);
        }
        return db;
    }

    public void removeTask(String name) {
        final SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_NAME, " name = " + "'" + name + "'", null);
    }

    public void removeAll() {
        final SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_NAME, null, null);
    }

}
