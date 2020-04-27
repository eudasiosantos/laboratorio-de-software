package com.example.calendario

import android.content.Context
import android.widget.Toast

class Message {

    companion object{

        fun message(con: Context,msg: String){
            Toast.makeText(con,msg,Toast.LENGTH_LONG).show()
        }

    }
}