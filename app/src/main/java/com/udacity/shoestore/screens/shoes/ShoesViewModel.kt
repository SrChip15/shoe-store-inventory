package com.udacity.shoestore.screens.shoes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoesViewModel : ViewModel() {

    private val _shoes = MutableLiveData<MutableList<Shoe>>()
    val shoes: LiveData<MutableList<Shoe>>
        get() = _shoes

    private val _addingShoe = MutableLiveData<Boolean>()
    val addingShoe: LiveData<Boolean>
        get() = _addingShoe

    val shoeName = MutableLiveData<String>()
    val shoeCompany = MutableLiveData<String>()
    val shoeSize = MutableLiveData<String>()
    val shoeDescription = MutableLiveData<String>()

    init {
        _shoes.value = mutableListOf(
            Shoe(
                name = "Air Jordan I",
                company = "Nike",
                description = "The first",
                size = 10.5
            ),
            Shoe(
                name = "Kobe 6 Mambacita",
                company = "Nike",
                description = "Equalizer",
                size = 10.5
            ),
        )
    }

    fun addShoe() {
        Timber.i("addShoe() called")
        _addingShoe.value = true
        _shoes.value?.add(
            Shoe(
                name = shoeName.value!!,
                size = shoeSize.value!!.toDouble(),
                company = shoeCompany.value!!,
                description = shoeDescription.value!!
            )
        )
        _addingShoe.value = false
    }
}