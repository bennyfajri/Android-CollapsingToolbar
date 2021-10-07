package com.example.collapsingtoolbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.collapsingtoolbar.databinding.ActivityMainBinding
import com.google.android.material.appbar.AppBarLayout
import android.widget.Toast




class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var appbarExtended = true
    lateinit var collapseMenu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.appbar.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                if(Math.abs(verticalOffset) > 200){
                    appbarExtended = false
                }else{
                    appbarExtended = true
                }
                invalidateOptionsMenu()
            }
        )

    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if(collapseMenu != null && (!appbarExtended || collapseMenu.size() != 1)){
            collapseMenu.add("Add")
                .setIcon(R.drawable.ic_add)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        }
        return super.onPrepareOptionsMenu(collapseMenu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        collapseMenu = menu!!
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> finish()
            R.id.action_settings -> Toast.makeText(this, "Setting menu clicked!", Toast.LENGTH_SHORT).show()
        }
        if(item.title == "Add"){
            Toast.makeText(this, "Add menu clicked!", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }


}