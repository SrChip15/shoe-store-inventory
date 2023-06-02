package com.udacity.shoestore.screens.shoes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoesViewModel : ViewModel() {

    private val _shoes = MutableLiveData<MutableList<Shoe>>()
    val shoes: LiveData<MutableList<Shoe>>
        get() = _shoes

    init {
        _shoes.value = mutableListOf(
            Shoe(
                name = "Air Jordan",
                company = "Nike",
                description = "comfy basketball",
                size = 10.5
            ),
            Shoe(
                name = "Air Jordan",
                company = "Nike",
                description = "comfy basketball",
                size = 10.5
            ),
        )
    }
}