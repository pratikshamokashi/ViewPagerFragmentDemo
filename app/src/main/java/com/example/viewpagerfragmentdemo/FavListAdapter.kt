package com.example.viewpagerfragmentdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FavListAdapter(
    private var mList: List<FavEntity>,
    var db: FavDB?,
    var param: deleteFav
) : RecyclerView.Adapter<FavListAdapter.MyViewHolder>()
    {
        lateinit var mdeleteFav: deleteFav

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
          //  val viewHolder :MyViewHolder
            val view = LayoutInflater.from(parent?.context).inflate(R.layout.fav_row_layout, parent, false)
            this.mdeleteFav = param
            return MyViewHolder(view)
        }
        override fun getItemCount(): Int {
            return mList.size
        }
        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val mFavList = mList[position]
            holder.name.setText(mFavList.name)
            holder.username.setText(mFavList.username)
            holder.email.setText(mFavList.email)
            holder.favImg.setImageResource(R.drawable.ic_fav_enable)

            holder.favImg.setOnClickListener(){
                mdeleteFav.favDelete(mList.get(position).id)
                //mFavList.isFav =false
               /* if (mFavList.isFav == false){
                    DemoApplication.getInstance()!!.getDatabase()!!.favDao().updateUserById(true,mList.get(position).id)

                }
                else{
                    DemoApplication.getInstance()!!.getDatabase()!!.favDao().updateUserById(false,mList.get(position).id)

                }*/
            }

        }


            inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                internal var name: TextView
                internal var username: TextView
                internal var email: TextView
                internal val favImg: ImageView
                internal var favRow :LinearLayout


                init {
                    name = itemView.findViewById<View>(R.id.tv_name) as TextView
                    username = itemView.findViewById(R.id.tv_username) as TextView
                    email = itemView.findViewById(R.id.tv_email) as TextView
                    favImg = itemView.findViewById(R.id.iv_fav) as ImageView
                    favRow = itemView.findViewById(R.id.fav_row) as LinearLayout

                }
            }
        interface deleteFav{
            fun favDelete(id :Int)
        }
        }