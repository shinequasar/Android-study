package com.example.sqllightairport

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.sql.SQLException

class CountriesDbAdapter(private val mCtx: Context) {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var db: SQLiteDatabase

    private class DatabaseHelper(context: Context)
        : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
        override fun onCreate(p0: SQLiteDatabase?) { //Create
            p0!!.execSQL(TABLE_CREATE)
        }

        override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) { //스키마 변경(alter, drop)
            p0!!.execSQL("DROP TABLE IF EXSISTS "+ SQLITE_TABLE)
            onCreate(p0)
        }

    }

    @kotlin.Throws(SQLException::class)
    fun open(): CountriesDbAdapter{
        dbHelper = DatabaseHelper(mCtx)
        db = dbHelper.writableDatabase // 기록용도로 DB에 접근
        return this
   }

    fun close(){
        if(dbHelper)
    }

    fun createCountry(code: String, name: String, continent: String, region: String): Long {
        val values = ContentValues()
        values.put(KEY_CODE, code)
        values.put(KEY_CODE, name)
        values.put(KEY_CONTINENT, continent)
        values.put(KEY_REGION, region)
        return db.insert(SQLITE_TABLE, null, values)
    }

    fun insertSomeCountries() {
        createCountry("ATL", "Hartsfield–Jackson Atlanta International Airport", "United States", "Atlanta, Georgia")
        createCountry("PEK", "Beijing Capital International Airport", "China", "Chaoyang-Shunyi, Beijing")
        createCountry("LAX", "Los Angeles International Airport", "United States", "Los Angeles, California")
        createCountry("HND", "Tokyo Haneda Airport", "Japan", "Ōta, Tokyo")
        createCountry("DXB", "Dubai International Airport", "United Arab Emirates", "Garhoud, Dubai")
        createCountry("ORD", "O'Hare International Airport", "United States", "Chicago, Illinois")
        createCountry("LHR", "London Heathrow Airport", "United Kingdom", "Hillingdon, London")
        createCountry("CDG", "Paris-Charles de Gaulle Airport", "France", "Roissy-en-France, Île-de-France")
        createCountry("ICN", "Seoul Incheon International Airport", "Republic of Korea", "Incheon")
    }

    fun deleteAllCountries(): Boolean{
        //db.execSQL("delete from country where code = ?" and country=?", arrayOf("LAX","")) 밑에거와 동일. ?,?자리에 밑에는 null로 둔거
        var result = db.delete(SQLITE_TABLE, null, null)
        return result > 0
    }

    @kotlin.Throws(SQLException::class)
    fun fetchCountriesByName(inputText: String?): Cursor? {
        var mCursor: Cursor? = if (inputText == null || inputText.length == 0) {  // 필터에 입력이 안된 경우
            db!!.query(SQLITE_TABLE, arrayOf(KEY_ROWID, KEY_CODE, KEY_NAME, KEY_CONTINENT, KEY_REGION),
                null, null, null, null, KEY_CODE)
        } else { // 필터에 값을 입력한 경우
            db!!.query(SQLITE_TABLE, arrayOf(KEY_ROWID, KEY_CODE, KEY_NAME, KEY_CONTINENT, KEY_REGION),
                KEY_NAME + " LIKE '%" + inputText + "%'", null, null, null, KEY_CODE)
        }
        mCursor?.moveToFirst()
        return mCursor
    }

    fun fetchAllCountries():Cursor{
        var cursor: Cursor? = null
        val query = "select * from "+ SQLITE_TABLE+ "order by"+ KEY_CODE
        cursor = db.rawQuery(query, null)
        return cursor
    }

    companion object {
        const val KEY_ROWID = "_id"
        const val KEY_CODE = "code"
        const val KEY_NAME = "name"
        const val KEY_CONTINENT = "continent"
        const val KEY_REGION = "region"
        private const val TAG = "CountriesDbAdapter"
        private const val DATABASE_NAME = "World"
        private const val SQLITE_TABLE = "Country"
        private const val DATABASE_VERSION = 2
        private const val TABLE_CREATE = "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                KEY_ROWID + " integer PRIMARY KEY autoincrement," +
                KEY_CODE + "," +
                KEY_NAME + "," +
                KEY_CONTINENT + "," +
                KEY_REGION + "," +
                " UNIQUE (" + KEY_CODE + "));"
    }
}