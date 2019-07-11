package com.example.viewpagerfragmentdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewPager =findViewById<ViewPager>(R.id.viewPager)
        if(viewPager!=null)
        {
            val adapter=PagerAdapter(supportFragmentManager)
            viewPager.adapter=adapter
        }
    }
}
