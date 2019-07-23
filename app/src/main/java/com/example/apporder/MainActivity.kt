package com.example.apporder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.apporder.Command.Invoker
import com.example.apporder.Command.RedoCommand
import com.example.apporder.Command.UndoCommand
import com.example.apporder.Model.DanhSachMonAn
import com.example.apporder.Model.SharedViewModel
import com.example.apporder.View.Fragment.FragmentDanhSachMonAn
import com.example.apporder.View.Fragment.FragmentDanhSachOrder
import kotlinx.android.synthetic.main.layout_order.*

class MainActivity : AppCompatActivity(),View.OnClickListener {
    var fragemnt:FragmentDanhSachMonAn?=null
    var fragemnt2:FragmentDanhSachOrder?=null
    var invoker: Invoker?=null
    var danhSachMonAn:DanhSachMonAn?=null
    val sharedViewModel by lazy {
        ViewModelProviders.of(this).get(SharedViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_order)
        invoker= Invoker()
        danhSachMonAn=DanhSachMonAn()
         fragemnt=FragmentDanhSachMonAn()
         fragemnt2=FragmentDanhSachOrder()

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragemnt!!).commit()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container2,fragemnt2!!).commit()

        btnHuy.setOnClickListener(this)
        btnRedo.setOnClickListener(this)
        btnUndo.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        var id:Int=v!!.id
        when(id){
            R.id.btnHuy -> {
               fragemnt2!!.XoaDanhSach()
            }
            R.id.btnRedo ->{
              fragemnt2!!.Redo()
            }
            R.id.btnUndo ->{
                fragemnt2!!.Undo()

            }

        }
    }

}
