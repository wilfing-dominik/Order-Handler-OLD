package com.example.roomapp.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Button
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

        view?.inventory_button?.setOnClickListener() {
            Toast.makeText(requireContext(), "cs", Toast.LENGTH_LONG).show()
        }

        return view
    }
}