package com.example.viewpagerfragmentdemo

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FirstFragment: Fragment() {

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: DemoAdapter? = null
    private lateinit var mContext:Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val view = inflater.inflate(R.layout.first_fragment, container, false)
       return view
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context!!
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

       /* (this.activity as MainActivity).viewModel?.favlist()?.observe(this, Observer<List<UserEntity>> {
            if (it != null) {
                Log.d("VVV", "in observe"+it.size)
                setAdapter(it)
            } else {
                Log.d("VVV", "empty")
            }
        })*/
    }
    fun setAdapter(res:List<UserResponse>){

        Log.d("tag","adpter")
        mAdapter = (this.activity as MainActivity).db?.let { DemoAdapter(res, it) }
        mRecyclerView = view?.findViewById(R.id.my_recycler_view) as RecyclerView
        val mLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mRecyclerView!!.layoutManager = mLayoutManager
        mRecyclerView?.adapter=mAdapter
        mAdapter!!.notifyDataSetChanged()

    }


    }

