package com.example.apporder.Controller

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apporder.Adapter.AdapterMonAnModel
import com.example.apporder.Model.MonAnModel
import com.example.apporder.Model.SharedViewModel
import com.example.zooapp.Control.Interface.DanhSachMonAnInterface
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference




class DanhSachMonAnCotroller {
    var context: Context?=null
    var monAnModel:MonAnModel?=null
    //var mainInterface:MainInterface?=null
    var adapter:AdapterMonAnModel?=null
    var monAnModelList:ArrayList<MonAnModel>?=null
    var layoutManager: RecyclerView.LayoutManager?=null
    var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference
    constructor(context: Context){
        this.context=context
        monAnModel= MonAnModel()
    }

    fun getDanhSachMonAnController(context: Context, recyclerView: RecyclerView,sharedViewModel: SharedViewModel){
        monAnModelList = ArrayList<MonAnModel>()
        monAnModelList!!.clear()
        layoutManager= GridLayoutManager(context,4)
        adapter = AdapterMonAnModel(monAnModelList!!,context,sharedViewModel)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        var danhSachMonAnInterface:DanhSachMonAnInterface= object : DanhSachMonAnInterface {
            override fun getDanhSachMonAn(monAnModel: MonAnModel) {
                var storageReferencee: StorageReference = FirebaseStorage.getInstance().reference.child(monAnModel!!.hinhanh.toString())
                storageReferencee.downloadUrl.addOnSuccessListener { uri ->
                    monAnModel.linkhinh=uri.toString()
                    if (uri!=null){
                        monAnModelList!!.add(monAnModel)
                        adapter!!.notifyDataSetChanged()
                    }
                }

            }
        }

        monAnModel!!.getDanhSachMonAn(danhSachMonAnInterface,monAnModelList!!)
    }
    fun timkiem(s:String,sharedViewModel:SharedViewModel,context: Context,recyclerView: RecyclerView){
        var list:ArrayList<MonAnModel> =ArrayList<MonAnModel>()
        for(item in monAnModelList!!){
            if (item.tenmonan.toString().toLowerCase().indexOf(s)==-1){
            }
            else{
                list.add(item)
            }
        }


        layoutManager= GridLayoutManager(context,4)
        adapter = AdapterMonAnModel(list!!,context,sharedViewModel)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

}