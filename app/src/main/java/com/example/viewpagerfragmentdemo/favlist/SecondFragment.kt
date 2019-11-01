package com.example.viewpagerfragmentdemo.favlist

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
import com.example.viewpagerfragmentdemo.DemoApplication
import com.example.viewpagerfragmentdemo.User.MainActivity
import com.example.viewpagerfragmentdemo.R
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class SecondFragment: Fragment() {
    private var mRecyclerView: RecyclerView? = null
    lateinit var myadapter: FavListAdapter
    private lateinit var mContext:Context
    var executor : Executor ?= null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.second_fragment,container,false)
        return view
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
            (this.activity as MainActivity).viewModel?.favlist()?.observe(this, Observer<List<FavEntity>> {
                if (it != null) {
                    Log.d("VVV", "in observe"+it.size)
                    setAdapter(it)
                } else {
                    Log.d("VVV", "empty")
                }
            })

        executor = Executors.newSingleThreadExecutor()

    }
    fun setAdapter(
        res: List<FavEntity>){

        myadapter = (this.activity as MainActivity).db?.let {
            FavListAdapter(
                res,
                (this.activity as MainActivity).db,
                object : FavListAdapter.updateFav {
                    override fun favUpdate(id: Int, fav: Boolean) {
                        updateFavList(fav, id)
                    }

                })
        }!!
        mRecyclerView = view?.findViewById(R.id.my_recycler_view_fav) as RecyclerView
        val mLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mRecyclerView!!.layoutManager = mLayoutManager
        mRecyclerView?.adapter=myadapter
       myadapter.notifyDataSetChanged()
    }

    private fun updateFavList(isFav:Boolean,id: Int) {
        //aync task to delete from database
        //UpdateFavListTask(DemoApplication.getInstance()?.getDatabase()!!).execute(id,isFav)
        var isFavourite = true
        if(isFav){
            isFavourite = false
        }
        executor!!.execute {

            DemoApplication.getInstance()?.getDatabase()!!.favDao().updateUserById(isFavourite,id)
        }

        if(myadapter  != null){
            myadapter.notifyDataSetChanged()
        }

    }
}