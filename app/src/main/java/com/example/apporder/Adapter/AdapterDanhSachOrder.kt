package com.example.apporder.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.apporder.Controller.ChuyenDuLieuInterface
import com.example.apporder.Model.DanhSachMonAn
import com.example.apporder.Model.MonAnModel
import com.example.apporder.R
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList







class AdapterDanhSachOrder() :RecyclerView.Adapter<AdapterDanhSachOrder.ViewHolder>() {


    var list:ArrayList<MonAnModel>?=null
    var chuyenDuLieuInterface:ChuyenDuLieuInterface?=null
    var context:Context?=null

    constructor(list: ArrayList<MonAnModel>, context: Context):this(){
        this.list=list
        this.context=context

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_dathang, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var monAnModel: MonAnModel =list!![position]
        val localeVN = Locale("vi", "VN")
        val currencyVN = NumberFormat.getCurrencyInstance(localeVN)
        val str1 = currencyVN.format(monAnModel.giatien)
        holder.txtGiaTien.text=str1
        holder.txtTenMonAn.text=monAnModel.tenmonan.toString()
        val adapter:ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(context,R.array.numbers,android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        holder.spiner.adapter=adapter
        holder.spiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var soluong:Int=parent!!.getItemAtPosition(position).toString().toInt()
                var giatien:Long=monAnModel.giatien!!*soluong
                val tienthaydoi=currencyVN.format(giatien)
                holder.txtGiaTien.text=tienthaydoi
                var danhSachMonAn:DanhSachMonAn= DanhSachMonAn()

                for(item in list!!){
                    Log.d("kiemtra",""+item.giatien)
                }
            }

        }

    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtGiaTien=itemView.findViewById<TextView>(R.id.txtGiaTien)
        val txtTenMonAn=itemView.findViewById<TextView>(R.id.txtTenMonAn)
        val spiner=itemView.findViewById<Spinner>(R.id.spinner_soluong)

    }



}