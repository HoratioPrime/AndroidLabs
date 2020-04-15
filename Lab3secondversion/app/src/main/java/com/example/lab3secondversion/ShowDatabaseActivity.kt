package com.example.lab3

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
class ShowDatabaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_database)
        val LOG_TAG = "myLogs"
        val dbHelper = DBHelper(baseContext)
        val db = dbHelper.writableDatabase
        val c = db.query("students", null, null, null, null, null, null)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val dbContent = mutableListOf<Record>()
        if (c.moveToFirst())
        {
            // определяем номера столбцов по имени в выборке
            val idColIndex = c.getColumnIndex("id")
            val lastNameColIndex = c.getColumnIndex("lastName")
            val firstNameColIndex = c.getColumnIndex("firstName")
            val patronymicColIndex = c.getColumnIndex("patronymic")
            val timeColIndex = c.getColumnIndex("time")

            do
            {
                Log.d(LOG_TAG,
                    "ID = " + c.getInt(idColIndex) +
                            ", name = " + c.getString(lastNameColIndex) +
                            ", time = " + c.getString(timeColIndex)
                )
                dbContent.add(
                    Record(c.getInt(idColIndex),
                        c.getString(lastNameColIndex),
                        c.getString(firstNameColIndex),
                        c.getString(patronymicColIndex),
                        c.getString(timeColIndex)))
            }
            while (c.moveToNext())
        }
        else
            Log.d(LOG_TAG, "0 rows")
        c.close()
        Log.d(LOG_TAG, "content size = " + dbContent.size.toString())
//        dbContent.add(Record(0, "", "", "", "")) // кооостыыыыль
        recyclerView.adapter = Adapter( dbContent )
    }
}
