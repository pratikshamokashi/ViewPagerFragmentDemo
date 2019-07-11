package com.example.viewpagerfragmentdemo

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class PagerAdapter internal constructor(fm: FragmentManager?): FragmentPagerAdapter(fm) {
    private val count=3
    override fun getItem(p0: Int): Fragment? {
        var fragment:Fragment?=null
        when(p0)
        {
            0 ->fragment=FirstFragment()
            1 ->fragment=SecondFragment()
            2->fragment=ThirdFragment()
        }
        return fragment
    }

    override fun getCount(): Int {
        return count
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Tab: "+(position+1)
    }
}