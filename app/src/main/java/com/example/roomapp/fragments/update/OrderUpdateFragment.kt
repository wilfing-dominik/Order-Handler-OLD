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
import com.example.roomapp.model.Order
import com.example.roomapp.viewmodel.OrderViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class OrderUpdateFragment : Fragment() {

    private val args by navArgs<InventoryItemUpdateFragmentArgs>()

    private lateinit var orderViewModel: OrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)

        view.updateName_et.setText(args.currentOrder.name)
        view.update_price_huf_et.setText(args.currentOrder.price_huf.toString())
        view.update_price_eur_et.setText(args.currentOrder.price_eur.toString())

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
            // Create Order Object
            val updatedOrder = Order(args.currentOrder.id, name, priceHuf, priceEur)
            // Update Current Order
            orderViewModel.updateOrder(updatedOrder)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
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
            deleteOrder()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteOrder() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            orderViewModel.deleteOrder(args.currentOrder)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentOrder.name}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentOrder.name}?")
        builder.setMessage("Are you sure you want to delete ${args.currentOrder.name}?")
        builder.create().show()
    }
}