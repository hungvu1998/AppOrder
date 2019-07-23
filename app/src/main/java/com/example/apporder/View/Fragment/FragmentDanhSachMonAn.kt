package com.example.apporder.View.Fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.apporder.Controller.DanhSachMonAnCotroller
import com.example.apporder.Controller.ItemClickListener

import com.example.apporder.Model.SharedViewModel
import com.example.apporder.R
import kotlinx.android.synthetic.main.fragment_danhsach_monan.view.*


class FragmentDanhSachMonAn : Fragment(){

    val sharedViewModel by lazy {
        ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)
    }
    var recyclerView:RecyclerView?=null
    var lister:ItemClickListener?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view:View=inflater.inflate(R.layout.fragment_danhsach_monan,container,false)
        var mainControler:DanhSachMonAnCotroller= DanhSachMonAnCotroller(context!!)
        recyclerView=view.findViewById(R.id.recyclerviewDanhSachMonAn)
        mainControler!!.getDanhSachMonAnController(context!!,recyclerView!!,sharedViewModel)
        view.edtTimKiem.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                //kiem tra sau khi go xong
                mainControler.timkiem(s.toString(),sharedViewModel,context!!,recyclerView!!)
                //Log.d("kiemtra",""+s)

            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
                //kiem tra chu cũ trước khi gõ chữ mới

            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                //kiem tra khi go chu vao may


            }
        })
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is ItemClickListener){
            lister=context
        }
        else
        {
            Toast.makeText(context,"ff",Toast.LENGTH_SHORT)
        }

    }

    override fun onDetach() {
        super.onDetach()
        lister=null
    }


}