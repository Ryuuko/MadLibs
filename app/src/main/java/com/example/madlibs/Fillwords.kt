package com.example.madlibs

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_fillwords.*
import kotlinx.android.synthetic.main.activity_story.*
import java.nio.file.Files.find
import java.util.*
import kotlin.collections.ArrayList

class Fillwords : AppCompatActivity() {

    private val REQ_CODE = 123
    private val words = ArrayList<String>()
    private val wordsType = ArrayList<String>()
    private lateinit var myAdapter: ArrayAdapter<String>
    private var counter = 0 // default value of number of words
    private var storyID = 0 // default as 0 just to satisfy the requirement of Kotlin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fillwords)
        val story = intent.getStringExtra("story")
        extract(story)
    }

    fun extract(story: String){
        val builder = StringBuilder()
        // receive the story id
        this.storyID = resources.getIdentifier(story, "raw", packageName)
        val reader = Scanner(resources.openRawResource(storyID))
        while(reader.hasNextLine()){
            val line = reader.nextLine()
            builder.append(line)
        }
        var story = builder.toString()
        val r = Regex("<.*?>")
        val found = r.findAll(story)
        found.forEach{ f ->
            val m = f.value
            wordsType.add(m)
            counter++
        }
        val first_type = wordsType.get(0)
        new_words.hint = "Please enter $first_type"
        hints.text = "words left: $counter"
    }

    fun add_new_words(view: View){

        val word = new_words.text.toString()
        words.add(word)
        counter--
        new_words.setText(""); // clear the text bar whenever something is entered, recommand to be commented during debugging
        if(counter >= 1){
            val next_type = wordsType.get(wordsType.size - counter)
            new_words.hint = "Please enter $next_type"
            hints.text = "words left: $counter"
        }

        if(counter == 1)
            enter.text = "FINISH!"


        if(counter == 0){
            val myIntent = Intent(this, Story::class.java)
            myIntent.putExtra("inputs", words)
            myIntent.putExtra("storyID", storyID) // pass the story ID to Story.kt in order to read files there
            startActivityForResult(myIntent, REQ_CODE) // jump to the story page
        }
    }
}
