package com.example.roomapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomapp.R
import com.example.roomapp.model.InventoryItem
import com.example.roomapp.viewmodel.InventoryItemViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var inventoryItemViewModel: InventoryItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        inventoryItemViewModel = ViewModelProvider(this).get(InventoryItemViewModel::class.java)

        view.updateFirstName_et.setText(args.currentUser.firstName)
        view.updateLastName_et.setText(args.currentUser.lastName)
        view.updateAge_et.setText(args.currentUser.age.toString())

        view.update_btn.setOnClickListener {
            updateItem()
        }

        // Add menu
        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem() {
        val name = updateFirstName_et.text.toString()
        val priceHuf = Integer.parseInt(updateAge_et.text.toString())
        val priceEuf = Integer.parseInt(updateAge_et.text.toString())

        if (inputCheck(name, priceHuf, updateAge_et.text)) {
            // Create User Object
            val updatedInventoryItem = InventoryItem(args.currentUser.id, name, priceHuf, priceEuf)
            // Update Current User
            inventoryItemViewModel.updateInventoryItem(updatedInventoryItem)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(firstName: String, priceHuf: Int, priceEur: Editable): Boolean {
        //return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
        return true
        TODO("NINCS INPUT CHECK")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteInventoryItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteInventoryItem() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            inventoryItemViewModel.deleteInventoryItem(args.currentUser)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentUser.firstName}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")
        builder.create().show()
    }
}