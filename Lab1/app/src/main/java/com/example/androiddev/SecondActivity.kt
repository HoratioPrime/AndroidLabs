package com.example.androiddev

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = Adapter(generateValues())
    }

    private fun generateValues():List<String>{
        val values = mutableListOf<String>()
            for(i in 1..1000000){
            values.add("$i element")
        }
        return values
    }


    class Adapter(private val values: List<String>): RecyclerView.Adapter<Adapter.ViewHolder>(){

        override fun getItemCount() = values.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_view, parent, false)
            return ViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder?.textView?.text = convertToString( (position+1).toString() )


            if(position % 2 == 1) {
                holder.itemView.setBackgroundColor(Color.GRAY)
            }else {
                holder.itemView.setBackgroundColor(Color.WHITE)
            }
        }

        class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            var textView: TextView? = null
            init{
                textView = itemView?.findViewById(R.id.text_list_item)
            }
        }

        val words = arrayOf(
            "один", "два",  "три", "четыре", "пять", "шесть", "семь", "восемь", "девять",
            "десять", "одиннадцать", "двенадцать", "тринадцать", "четыренадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать",
            "двадцать", "тридцать", "сорок", "пятьдесят", "шестьседяст", "семьдесят", "восемьдесят", "девяносто",
            "сто",  "двести", "триста", "четреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот",
            "тысяча",
            "одна", "две", "тысяч", "тысячи", "миллион"
        )
        public fun convertToString(number: String) : String
        {
            val length = number.length
            val firstLetter = number[0].toString().toInt()
            //println(firstLetter)
            if(firstLetter != 0) {
                return  when (length) {
                    7 -> {
                        words[0] + words[41]
                    }

                    6 -> {
                        convertToString(number[0] + "00") + " " + convertToString(number.substring(1))
                    }

                    5 -> {
                        convertToString(number[0] + "0") + " " + convertToString(number.substring(1))
                    }

                    4 -> {
                        if (firstLetter == 1) {
                            words[37] + " " + words[36] + " " + convertToString(number.substring(1))
                        } else if (firstLetter < 5) {
                            if (firstLetter == 2) {
                                words[38] + " " + words[40] + " " + convertToString(number.substring(1))
                            } else {
                                words[firstLetter - 1] + " " + words[40] + " " + convertToString(number.substring(1))
                            }
                        } else {
                            words[firstLetter - 1] + " " + words[39] + " " + convertToString(number.substring(1))
                        }
                    }

                    3 -> {
                        words[firstLetter + 26] + " " + convertToString(number.substring(1))
                    }

                    2 -> {
                        if (firstLetter == 1) {
                            words[number.toInt() - 1]
                        } else {
                            words[firstLetter + 17] + " " + convertToString(number.substring(1))
                        }
                    }
                    1 -> {
                        words[number.toInt() - 1]
                    }
                    else -> {
                        ""
                    }
                }
            }else if(length == 1) {
                return ""
            }else {
                return convertToString(number.substring(1))
            }
        }




    }



}


