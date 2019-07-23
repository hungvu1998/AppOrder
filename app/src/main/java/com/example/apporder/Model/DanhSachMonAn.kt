package com.example.apporder.Model

import android.util.Log
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class DanhSachMonAn {
    val list = ArrayList<MonAnModel>()//
    fun insert(str:MonAnModel)
    {
        list.add(str)
    }
    fun remove(){

        list.removeAt(list.size - 1)

    }
    fun TinhTongTien():String{
        val localeVN = Locale("vi", "VN")
        val currencyVN = NumberFormat.getCurrencyInstance(localeVN)
        var tongtien:Long=0
        for(x in list){
          tongtien= tongtien+x.giatien!!
        }
        val str1 = currencyVN.format(tongtien!!)
        return str1
    }
}