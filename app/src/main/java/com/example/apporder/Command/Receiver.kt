package com.example.apporder.Command

import android.util.Log
import java.util.*

class Receiver {
    fun undo(invoker: Invoker) {
        if (!invoker.history.isEmpty()) {
            var command:Command=invoker.history.pop()
            command.undo()
            invoker.redoList.push(command)
        }
    }

    fun redo(invoker: Invoker) {
        if (!invoker.redoList.isEmpty()) {
            var command:Command=invoker.redoList.pop()
            command.execute()
            invoker.history.push(command)
        }
    }
}