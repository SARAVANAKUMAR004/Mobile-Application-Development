package com.example.database_exp3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "student.db";
    private static final int DATABASE_VERSION = 2; // Updated version

    private static final String TABLE_NAME = "Student";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_ROLL_NO = "RollNo";
    private static final String COLUMN_MARKS = "Marks";
    private static final String COLUMN_AGE = "Age";
    private static final String COLUMN_REGISTER_NO = "RegisterNo";
    private static final String COLUMN_ADDRESS = "Address";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ROLL_NO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_MARKS + " INTEGER, "
                + COLUMN_AGE + " INTEGER, "
                + COLUMN_REGISTER_NO + " TEXT, "
                + COLUMN_ADDRESS + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + COLUMN_AGE + " INTEGER");
            db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + COLUMN_REGISTER_NO + " TEXT");
            db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + COLUMN_ADDRESS + " TEXT");
        }
    }

    public void insertStudent(String name, int marks, int age, String registerNo, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_MARKS, marks);
        values.put(COLUMN_AGE, age);
        values.put(COLUMN_REGISTER_NO, registerNo);
        values.put(COLUMN_ADDRESS, address);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void updateStudent(int rollNo, String name, int marks, int age, String registerNo, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_MARKS, marks);
        values.put(COLUMN_AGE, age);
        values.put(COLUMN_REGISTER_NO, registerNo);
        values.put(COLUMN_ADDRESS, address);
        db.update(TABLE_NAME, values, COLUMN_ROLL_NO + " = ?", new String[]{String.valueOf(rollNo)});
        db.close();
    }

    public void deleteStudent(int rollNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ROLL_NO + " = ?", new String[]{String.valueOf(rollNo)});
        db.close();
    }

    public Cursor getStudentDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public Cursor getStudentByRegisterNo(String registerNo) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_REGISTER_NO + " = ?", new String[]{registerNo});
    }
}
