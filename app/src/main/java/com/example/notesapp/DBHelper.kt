package com.example.notesapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(
    context: Context?,
    name: String?= "details.db",
    factory: SQLiteDatabase.CursorFactory?= null,
    version: Int= 1,
    private val tableName: String= "notes"
) : SQLiteOpenHelper(context, name, factory, version) {

    private val sqLiteDatabase: SQLiteDatabase= writableDatabase

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table $tableName (Note Text)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}

    fun saveNotes(note: String): Long {
        val cv= ContentValues()
        cv.put("Note",note)
        return sqLiteDatabase.insert(tableName,null,cv)
    }

    fun gettingNotes(): ArrayList<String>{
        return try{
            val notes= arrayListOf<String>()
            val cursor =
                sqLiteDatabase.query(tableName, arrayOf("Note"), null, null, null, null, null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast){
                notes.add(cursor.getString(0))
                cursor.moveToNext()
            }
            notes
        } catch (e:Exception){
            arrayListOf("Error")
        }
    }
}