package com.example.roomdatabase.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdatabase.R
import com.example.roomdatabase.model.User
import com.example.roomdatabase.viewModel.UserViewModel


class AddFragment : Fragment() {

    private var mUserViewModel: UserViewModel? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val buttonAdd = view.findViewById<Button>(R.id.buttonAdd)

        buttonAdd.setOnClickListener{
            insertdataToDatabase()
        }
        return view
    }

    private fun insertdataToDatabase() {

        val firstName = view?.findViewById<EditText>(R.id.textFirstName)
        val lastName = view?.findViewById<EditText>(R.id.textLastName)
        val age = view?.findViewById<EditText>(R.id.textAge)


        val FirstName = firstName?.text.toString()
        val LastName = lastName?.text.toString()
        val Age = age?.text.toString()

        if (inputCheck(FirstName,LastName,Age)){
            val user = User(0,FirstName,LastName,Integer.parseInt(Age.toString()))

            mUserViewModel?.addUser(user)
            Toast.makeText(requireContext(),"Successfully added" , Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Please Fill Out All Fields . " , Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(FirstName:String, LastName: String, Age: String) : Boolean{
        return !(TextUtils.isEmpty(FirstName) && TextUtils.isEmpty(LastName) && TextUtils.isEmpty(Age))
    }


}