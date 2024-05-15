package com.example.testepekus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "calculator.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "calculations";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_VALUE_A = "value_a";
    private static final String COLUMN_VALUE_B = "value_b";
    private static final String COLUMN_OPERATION = "operation";
    private static final String COLUMN_RESULT = "result";
    private static final String COLUMN_TIMESTAMP = "timestamp";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_VALUE_A + " REAL," +
                    COLUMN_VALUE_B + " REAL," +
                    COLUMN_OPERATION + " TEXT," +
                    COLUMN_RESULT + " REAL," +
                    COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP" +
                    ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addCalculation(double valueA, double valueB, String operation, double result) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_VALUE_A, valueA);
        values.put(COLUMN_VALUE_B, valueB);
        values.put(COLUMN_OPERATION, operation);
        values.put(COLUMN_RESULT, result);
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public List<Calculation> getAllCalculations() {
        List<Calculation> calculations = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Calculation calculation = new Calculation();
                calculation.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                calculation.setValueA(cursor.getDouble(cursor.getColumnIndex(COLUMN_VALUE_A)));
                calculation.setValueB(cursor.getDouble(cursor.getColumnIndex(COLUMN_VALUE_B)));
                calculation.setOperation(cursor.getString(cursor.getColumnIndex(COLUMN_OPERATION)));
                calculation.setResult(cursor.getDouble(cursor.getColumnIndex(COLUMN_RESULT)));
                calculation.setTimestamp(cursor.getString(cursor.getColumnIndex(COLUMN_TIMESTAMP)));
                calculations.add(calculation);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return calculations;
    }
}