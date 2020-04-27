package com.example.calendario

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.sql.SQLException

class DbHelper : SQLiteOpenHelper {

    companion object{
        internal val DATABASE_NAME : String = "db_calendario" //nome do banco
        internal val TABLE_NAME : String = "tb_profe" //nome da tabela
        internal val DATABASE_VERSION : Int = 1
        internal val UID : String = "id"
        internal val NAME : String = "Name"
        internal val PASSWORD : String = "Password"
        internal val CREATE_TABLE : String = "CREATE TABLE" + TABLE_NAME + " (" + UID + "INTEGER PRIMARY KEY AUTOINCREMENT," + NAME+ "VARCHAR(255)"+ PASSWORD + "VARCHAR(255));"
        internal val DROP_TABLE : String = "DROP TABLE IF EXISTS " + TABLE_NAME
    }

    internal lateinit var context: Context

    constructor(context: Context) : super(context,DATABASE_NAME, null, DATABASE_VERSION) {
        this.context = context
        Message.message(context,"Construct Called")
    }

    override fun onCreate(db: SQLiteDatabase?) {
        try {
            Message.message(context,"onCreate")
            db?.execSQL(CREATE_TABLE)
        }catch (e:SQLException){
            Message.message(context,""+e)
        }

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        try {
            Message.message(context,"onUpgrade")
            db?.execSQL(DROP_TABLE)
            onCreate(db)
        }catch (e:SQLException){
            Message.message(context,""+e)
        }

    }

}