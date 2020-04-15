package com.example.lab3

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText

class MainActivity:Activity(), OnClickListener {

    internal val LOG_TAG = "myLogs"


/* Called when the activity is first created.  */
    public override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    (findViewById(R.id.btnShow) as Button).setOnClickListener(this)

    (findViewById(R.id.btnAdd) as Button).setOnClickListener(this)

    (findViewById(R.id.btnUpdate) as Button).setOnClickListener(this)
        val dbHelper = DBHelper(this)
        dbHelper.initialize()
    }


     override fun onClick(v: View) {

     // создаем объект для данных
         val cv = ContentValues()

     // получаем данные из полей ввода
         val etName = findViewById(R.id.etName) as EditText
         val fullName = etName.text.toString()

     // подключаемся к БД
         val dbHelper = DBHelper(this)
         val db = dbHelper.writableDatabase

        when (v.getId()) {
            R.id.btnShow -> {
                Log.d(LOG_TAG, "--- Rows in students: ---")
                intent = Intent(baseContext, ShowDatabaseActivity::class.java)
                startActivity(intent)
            }
            R.id.btnAdd -> {
                Log.d(LOG_TAG, "--- Insert in students: ---")
                // подготовим данные для вставки в виде пар: наименование столбца - значение
                val dividedFullName = fullName.split(" ")
                cv.put("lastName", dividedFullName[0])
                cv.put("firstName", dividedFullName[1])
                cv.put("patronymic", dividedFullName[2])
                cv.put("time", java.util.Calendar.getInstance().time.toString())
                // вставляем запись и получаем ее ID
                val rowID = db.insert("students", null, cv)
                Log.d(LOG_TAG, "row inserted, ID = $rowID")
            }
            R.id.btnUpdate -> {
                Log.d(LOG_TAG, "--- Update students: ---")
                 // удаляем все записи
                cv.put("lastName", "Ivanov")
                cv.put("firstName", "Ivan")
                cv.put("patronymic", "Ivanovich")
                db.update("students", cv, "id = (SELECT MAX(id) FROM students)", null)
            }
        }
     // закрываем подключение к БД
        dbHelper.close()
    }


}
