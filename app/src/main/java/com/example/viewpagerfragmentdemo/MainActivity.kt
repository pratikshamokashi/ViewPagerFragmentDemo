package com.example.viewpagerfragmentdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var viewModel: UserViewModel ?= null
    var db :FavDB ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         db = Room.databaseBuilder(this, FavDB::class.java,"favdb.db").build()

        viewModel = ViewModelProviders.of(this, UserViewModel.Factory(this)).get(UserViewModel::class.java)

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        tabLayout!!.addTab(tabLayout.newTab().setText("User"))
        tabLayout.addTab(tabLayout.newTab().setText("Fav"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        if(viewPager!=null)
        {
            val adapter= PagerAdapter(this, supportFragmentManager, tabLayout.tabCount)
            viewPager.adapter=adapter
        }

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })



    }
}
