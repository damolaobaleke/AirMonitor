package com.airmonitor.views.ui_fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

class HomeViewModel : ViewModel() {
    private val mText: MutableLiveData<String>

    val text: LiveData<String>
    get() = mText

    init {
        mText = MutableLiveData()
        mText.value = "This is home fragment"
    }
}