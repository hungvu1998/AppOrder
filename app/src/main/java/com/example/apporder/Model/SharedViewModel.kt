package com.example.apporder.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SharedViewModel:ViewModel() {
    var mutableLiveData:MutableLiveData<MonAnModel>?=MutableLiveData<MonAnModel>()

    public fun setValue(monAnModel: MonAnModel){
        mutableLiveData!!.value=monAnModel
    }
    fun getValue():LiveData<MonAnModel>{
        return mutableLiveData!!
    }
}