package com.example.broadcastexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.ResultReceiver
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

//    var start:Button?= null
    lateinit var start:Button
    lateinit var stop:Button

    var songList = mutableListOf<String>()

    private var resultReceiver = MyDownloadResultReceiver(null)

    companion object
    {
        var mHandler = Handler(Looper.getMainLooper())

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start = findViewById(R.id.start)
        stop = findViewById(R.id.stop)
        prepareList()
        start.setOnClickListener{
//            Log.d("service","${songList.size}")
            sendData()
        }

        this@MainActivity.runOnUiThread(object : Runnable {
            override fun run() {

                Log.d("", "run: ")
            }
        })
        stop.setOnClickListener {
            Intent(this,CustomService::class.java)
                .also {intent ->
                    stopService(intent)
                }

        }
    }

    private fun sendData() {
        songList.forEach{
//            val i = Intent(this,MyService::class.java)
             val i = Intent(this,CustomService::class.java)
            i.putExtra("song",it)

            i.putExtra(Intent.EXTRA_RESULT_RECEIVER , resultReceiver)
            startService(i)
        }
    }

    private fun prepareList():MutableList<String> {
        songList.add("Dil Dil")
        songList.add("Pakistan")
        songList.add("fsdsds")
        songList.add("dsdsds")
        songList.add("testing song")

        return songList
    }
}