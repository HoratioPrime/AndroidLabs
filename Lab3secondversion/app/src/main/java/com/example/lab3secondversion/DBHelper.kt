package com.example.lab3

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


internal class DBHelper(context: Context)// конструктор суперкласса
    : SQLiteOpenHelper(context, "studentsDB", null, 2) {

    val LOG_TAG = "myLogs"

    override fun onCreate(db: SQLiteDatabase) {
        Log.d(LOG_TAG, "--- onCreate database ---")
        // создаем таблицу с полями

        db.execSQL(("create table students ("
                + "id integer primary key autoincrement,"
                + "lastName text,"
                + "firstName text,"
                + "patronymic text,"
                + "time text" + ");"))

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion:Int, newVersion:Int) {
        Log.d(LOG_TAG, " --- onUpgrade database from " + oldVersion
                + " to " + newVersion + " version --- ");
        db.execSQL("DROP TABLE IF EXISTS students")
        onCreate(db)
    }

    fun initialize()
    {

        writableDatabase.delete("students", null, null)

        val names = mutableListOf("Samokhin Oleg Romanovich",
            "Chekhurov Denis Alexandrovich", "Parshakov Nikita Sergeevich",
            "Magomedov Murad Muradovich", "Ivanov Aleksey Alekseevich")

        val values = ContentValues()

        for (name in names) {
            values.put("lastName", name.split(" ")[0])
            values.put("firstName", name.split(" ")[1])
            values.put("patronymic", name.split(" ")[2])
            values.put("time", java.util.Calendar.getInstance().time.toString())
            writableDatabase.insert("students", null, values)
        }
    }
}