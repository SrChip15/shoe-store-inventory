package com.udacity.shoestore.screens.shoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoesBinding
import com.udacity.shoestore.databinding.ItemShoeBinding

class ShoesFragment: Fragment() {

    private lateinit var binding: FragmentShoesBinding
    private val sharedViewModel: ShoesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoes, container, false)

        binding.apply {
            shoesViewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            shoesFragment = this@ShoesFragment
        }

        sharedViewModel.shoes.observe(viewLifecycleOwner) { shoeList ->
            shoeList.map {shoe ->
                val shoeBinding = ItemShoeBinding.inflate(layoutInflater)
                shoeBinding.shoe = shoe
                binding.shoeDisplay.addView(shoeBinding.root)
            }
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(
                ShoesFragmentDirections.actionShoesFragmentToShoeDetailFragment()
            )
        }

        return binding.root
    }
}
