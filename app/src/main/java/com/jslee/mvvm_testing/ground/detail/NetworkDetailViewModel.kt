package com.jslee.mvvm_testing.ground.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.jslee.mvvm_testing.R
import com.jslee.mvvm_testing.data.remote.GroundProperty

class NetworkDetailViewModel(groundProperty: GroundProperty,
                             app: Application) : AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<GroundProperty>()
    val selectedProperty: LiveData<GroundProperty>
        get() = _selectedProperty

    init {
        _selectedProperty.value = groundProperty
    }


    val displayPropertyPrice = Transformations.map(selectedProperty) {
        app.applicationContext.getString(
            when (it.isRental) {
                true -> R.string.display_price_monthly_rental
                false -> R.string.display_price
            }, it.price)
    }

    val displayPropertyType = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.display_type,
            app.applicationContext.getString(
                when (it.isRental) {
                    true -> R.string.type_rent
                    false -> R.string.type_sale
                }))
    }


}