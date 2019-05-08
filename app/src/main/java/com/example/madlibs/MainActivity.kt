package com.example.madlibs

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.TextView
import android.view.ViewGroup



class MainActivity : AppCompatActivity() {

    private val REQ_CODE = 124
    val StoryArray = arrayOf("hints", "short_story", "madlib1_tarzan") // hints just represent the hint in the spinner and doesn't really exist
    var Story_title = "short_story" // the default chosen story will be the first option

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        story_menu.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if(position != 0) {
                    Story_title = StoryArray.get(position)
                } // we will do no change when the hint comes, whose position will be 0
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // do nothing
            }

            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // do nothing
            }

        }
        // getting the instance of spinner and apply OnItemSelectedListene
//        story_menu.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, StoryArray)



    }


    fun gameStartClick(view: View){
            val myIntent = Intent(this, Fillwords::class.java)
            myIntent.putExtra("story", Story_title)
            startActivityForResult(myIntent, REQ_CODE) // jump to the story page
        }

    }


