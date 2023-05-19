package org.d3if3062.mobpro1.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3062.mobpro1.db.CalorieDao

class HitungViewModelFactory(private val db: CalorieDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HitungViewModel::class.java)){
            return HitungViewModel(db) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel Class")
    }
}