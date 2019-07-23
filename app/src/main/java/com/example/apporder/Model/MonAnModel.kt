package com.example.apporder.Model

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.example.zooapp.Control.Interface.DanhSachMonAnInterface
import com.google.firebase.database.*

class MonAnModel: Parcelable {
    var hinhanh:String?=null
    var tenmonan:String?=null
    var giatien:Long?=null
    var linkhinh:String?=null
    var databaseReference: DatabaseReference?=null
   /* constructor(hinhanh:String,tenmonan:String,giatien:Long) : this() {
        this.hinhanh=hinhanh
        this.tenmonan=tenmonan
        this.giatien=giatien
    }*/
    constructor()
    constructor(parcel: Parcel) : this() {
        tenmonan = parcel.readString()
        linkhinh = parcel.readString()
        hinhanh = parcel.readString()
        giatien = parcel.readLong()
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(tenmonan)
        parcel.writeString(linkhinh)
        parcel.writeString(hinhanh)
        parcel.writeLong(giatien!!)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MonAnModel> {
        override fun createFromParcel(parcel: Parcel): MonAnModel {
            return MonAnModel(parcel)
        }

        override fun newArray(size: Int): Array<MonAnModel?> {
            return arrayOfNulls(size)
        }
    }
    fun LayDanhSachMonAn(dataSnapshot: DataSnapshot, mainInterface: DanhSachMonAnInterface){
        var dataSnapshotMonAn: DataSnapshot = dataSnapshot.child("MonAn")
        for(item: DataSnapshot in dataSnapshotMonAn.children)
        {
            var monAnModel2: MonAnModel? = item.getValue(MonAnModel::class.java)
            mainInterface.getDanhSachMonAn(monAnModel2!!)
        }
    }
    var dataRoot: DataSnapshot?=null
    fun getDanhSachMonAn(mainInterface: DanhSachMonAnInterface,monAnModelList:ArrayList<MonAnModel>){
        databaseReference= FirebaseDatabase.getInstance().reference


        var valueEventListener: ValueEventListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {

                dataRoot=p0
                monAnModelList.clear()

                LayDanhSachMonAn(p0,mainInterface)
            }

        }
        if(dataRoot!=null){
            LayDanhSachMonAn(dataRoot!!,mainInterface)


        }
        else{
            databaseReference!!.addValueEventListener(valueEventListener)

        }
    }
}