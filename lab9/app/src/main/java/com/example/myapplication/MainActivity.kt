package com.example.myapplication

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var progressrabbit = 0
    private var progressturtle = 0
    private lateinit var btn_start: Button
    private lateinit var sb_rabbit: SeekBar
    private lateinit var sb_turtle: SeekBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 在這裡進行 UI 元素的初始化
        btn_start = findViewById(R.id.btn_start)
        sb_rabbit = findViewById(R.id.sb_rabbit)
        sb_turtle = findViewById(R.id.sb_turtle)

        btn_start.setOnClickListener(View.OnClickListener {
            btn_start.isEnabled = false
            progressrabbit = 0
            progressturtle = 0
            sb_rabbit.progress = 0
            sb_turtle.progress = 0
            runRabbit()
            runTurtle()
        })
    }


    private val handler = Handler(Looper.myLooper()!!) { message ->
        if (message.what == 1) sb_rabbit!!.progress =
            progressrabbit else if (message.what == 2) sb_turtle!!.progress =
            progressturtle
        if (progressrabbit >= 100 && progressturtle < 100) {
            Toast.makeText(this@MainActivity, "兔子勝利", Toast.LENGTH_SHORT).show()
            btn_start!!.isEnabled = true
        } else if (progressturtle >= 100 && progressrabbit < 100) {
            Toast.makeText(this@MainActivity, "烏龜勝利", Toast.LENGTH_SHORT).show()
            btn_start!!.isEnabled = true
        }
        false
    }

    private fun runRabbit() {
        Thread {
            val sleepProbability = booleanArrayOf(true, true, false)
            while (progressrabbit <= 100 && progressturtle < 100) {
                try {
                    Thread.sleep(100)
                    if (sleepProbability[(Math.random() * 3).toInt()]) Thread.sleep(
                        300
                    )
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                progressrabbit += 3
                val msg = Message()
                msg.what = 1
                handler.sendMessage(msg)
            }
        }.start()
    }

    private fun runTurtle() {
        Thread {
            while (progressturtle <= 100 && progressrabbit < 100) {
                try {
                    Thread.sleep(100)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                progressturtle += 1
                val msg = Message()
                msg.what = 2
                handler.sendMessage(msg)
            }
        }.start()
    }
}