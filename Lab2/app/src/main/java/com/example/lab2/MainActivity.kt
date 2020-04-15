package com.example.lab2

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }

    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_main)
        val url = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/data/techs.ruleset.json"
        AsyncTaskHandler().execute(url)

    }

    inner class AsyncTaskHandler:AsyncTask<String, String, String>()
    {
        override fun onPreExecute() {
            super.onPreExecute()
        }
        override fun doInBackground(vararg url: String?): String {
            val res:String
            val connection = URL(url[0]).openConnection() as HttpURLConnection
            try {
                connection.connect()
                res = connection.inputStream.use {it.reader().use {reader->reader.readText()}}
            }
            finally {
                connection.disconnect()
            }
            return res
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            jsonResult(result)
        }

        private fun jsonResult(jsonString: String?)
        {
            val jsonArray = JSONArray(jsonString)
            val technologyList = ArrayList<Technology>()

            var i = 1
            while(i < jsonArray.length())
            {
                val jsonObject = jsonArray.getJSONObject(i)
                val image = jsonObject.getString("graphic")
                val name = jsonObject.getString("name")
                val helpText : String
                try{
                     helpText = jsonObject.getString("helptext")
                    technologyList.add(
                        Technology(
                            image,
                            name,
                            helpText
                        )
                    )
                }
                catch (e:Exception)
                {
                    technologyList.add(
                        Technology(
                            image,
                            name
                        )
                    )
                }
                i++
            }
            val intent = Intent(baseContext, SecondActivity::class.java)
            intent.putExtra("techList", technologyList)
            startActivity(intent)
        }
    }

}