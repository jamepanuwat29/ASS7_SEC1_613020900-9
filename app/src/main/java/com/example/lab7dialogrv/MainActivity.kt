package com.example.lab7dialogrv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asslab7dialogrv.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_dialog_layout.*

class MainActivity : AppCompatActivity() {
    val employeeList = arrayListOf<Employee>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        employeeData()
        recycler_view.adapter = StudentsAdapter(this.employeeList, applicationContext)
        recycler_view.layoutManager = LinearLayoutManager(applicationContext)
    }
    fun employeeData() {
        employeeList.add(Employee("Panuwat", "Male", "Panuwat@gmail.com", 50000))
        employeeList.add(Employee("Punnita", "Female", "Punnita@gmail.com", 50000))
    }
    fun addEmployee(view : View) {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.add_dialog_layout, null)
        val myBuilder = AlertDialog.Builder(this)
        myBuilder.setView(mDialogView)

        val mAlertDialog = myBuilder.show()
        mAlertDialog.btnAdd.setOnClickListener() {
            val radio = mAlertDialog.radio_gender.checkedRadioButtonId
            val getRadio = mAlertDialog.findViewById<RadioButton>(radio)
            employeeList.add(
                Employee(
                    mAlertDialog.edt_name.text.toString(),
                    getRadio?.text.toString(),
                    mAlertDialog.edt_email.text.toString(),
                    mAlertDialog.edt_salary.text.toString().toInt()
                )
            )
            recycler_view.adapter?.notifyDataSetChanged()
            Toast.makeText(
                applicationContext,
                "The employee is added successfully",
                Toast.LENGTH_LONG
            ).show()
            mAlertDialog.dismiss()
        }
        mAlertDialog.btnCancel.setOnClickListener(){
            mAlertDialog.dismiss()
        }
    }

}