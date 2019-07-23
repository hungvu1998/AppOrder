package com.example.apporder.Command

import android.util.Log
import com.example.apporder.Model.DanhSachMonAn
import com.example.apporder.Model.MonAnModel

class InsertCommand :Command{
    var danhSachMonAn:DanhSachMonAn?=null
    var monAnModel:MonAnModel?=null
    constructor(danhSachMonAn:DanhSachMonAn,monAnModel: MonAnModel){
        this.danhSachMonAn=danhSachMonAn
        this.monAnModel=monAnModel
    }
       override fun execute() {
        danhSachMonAn!!.insert(monAnModel!!)
    }
    override fun undo() {
        danhSachMonAn!!.remove()
    }
}