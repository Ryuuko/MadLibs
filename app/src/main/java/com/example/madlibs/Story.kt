package com.example.madlibs

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_story.*
import java.io.BufferedReader
import java.io.PrintStream
import java.util.*
import kotlin.collections.ArrayList

class Story : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)
        val inputs = intent.getStringArrayListExtra("inputs")
        writer(inputs)
    }

    fun writer(inputs: ArrayList<String>){
        val builder = StringBuilder()
        val reader = Scanner(resources.openRawResource(R.raw.madlib1_tarzan))
        while(reader.hasNextLine()){
            val line = reader.nextLine()
            builder.append(line)
        }

        var story = builder.toString()
        // identify blanks
        val r = Regex("<.*?>")
        val blanks = r.findAll(story).map { it.value }.distinct()
        var i: Int = 0
        for(blank in blanks){
            story = story.replace(blank, inputs.get(i))
            i++
        }
        story_text.text = "$story"
    }


    fun makeNew(view: View){
        val myIntent = Intent(this, Fillwords::class.java)
        startActivity(myIntent) // jump to the story page
    }
}
