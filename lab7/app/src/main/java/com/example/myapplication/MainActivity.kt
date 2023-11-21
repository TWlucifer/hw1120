package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    inner class Data {
        var photo = 0
        var name: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val transNameArray = arrayOf("腳踏車", "機車", "汽車", "巴士", "飛機", "輪船")
        val transPhotoIdArray = intArrayOf(
            R.drawable.trans1,
            R.drawable.trans2,
            R.drawable.trans3,
            R.drawable.trans4,
            R.drawable.trans5,
            R.drawable.trans6
        )
        val transData = arrayOfNulls<Data>(transNameArray.size)
        for (i in transData.indices) {
            transData[i] = Data()
            transData[i]!!.name = transNameArray[i]
            transData[i]!!.photo = transPhotoIdArray[i]
        }
        val transAdapter: MyAdapter = MyAdapter(transData, R.layout.trans_list)
        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.adapter = transAdapter
        val cubeeNameArray =
            arrayOf("雷電將軍", "夜蘭", "甘雨", "芙麗娜", "心海", "八重神子", "申鶴", "宵宮", "崎良良", "麗莎")
        val cubeePhotoIdArray = intArrayOf(
            R.drawable.genshin1,
            R.drawable.genshin2,
            R.drawable.genshin3,
            R.drawable.genshin4,
            R.drawable.genshin5,
            R.drawable.genshin6,
            R.drawable.genshin7,
            R.drawable.genshin8,
            R.drawable.genshin9,
            R.drawable.genshin10
        )
        val cubeeData = arrayOfNulls<Data>(cubeeNameArray.size)
        for (i in cubeeData.indices) {
            cubeeData[i] = Data()
            cubeeData[i]!!.name = cubeeNameArray[i]
            cubeeData[i]!!.photo = cubeePhotoIdArray[i]
        }
        val cubeeAdapter: MyAdapter2 = MyAdapter2(cubeeData, R.layout.cubee_list)
        val gridView = findViewById<GridView>(R.id.gridview)
        gridView.adapter = cubeeAdapter
        gridView.numColumns = 3
        val messageArray =
            arrayOf("message 1", "message 2", "message 3", "message 4", "message 5", "message 6")
        val messageAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, messageArray)
        val listView = findViewById<ListView>(R.id.listview)
        listView.adapter = messageAdapter
    }

    inner class MyAdapter(private val data: Array<Data?>, private val view: Int) : BaseAdapter() {

        override fun getCount(): Int = data.size

        override fun getItem(position: Int): Any = data[position]!!

        override fun getItemId(position: Int): Long = 0

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var convertView = convertView
            convertView = layoutInflater.inflate(view, parent, false)

            val name = convertView.findViewById<TextView>(R.id.name)
            name.text = data[position]!!.name

            val imageView = convertView.findViewById<ImageView>(R.id.imageView)
            imageView.setImageResource(data[position]!!.photo)

            return convertView
        }
    }

    inner class MyAdapter2(private val data: Array<Data?>, private val view: Int) : BaseAdapter() {

        override fun getCount(): Int = data.size

        override fun getItem(position: Int): Data = data[position]!!

        override fun getItemId(position: Int): Long = 0

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var convertView = convertView
            convertView = layoutInflater.inflate(view, parent, false)

            val name = convertView.findViewById<TextView>(R.id.tvName)
            name.text = data[position]!!.name

            val imageView = convertView.findViewById<ImageView>(R.id.imgPhoto)
            imageView.setImageResource(data[position]!!.photo)

            return convertView
        }
    }
}