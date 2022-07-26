package com.example.roomapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomapp.R
import com.example.roomapp.model.InventoryItem
import com.example.roomapp.viewmodel.InventoryItemViewModel
import kotlinx.android.synthetic.main.fragment_update_inventory_item.*
import kotlinx.android.synthetic.main.fragment_update_inventory_item.view.*

class InventoryItemUpdateFragment : Fragment() {

    private val args by navArgs<InventoryItemUpdateFragmentArgs>()

    private lateinit var inventoryItemViewModel: InventoryItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_inventory_item, container, false)

        inventoryItemViewModel = ViewModelProvider(this).get(InventoryItemViewModel::class.java)

        view.updateName_et.setText(args.currentInventoryItem.name)
        view.update_price_huf_et.setText(args.currentInventoryItem.price_huf.toString())
        view.update_price_eur_et.setText(args.currentInventoryItem.price_eur.toString())

        view.update_btn.setOnClickListener {
            updateItem()
        }

        // Add menu
        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem() {
        val name = updateName_et.text.toString()
        val priceHuf = Integer.parseInt(update_price_huf_et.text.toString())
        val priceEur = Integer.parseInt(update_price_eur_et.text.toString())

        if (inputCheck(name, priceHuf, priceEur)) {
            // Create InventoryItem Object
            val updatedInventoryItem = InventoryItem(args.currentInventoryItem.id, name, priceHuf, priceEur)
            // Update Current InventoryItem
            inventoryItemViewModel.updateInventoryItem(updatedInventoryItem)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_update_inventory_to_list_inventory)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(firstName: String, priceHuf: Int, priceEur: Int): Boolean {
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
            inventoryItemViewModel.deleteInventoryItem(args.currentInventoryItem)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentInventoryItem.name}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_update_inventory_to_list_inventory)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentInventoryItem.name}?")
        builder.setMessage("Are you sure you want to delete ${args.currentInventoryItem.name}?")
        builder.create().show()
    }
}