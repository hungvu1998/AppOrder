package com.example.apporder.Command

import android.util.Log
import java.util.*


class Invoker {
    var history: Stack<Command> = Stack<Command>()
    var redoList: Stack<Command> =  Stack<Command>()
    fun xuky(command: Command){

        command.execute()

        if (command !is UndoCommand && command !is RedoCommand)
        {
            history.push(command)
        }
    }

}