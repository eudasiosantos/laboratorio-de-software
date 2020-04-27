package com.example.calendario

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class CustomAdapter {

    lateinit var dbHelper : DbHelper

    constructor(context: Context){
        dbHelper = DbHelper(context)
    }

    fun insertData(name: String, password: String)  :   Long{
        var db : SQLiteDatabase = dbHelper.writableDatabase
        var cv: ContentValues = ContentValues()
        cv.put(DbHelper.NAME,name)
        cv.put(DbHelper.PASSWORD,password)
        var id: Long = db.insert(DbHelper.TABLE_NAME,null,cv)
        return id
    }

    fun getAllData() : String{
        var db: SQLiteDatabase = dbHelper.writableDatabase
        var columns : Array<String> = arrayOf(DbHelper.UID, DbHelper.NAME, DbHelper.PASSWORD)
        var cursor: Cursor = db.query(DbHelper.TABLE_NAME,columns,null,null,null,null,null)
        var sb: StringBuffer = StringBuffer()
        while (cursor.moveToNext()){
            var cid: Int = cursor.getInt(0)
            var name: String = cursor.getString(1)
            var password: String = cursor.getString(2)
            sb.append("$cid,$name,$password \n")
        }
        return sb.toString()
    }

    fun getData(name: String) : String {
        var db: SQLiteDatabase = dbHelper.writableDatabase
        var columns : Array<String> = arrayOf(DbHelper.NAME,DbHelper.PASSWORD)
        var cursor: Cursor = db.query(DbHelper.TABLE_NAME,columns,DbHelper.NAME+" = '"+name+"'",null,null,null,null)
        var sb: StringBuffer = StringBuffer()
        while (cursor.moveToNext()){
            var index1 : Int = cursor.getColumnIndex(DbHelper.NAME)
            var index2 : Int = cursor.getColumnIndex(DbHelper.PASSWORD)
            var name: String = cursor.getString(index1)
            var password: String = cursor.getString(index2)
            sb.append("$name,$password \n")   }
        return sb.toString()
    }

    fun updateName(oldName: String, newName: String) : Int{
        var db: SQLiteDatabase = dbHelper.writableDatabase
        var cv: ContentValues = ContentValues()
        cv.put(DbHelper.NAME,newName)
        var whereargs: Array<String> = arrayOf(oldName)
        var count: Int = db.update(DbHelper.TABLE_NAME,cv,DbHelper.NAME+"=?",whereargs)
        return count
    }

    fun deleteRow() : Int{
        var db: SQLiteDatabase = dbHelper.writableDatabase
        var whereargs: Array<String> = arrayOf("priyan")
        var count: Int = db.delete(DbHelper.TABLE_NAME,DbHelper.NAME+"=?",whereargs)
        return count
    }
}