package com.example.appprueba;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConectDB {
    protected SQLiteDatabase db;
    private DBHelper dbHelper;

    public ConectDB(Context context) {
        dbHelper = new ConectDB.DBHelper(context);
    }

    protected void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    protected void openWritableDB()     {
        db = dbHelper.getWritableDatabase();
    }

    protected void closeDB() {
        if (db != null) {
            db.close();
        }
    }

    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, ConstansDB.DB_NAME, null, ConstansDB.DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(ConstansDB.TABLA_MATERIAS_SQL);
            db.execSQL(ConstansDB.TABLA_ALUMNO_SQL);
            db.execSQL(ConstansDB.TABLA_ALUMNO_MATERIA_SQL);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
