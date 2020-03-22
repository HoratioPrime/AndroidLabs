package com.example.androiddev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
    }

    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_main)

        Thread() {
            try {
                Thread.sleep((2000).toLong())
                val i = Intent(baseContext, SecondActivity::class.java)
                startActivity(i)
                finish()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }

}


