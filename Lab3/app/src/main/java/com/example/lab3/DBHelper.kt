package com.example.lab3

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


internal class DBHelper(context: Context)// конструктор суперкласса
    : SQLiteOpenHelper(context, "studentsDB", null, 1) {

    val LOG_TAG = "myLogs"

    override fun onCreate(db: SQLiteDatabase) {
        Log.d(LOG_TAG, "--- onCreate database ---")
        // создаем таблицу с полями

        db.execSQL(("create table students ("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "time text" + ");"))

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion:Int, newVersion:Int) {

    }
    fun initialize()
    {

        writableDatabase.delete("students", null, null)

        val names = mutableListOf("Samokhin Oleg Romanovich",
            "Chekhurov Denis Alexandrovich", "Parshakov Nikita Sergeevich",
            "Magomedov Murad Muradovich", "Ivanov Aleksey Alekseevich")
        val values = ContentValues()
        for (name in names) {
            values.put("name", name)
            values.put("time", java.util.Calendar.getInstance().time.toString())
            writableDatabase.insert("students", null, values)
        }
    }
}