package com.udacity.shoestore.screens.shoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_detail,
            container,
            false
        )

        binding.cancelButton.setOnClickListener {
            showUnsavedChangesDialog()
        }

        return binding.root
    }

    private fun showUnsavedChangesDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.unsaved_alert_title))
        builder.setMessage(getString(R.string.unsaved_alert_message))
        builder.setPositiveButton(getString(R.string.unsaved_alert_positive_button_text)) { _, _ ->
            findNavController().navigate(
                ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoesFragment()
            )
        }
        builder.setNegativeButton(getString(R.string.unsaved_alert_negative_button_text)) { dialog, _ ->
            dialog.dismiss()
        }

        builder.create().show()
    }
}