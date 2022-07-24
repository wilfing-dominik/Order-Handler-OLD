package com.example.roomapp.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomapp.R
import com.example.roomapp.viewmodel.InventoryItemViewModel
import kotlinx.android.synthetic.main.fragment_list_tables.view.*
import kotlinx.android.synthetic.main.fragment_list_tables.*

class TablesFragment : Fragment() {

    //private lateinit var inventoryViewModel: InventoryItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_inventory_item, container, false)

        // Recyclerview
        val adapter = InventoryItemListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        /*// UserViewModel
        inventoryViewModel = ViewModelProvider(this).get(InventoryItemViewModel::class.java)
        inventoryViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })*/

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.inventory_button)
        }
        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.log_button)
        }

        // Add menu
        setHasOptionsMenu(true)

        return view
    }
}