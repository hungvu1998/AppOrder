package com.example.apporder.View.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.apporder.Model.MonAnModel
import com.example.apporder.Model.SharedViewModel
import com.example.apporder.R

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apporder.Adapter.AdapterDanhSachOrder
import com.example.apporder.Command.InsertCommand
import com.example.apporder.Command.Invoker
import com.example.apporder.Command.RedoCommand
import com.example.apporder.Command.UndoCommand
import com.example.apporder.Model.DanhSachMonAn


public class FragmentDanhSachOrder : Fragment() {
    val sharedViewModel by lazy {
        ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)
    }
    var recyclerView:RecyclerView?=null
    var layoutManager: RecyclerView.LayoutManager?=null
    var adapter:AdapterDanhSachOrder?=null
    public var invoker:Invoker?=null
    //var monAnModelList:ArrayList<MonAnModel>?=null
    var danhSachMonAn:DanhSachMonAn?=null
    var txtTongTien:TextView?=null
    var tongtien:Long?=0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view:View=inflater.inflate(R.layout.fragment_danhsach_order,container,false)
      //   monAnModelList = ArrayList<MonAnModel>()
        danhSachMonAn= DanhSachMonAn()
        invoker= Invoker()
        txtTongTien=view.findViewById(R.id.txtTongTien)
        recyclerView=view.findViewById(R.id.recyclerviewDanhSachOrder)
        layoutManager= LinearLayoutManager(context)
        adapter = AdapterDanhSachOrder(danhSachMonAn!!.list!!,context!!)
        recyclerView!!.layoutManager = layoutManager

        recyclerView!!.adapter = adapter
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedViewModel.getValue().observe(viewLifecycleOwner,object :Observer<MonAnModel>
        {
            override fun onChanged(t: MonAnModel?) {
              //  monAnModelList!!.add(t!!)
                val insertCommand: InsertCommand =InsertCommand(danhSachMonAn!!,t!!)
                invoker!!.xuky(insertCommand)
               txtTongTien!!.text=danhSachMonAn!!.TinhTongTien()
                adapter!!.notifyDataSetChanged()
            }

        })
    }
    fun Undo(){
        val undoCommand: UndoCommand = UndoCommand(invoker!!)
        invoker!!.xuky(undoCommand)
        txtTongTien!!.text=danhSachMonAn!!.TinhTongTien()
        adapter!!.notifyDataSetChanged()


    }
    fun Redo(){
        val redoCommand: RedoCommand = RedoCommand(invoker!!)
        invoker!!.xuky(redoCommand)
        txtTongTien!!.text=danhSachMonAn!!.TinhTongTien()
        adapter!!.notifyDataSetChanged()
    }
    fun XoaDanhSach(){
        danhSachMonAn!!.list.clear()
        invoker!!.redoList.clear()
        invoker!!.history.clear()
        txtTongTien!!.text=danhSachMonAn!!.TinhTongTien()
        adapter!!.notifyDataSetChanged()
    }


}