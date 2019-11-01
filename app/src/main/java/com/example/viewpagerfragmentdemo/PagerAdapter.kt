package com.example.viewpagerfragmentdemo

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.viewpagerfragmentdemo.User.FirstFragment
import com.example.viewpagerfragmentdemo.favlist.SecondFragment


class PagerAdapter(
    private val myContext: Context,
    fm: FragmentManager,
    internal var totalTabs: Int): FragmentPagerAdapter(fm) {
  //  private val count=3
    override fun getItem(p0: Int): Fragment {
      var fragment: Fragment? = null
        when(p0)
        {
            0 ->fragment= FirstFragment()
            1 ->fragment= SecondFragment()
            2->fragment=ThirdFragment()
        }
        return fragment!!
    }

    override fun getCount(): Int {
        return totalTabs
    }

    /*override fun getPageTitle(position: Int): CharSequence? {
        return "Tab: "+(position+1)
    }*/
}