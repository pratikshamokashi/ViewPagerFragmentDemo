package com.example.viewpagerfragmentdemo.User

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpagerfragmentdemo.R
import com.example.viewpagerfragmentdemo.favlist.FavListAdapter
import com.example.viewpagerfragmentdemo.map.MapsActivity

class FirstFragment: Fragment(){


    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: DemoAdapter? = null
    private lateinit var mContext:Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val view = inflater.inflate(R.layout.first_fragment, container, false)
       return view
    }


    override fun onAttach(context: Context) {
        if (context != null) {
            super.onAttach(context)
        }
        mContext = context
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (this.activity as MainActivity).db
        (this.activity as MainActivity).viewModel?.userList()
        (this.activity as MainActivity).viewModel?.userlist?.observe(this, Observer<List<UserResponse>> {
            Log.d("Tag","Check1:"+ arrayOf(it))
            if (it != null)
            {
                setAdapter(it)

            }
            else{

            }
        })
    }
    fun setAdapter(res:List<UserResponse>){

        Log.d("tag","adpter")
        mAdapter = (this.activity as MainActivity).db?.let {
            DemoAdapter(res,it, object :DemoAdapter.onclickLayout {
                override fun onclick() {
                    val intent = Intent(activity,MapsActivity::class.java)
                    startActivity(intent)
                }

            })

        }
        mRecyclerView = view?.findViewById(R.id.my_recycler_view) as RecyclerView
        val mLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mRecyclerView!!.layoutManager = mLayoutManager
        mRecyclerView?.adapter=mAdapter
        mAdapter!!.notifyDataSetChanged()

    }
}

