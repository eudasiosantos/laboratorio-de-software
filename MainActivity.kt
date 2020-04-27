package com.example.calendario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText


class MainActivity : AppCompatActivity() {

    lateinit var userName : EditText
    lateinit var password :  EditText
    lateinit var getPassword :  EditText
    lateinit var ca : CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userName = findViewById(R.id.etUsername) as EditText
        password = findViewById(R.id.etPassword) as EditText
        getPassword = findViewById(R.id.etPassword2) as EditText
        ca = CustomAdapter(this)
    }

    fun addUser(view: View){
        var user: String = userName.text.toString()
        var pass: String = password.text.toString()
        var id: Long = ca.insertData(user,pass)
        if (id < 0){
            Message.message(this,"Falha ao inserir.")
        }else{
            Message.message(this,"inserido com sucesso.")
        }
    }

    fun viewUsers(view: View){
        var data: String = ca.getAllData()
        Message.message(this,data)
    }

    fun viewUser(view: View){
        var data: String = ca.getData(getPassword.text.toString().trim())
        Message.message(this,data)
    }

    fun updateUser(view: View){
        ca.updateName("test","mihir")
        Message.message(this,"Data Update Sucessfully")
    }

    fun deleteUser(view: View){
        var count: Int = ca.deleteRow()
        Message.message(this,""+count)
    }

}
