package com.example.sqllightairport

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    private var dbHelper: CountriesDbAdapter? = null
    private var dataAdapter: SimpleCursorAdapter? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHelper = CountriesDbAdapter(this)
        dbHelper!!.open()

        //Clean all data
        dbHelper!!.deleteAllCountries()
        //Add some data
        dbHelper!!.insertSomeCountries()

        //Generate ListView from SQLite Database
        displayListView()
    }

    override fun onDestroy() {
        super.onDestroy()
        dbHelper!!.close()
    }

    private fun displayListView() {
        val cursor = dbHelper!!.fetchAllCountries()

        // The desired columns to be bound
        val columns = arrayOf(
            CountriesDbAdapter.KEY_CODE,
            CountriesDbAdapter.KEY_NAME,
            CountriesDbAdapter.KEY_CONTINENT,
            CountriesDbAdapter.KEY_REGION)

        // the XML defined views which the data will be bound to
        val to = intArrayOf(
            R.id.code,
            R.id.name,
            R.id.continent,
            R.id.region)

        // create the adapter using the cursor pointing to the desired data
        //as well as the layout information
        dataAdapter = SimpleCursorAdapter(
            this, R.layout.country_info,
            cursor,
            columns,
            to,
            0)
        val listView = findViewById<View>(R.id.listView1) as ListView
        // Assign adapter to ListView
        listView.adapter = dataAdapter
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { listView, view, position, id -> // Get the cursor, positioned to the corresponding row in the result set
                val cursor = listView.getItemAtPosition(position) as Cursor

                // Get the state's capital from this row in the database.
                val countryCode = cursor.getString(cursor.getColumnIndexOrThrow("code"))
                Toast.makeText(
                    applicationContext,
                    countryCode, Toast.LENGTH_SHORT
                ).show()
            }
        dataAdapter!!.filterQueryProvider = FilterQueryProvider { constraint -> dbHelper!!.fetchCountriesByName(constraint.toString())!! }
        val myFilter = findViewById<View>(R.id.myFilter) as EditText
        myFilter.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                dataAdapter!!.filter.filter(s.toString())
            }
        })
    }
}