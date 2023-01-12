package com.example.collapsingtoolbar

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.collapsingtoolbar.databinding.ActivityMainBinding
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var appbarExtended = true
    lateinit var collapseMenu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.appbar.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
                appbarExtended = abs(verticalOffset) <= 433
                Log.d(TAG, "verticalOffset: $verticalOffset")
//                setLightStatusBar(abs(verticalOffset) >= 433)
                invalidateOptionsMenu()
            }
        )

    }

    private fun setLightStatusBar(expandState: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val window: Window = window
            val decorView: View = window.decorView
            val wic = WindowInsetsControllerCompat(window, decorView)
            wic.isAppearanceLightStatusBars = expandState // true or false as desired.

            // And then you can set any background color to the status bar.
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if(!appbarExtended || collapseMenu.size() != 1){
            collapseMenu.add("Add")
                .setIcon(R.drawable.ic_add)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        }
        return super.onPrepareOptionsMenu(collapseMenu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        if (menu != null) {
            collapseMenu = menu
        }
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

    companion object {
        const val TAG = "Response:::"
    }

}