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
    private var counter = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fillwords)
        extract()
    }

    fun extract(){
        val builder = StringBuilder()
        val reader = Scanner(resources.openRawResource(R.raw.madlib1_tarzan))
        while(reader.hasNextLine()){
            val line = reader.nextLine()
            builder.append(line)
        }
        var story = builder.toString()
        val r = Regex("<.*?>")
        while(r.find(story)!=null){
            wordsType.add(r.find(story).value)
        }
    }

    fun add_new_words(view: View){
        val word = new_words.text.toString()
        words.add(word)
        counter--
        hints.text = "words left: $counter"
        if(counter == 1)
            enter.text = "FINISH!"
        if(counter == 0){
            val myIntent = Intent(this, Story::class.java)
            myIntent.putExtra("inputs", words)
            startActivityForResult(myIntent, REQ_CODE) // jump to the story page
        }
    }
}
