package com.example.roomdatabase.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.R
import com.example.roomdatabase.viewModel.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListFragment : Fragment() {
     //lateinit var floatingActionButton: FloatingActionButton
    private lateinit var mUserViewModel: UserViewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_list, container, false)

        //RecyclerView

        val adapter = ListAdapter()
        val recyclerView= view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter


        val layoutManager: LinearLayoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        //UserViewModel

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner , Observer { user ->
            adapter.setData(user)

        })

        val floatingActionButton = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)


        floatingActionButton.setOnClickListener{
            this.findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        return view

    }


}