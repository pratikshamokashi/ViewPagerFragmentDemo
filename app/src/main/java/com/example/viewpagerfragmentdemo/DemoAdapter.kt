package com.example.viewpagerfragmentdemo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DemoAdapter(private var list: List<UserResponse>?, val db: FavDB): RecyclerView.Adapter<DemoAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.user_row_layout, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }
    override fun onBindViewHolder(holder: MyViewHolder, position:  Int) {
        holder.name.setText("Name:" + list?.get(position)!!.name)
        holder.username.setText("User Name: " + list?.get(position)!!.username)
        holder.email.setText("Email: " + list?.get(position)!!.email)


        holder.favImg.setOnClickListener{
            var isFavorite = false

            if (isFavorite == true) {

                    holder.favImg.setImageResource(R.drawable.ic_fav_disable)
                } else {
                    holder.favImg.setImageResource(R.drawable.ic_fav_enable)
                    Thread {
                        val favEntity = FavEntity()

                        favEntity.id = list?.get(position)!!.id!!
                        favEntity.name = list?.get(position)!!.name.toString()
                        favEntity.username = list?.get(position)!!.username.toString()
                        favEntity.email = list?.get(position)!!.email.toString()
                        favEntity.isFav = true
                        DemoApplication.getInstance()!!.getDatabase()!!.favDao().saveFavList(favEntity)

                        //Log.d("Tag","Room"+favEntity.isFav)
                    }.start()

                }
        }

    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var name: TextView
        internal var username: TextView
        internal var email: TextView
        internal val favImg: ImageView


        init {
            name = itemView.findViewById<View>(R.id.tv_name) as TextView
            username = itemView.findViewById(R.id.tv_username) as TextView
            email = itemView.findViewById(R.id.tv_email) as TextView
            favImg =itemView.findViewById(R.id.iv_fav) as ImageView

        }
    }
}