package com.stylingandroid.recyclerviewanimations

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val itemString: String by lazy(LazyThreadSafetyMode.NONE) {
        getString(R.string.item_string)
    }

    private val adapter: MyAdapter by lazy(LazyThreadSafetyMode.NONE) {
        MyAdapter(itemString)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(true)
            setTitle(R.string.app_name)
        }

        recyclerView.setup()
    }

    private fun RecyclerView.setup() {
        adapter = this@MainActivity.adapter
        layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
        when(item?.itemId) {
            R.id.menu_add -> {
                addItem()
                true
            }
            else -> null
        } ?: super.onOptionsItemSelected(item)

    private fun addItem() {
        adapter.appendItem(itemString)
    }

}
