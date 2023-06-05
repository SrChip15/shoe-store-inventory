package com.udacity.shoestore.screens.shoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoesBinding
import com.udacity.shoestore.databinding.ItemShoeBinding

@Suppress("DEPRECATION", "OVERRIDE_DEPRECATION")
class ShoesFragment : Fragment() {

    private lateinit var binding: FragmentShoesBinding
    private val sharedViewModel: ShoesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoes, container, false)
        setHasOptionsMenu(true)

        binding.apply {
            shoesViewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            shoesFragment = this@ShoesFragment
        }

        sharedViewModel.shoes.observe(viewLifecycleOwner) { shoeList ->
            shoeList.map { shoe ->
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.logout_menu -> {
                findNavController().navigate(R.id.login_fragment)
                true
            }
           else -> super.onOptionsItemSelected(item)
        }
    }
}
