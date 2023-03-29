package com.example.roomdatabase.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.R
import com.example.roomdatabase.model.User

class ListAdapter/*(context: Context, val items: List<String>)*/ : RecyclerView.Adapter<ListAdapter.MyViewHolder>(){

    private var userList = emptyList<User>()



    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val id:TextView = itemView.findViewById(R.id.srNumber)
        val firstName:TextView = itemView.findViewById(R.id.firstName)
        val lastName:TextView = itemView.findViewById(R.id.LastName)
        val Age:TextView = itemView.findViewById(R.id.userAge)
        val rowLayout:ConstraintLayout = itemView.findViewById(R.id.rowLayout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row , parent , false))
    }

    override fun getItemCount(): Int {
        return userList.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]



        holder.id.text = currentItem.id.toString()
        holder.firstName.text = currentItem.firstName
        holder.lastName.text = currentItem.lastName
        holder.Age.text =currentItem.age.toString()

        holder.rowLayout.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }
    fun setData(user:List<User>){
        this.userList = user
        notifyDataSetChanged()
    }

}