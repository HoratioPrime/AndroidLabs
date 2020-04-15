package com.example.lab2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.net.HttpURLConnection
import java.net.URL

class Adapter(private val technologies: List<Technology>): RecyclerView.Adapter<Adapter.ViewHolder>(){

    var downloadedImages = mutableMapOf<String, Bitmap?>()

    override fun getItemCount() = technologies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_view, parent, false)
        for(el in technologies)
        {
            downloadedImages.put(el.graphic, null)
        }
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView?.text = technologies[position].name
//        holder.helpTextView?.text = technologies[position].helptext

        if(downloadedImages[technologies[position].graphic] == null) {
            DownloadImagesTask(holder.imageView).execute(technologies[position].graphic)
        }else
        {
            holder.imageView?.setImageBitmap(downloadedImages[technologies[position].graphic])
        }

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var textView: TextView? = null
//        var helpTextView : TextView? = null
        var imageView : ImageView? = null
        init{
            textView = itemView.findViewById(R.id.text_list_item)
//            helpTextView = itemView.findViewById(R.id.helptext_list_item)
            imageView = itemView.findViewById(R.id.imageView)
        }
    }

    inner class DownloadImagesTask(val view: ImageView?): AsyncTask<String, Bitmap, Void>()
    {
        override fun onPreExecute() {
            super.onPreExecute()
        }
        override fun doInBackground(vararg techPath: String): Void? {

            val url = URL(
                "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/"
                        + techPath[0]
            )

            val connection = url.openConnection() as HttpURLConnection

            if (connection.responseCode == HttpURLConnection.HTTP_OK && connection.responseCode != HttpURLConnection.HTTP_NOT_FOUND) {
                downloadedImages[techPath[0]] = BitmapFactory.decodeStream(connection.inputStream)
                publishProgress(downloadedImages[techPath[0]])
            }
            connection.disconnect()

            return null
        }

        override fun onProgressUpdate(vararg values: Bitmap?) {
            super.onProgressUpdate(*values)
            if(values[0] != null) {
                view?.setImageBitmap(Bitmap.createScaledBitmap(values[0]!!, 100, 100, true))
            }
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
        }
    }
}