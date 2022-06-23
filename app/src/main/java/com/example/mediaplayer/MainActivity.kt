package com.example.mediaplayer

import android.content.ContentValues.TAG
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MediaPlayer.OnPreparedListener {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {

        var httpUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-9.mp3"

        btn_play.setOnClickListener {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer()
//                mediaPlayer!!.setDataSource(httpUrl)
//                mediaPlayer!!.setOnPreparedListener(this)
//                mediaPlayer!!.prepareAsync()
                mediaPlayer = MediaPlayer.create(this,R.raw.mot)
                mediaPlayer?.start()
            }
        }

        btn_resume.setOnClickListener {
            if (!mediaPlayer!!.isPlaying) mediaPlayer!!.start()
        }

        btn_pause.setOnClickListener {
            if (mediaPlayer!!.isPlaying) {
                mediaPlayer!!.pause()
            }
        }

        btn_stop.setOnClickListener {
            if (mediaPlayer!!.isPlaying) {
                mediaPlayer!!.stop()
            }
        }

        btn_backward.setOnClickListener {
            mediaPlayer!!.seekTo(mediaPlayer!!.currentPosition - 3000)
            Log.d(TAG, "currentPosition" + mediaPlayer!!.currentPosition.toString())
        }

        btn_forward.setOnClickListener {
            mediaPlayer!!.seekTo(mediaPlayer!!.currentPosition.plus(3000))
            Log.d(TAG, "currentPosition" + mediaPlayer!!.currentPosition.toString())
        }
    }

    private fun releaseMP() {
        if (mediaPlayer != null) {
           try {
               mediaPlayer?.release()
               mediaPlayer = null
           }catch (e: Exception){
               e.printStackTrace()
           }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseMP()
    }

    override fun onPrepared(p0: MediaPlayer?) {
        p0?.start()

    }
}