package com.example.roomdatabase.fragments.update

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdatabase.R
import com.example.roomdatabase.model.User
import com.example.roomdatabase.viewModel.UserViewModel


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val firstName = view.findViewById<EditText>(R.id.updateFirstName)
        val lastName = view.findViewById<EditText>(R.id.updateLastName)
        val age = view.findViewById<EditText>(R.id.updateAge)

        val updateButton = view.findViewById<Button>(R.id.buttonUpdate)

        firstName.setText(args.currentUser.firstName)
        lastName.setText(args.currentUser.lastName)
        age.setText(args.currentUser.age.toString())

        updateButton.setOnClickListener {

            updateItem()
        }

        //ADD MENU

        setHasOptionsMenu(true)
        return view
    }



    private fun updateItem(){
        val firstName = view?.findViewById<EditText>(R.id.updateFirstName)
        val lastName = view?.findViewById<EditText>(R.id.updateLastName)
        val age = view?.findViewById<EditText>(R.id.updateAge)

        val FirstName = firstName?.text.toString()
        val LastName = lastName?.text.toString()
        val Age = age?.text.toString()

        if (inputCheck(FirstName , LastName , Age )){

            // Create User Object

            val updateduser = User(args.currentUser.id,FirstName,LastName,Integer.parseInt(Age.toString()))

            // Update Current User
            mUserViewModel.updateuser(updateduser)
            Toast.makeText(requireContext(),"Data Updated Successfully" , Toast.LENGTH_SHORT).show()

            // Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Please Fill Out All Fields" , Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(FirstName:String, LastName: String, Age: String) : Boolean{
        return !(TextUtils.isEmpty(FirstName) && TextUtils.isEmpty(LastName) && TextUtils.isEmpty(Age))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.deleteMenu){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes "){ _,_ ->

            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),"Successfully Removed : ${args.currentUser.firstName}",Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No "){ _,_ -> }
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")
        builder.create().show()
    }


}