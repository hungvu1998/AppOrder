package com.example.apporder.Command

import android.util.Log

class RedoCommand :Command{
   var receiver:Receiver?=null
    var invoker:Invoker?=null
    constructor(invoker: Invoker){
        receiver= Receiver()
        this.invoker=invoker
    }
    override fun execute() {
        receiver!!.redo(invoker!!)
    }
    override fun undo() {
    }
}