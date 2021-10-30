package com.example.pushdatatomysql

import android.database.sqlite.SQLiteDatabase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,ITEMNAME TEXT,ITEMCOUNT NUMBER,TOTALAMOUNT NUMBER)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun insertData(itemname: String, itemcount: Int, totalamount: Int): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_2, itemname)
        contentValues.put(COL_3, itemcount)
        contentValues.put(COL_4, totalamount)
        val result = db.insert(TABLE_NAME, null, contentValues)
        return if (result == -1L) false else true
    }

    //adding
    open fun getAllData(): Cursor? {
        val db = this.writableDatabase
        return db.rawQuery("select * from $TABLE_NAME", null)
    }
    //adding

    fun updateData(id: Int, itemname: String, itemcount: Int, totalamount: Int): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_1, id)
        contentValues.put(COL_2, itemname)
        contentValues.put(COL_3, itemcount)
        contentValues.put(COL_4, totalamount)
        db.update(TABLE_NAME, contentValues, "ITEMNAME = ?", arrayOf(itemname))
        return true
    }

//    fun deleteData(id: String): Int {
//        val db = this.writableDatabase
//        return db.delete(TABLE_NAME, "ID = ?", arrayOf(id))
//    }

    companion object {
        const val DATABASE_NAME = "Employee.db"
        const val TABLE_NAME = "EMP_table"
        const val COL_1 = "ID"
        const val COL_2 = "ITEMNAME"
        const val COL_3 = "ITEMCOUNT"
        const val COL_4 = "TOTALAMOUNT"
    }
}