package com.example.roomapp.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.model.InventoryItem
import com.example.roomapp.viewmodel.InventoryItemViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddInventoryItemFragment : Fragment() {

    private lateinit var inventoryItemViewModel: InventoryItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        inventoryItemViewModel = ViewModelProvider(this).get(InventoryItemViewModel::class.java)

        view.add_btn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val name = addName_et.text.toString()
        val priceHuf = price_huf_et.text.toString()
        val priceEur = price_eur_et.text

        if(inputCheck(name, priceHuf, priceEur)){
            // Create InventoryItem Object
            val inventoryItem = InventoryItem(
                0,
                name,
                Integer.parseInt(priceHuf.toString()),
                Integer.parseInt(priceEur.toString())
            )
            // Add Data to Database
            inventoryItemViewModel.addInventoryItem(inventoryItem)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean{
        //return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
        return true
    }

}