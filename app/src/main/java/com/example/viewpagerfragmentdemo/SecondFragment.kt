package com.example.viewpagerfragmentdemo

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecondFragment: Fragment() {
    private var mRecyclerView: RecyclerView? = null
    lateinit var myadapter: FavListAdapter
    private lateinit var mContext:Context
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.second_fragment,container,false)
        return view
    }
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context!!
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

    }
    fun setAdapter(
        res: List<FavEntity>){

        myadapter = (this.activity as MainActivity).db?.let { FavListAdapter(res,(this.activity as MainActivity).db, object: FavListAdapter.deleteFav{
            override fun favDelete(id: Int) {
                deleteFavList(id)
            }

        }) }!!
        mRecyclerView = view?.findViewById(R.id.my_recycler_view_fav) as RecyclerView
        val mLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mRecyclerView!!.layoutManager = mLayoutManager
        mRecyclerView?.adapter=myadapter
       // myadapter!!.notifyDataSetChanged()
    }

    private fun deleteFavList(id: Int) {
        //aync task to delete from database
        DeleteFavListTask(DemoApplication.getInstance()?.getDatabase()!!).execute(id)
    }

    class DeleteFavListTask(var favDB: FavDB) : AsyncTask<Int, Unit, Unit>() {
        override fun doInBackground(vararg params: Int?) {
            favDB!!.favDao().deleteFav(params[0])
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
        }
    }
}