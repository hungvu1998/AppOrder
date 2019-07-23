package com.example.apporder.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.apporder.Command.Invoker
import com.example.apporder.Controller.ChuyenDuLieuInterface
import com.example.apporder.Controller.ItemClickListener

import com.example.apporder.Model.MonAnModel
import com.example.apporder.Model.SharedViewModel
import com.example.apporder.R
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList







class AdapterMonAnModel() :RecyclerView.Adapter<AdapterMonAnModel.ViewHolder>() {


    var list:ArrayList<MonAnModel>?=null
    var chuyenDuLieuInterface:ChuyenDuLieuInterface?=null
    var context:Context?=null

    var invoker:Invoker?=null
    var sharedViewModel:SharedViewModel?=null
    constructor(list: ArrayList<MonAnModel>, context: Context,sharedViewModel: SharedViewModel):this(){
        this.list=list
        this.context=context
        this.sharedViewModel=sharedViewModel

        invoker= Invoker()

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_monan, parent, false)

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
        Picasso.with(context).load((monAnModel.linkhinh)).into(holder.imgAnhMonAn)
        var monAnModelList:ArrayList<MonAnModel> = ArrayList<MonAnModel>()
       holder.itemClickListener=object : ItemClickListener {
           override fun onItemClick(pos: Int) {
               sharedViewModel!!.setValue(list!![pos])


           }

       }
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),View.OnClickListener {
        override fun onClick(v: View?) {
            this.itemClickListener!!.onItemClick(this.layoutPosition)
        }

        val cardviewOrder=itemView.findViewById<CardView>(R.id.cardviewOrder).setOnClickListener(this)
        val imgAnhMonAn=itemView.findViewById<ImageView>(R.id.imgAnhMonAn)
        val txtGiaTien=itemView.findViewById<TextView>(R.id.txtGiaTien)
        val txtTenMonAn=itemView.findViewById<TextView>(R.id.txtTenMonAn)
        var itemClickListener:ItemClickListener?=null

    }


}