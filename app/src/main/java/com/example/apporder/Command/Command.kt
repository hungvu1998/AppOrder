package com.example.apporder.Command

interface Command {
    fun execute()
    fun undo()
}