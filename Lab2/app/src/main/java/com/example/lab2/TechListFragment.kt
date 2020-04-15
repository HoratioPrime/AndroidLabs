//package com.example.lab2
//
//import android.content.Context
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.os.AsyncTask
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.*
//import androidx.fragment.app.ListFragment
//import androidx.recyclerview.widget.RecyclerView
//import java.net.HttpURLConnection
//
//class TechListFragment : ListFragment(){
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//    }
//
//    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
//        super.onListItemClick(l, v, position, id)
//    }
//
//    class Adapter(context : Context, resource: Int,  technologies: List<Technology>): ArrayAdapter<Technology>(context, resource, technologies){
//
//        var downloadedImages = mutableMapOf<String, Bitmap?>()
//
//
//        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//            var view = convertView
//            val tech = getItem(position)
//            if(view == null){
//                view = LayoutInflater.from(context).inflate(R.layout.list_item_view, null)
//            }
//            (view?.findViewById(R.id.text_list_item) as TextView).text = tech?.name
//
//
//
//        }
//
//
//
//
//
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_view, parent, false)
//            for(el in technologies)
//            {
//                downloadedImages.put(el.graphic, null)
//            }
//            return ViewHolder(itemView)
//        }
//
//        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//            holder.textView?.text = technologies[position].name
////        holder.helpTextView?.text = technologies[position].helptext
//
//            if(downloadedImages[technologies[position].graphic] == null) {
//                DownloadImagesTask(holder.imageView).execute(technologies[position].graphic)
//            }else
//            {
//                holder.imageView?.setImageBitmap(downloadedImages[technologies[position].graphic])
//            }
//
//        }
//
//        class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
//            var textView: TextView? = null
//            //        var helpTextView : TextView? = null
//            var imageView : ImageView? = null
//            init{
//                textView = itemView.findViewById(R.id.text_list_item)
////            helpTextView = itemView.findViewById(R.id.helptext_list_item)
//                imageView = itemView.findViewById(R.id.imageView)
//            }
//        }
//
//        inner class DownloadImagesTask(val view: ImageView?): AsyncTask<String, Bitmap, Void>()
//        {
//            override fun onPreExecute() {
//                super.onPreExecute()
//            }
//            override fun doInBackground(vararg techPath: String): Void? {
//
//                val url = URL(
//                    "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/"
//                            + techPath[0]
//                )
//
//                val connection = url.openConnection() as HttpURLConnection
//
//                if (connection.responseCode == HttpURLConnection.HTTP_OK && connection.responseCode != HttpURLConnection.HTTP_NOT_FOUND) {
//                    downloadedImages[techPath[0]] = BitmapFactory.decodeStream(connection.inputStream)
//                    publishProgress(downloadedImages[techPath[0]])
//                }
//                connection.disconnect()
//
//                return null
//            }
//
//            override fun onProgressUpdate(vararg values: Bitmap?) {
//                super.onProgressUpdate(*values)
//                if(values[0] != null) {
//                    view?.setImageBitmap(Bitmap.createScaledBitmap(values[0]!!, 100, 100, true))
//                }
//            }
//
//            override fun onPostExecute(result: Void?) {
//                super.onPostExecute(result)
//            }
//        }
//    }
//
//
//}
//
