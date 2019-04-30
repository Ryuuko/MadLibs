package com.example.madlibs

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun gameStartClick(view: View){
        // enter the word fill-in page
        val myIntent = Intent(this, Fillwords::class.java)
        startActivity(myIntent)
    }
}
