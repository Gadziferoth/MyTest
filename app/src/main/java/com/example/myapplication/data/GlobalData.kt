package com.example.myapplication.data

object GlobalData {
    lateinit var student : Student

    fun isStudentInitialized() = ::student.isInitialized


}