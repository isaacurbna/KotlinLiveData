package com.isaacurbna.kotlinlivedata

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    val myResource = MutableLiveData<Long>()

    init {
        myResource.value = -1
    }
}