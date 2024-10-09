package com.example.menuybanez

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_fragment -> {
                startActivity(Intent(this, FragmentActivity::class.java))
                true
            }
            R.id.action_dialog -> {
                showDialog()
                true
            }
            R.id.action_exit -> {
                finishAffinity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showDialog() {
        AlertDialog.Builder(this)
            .setTitle("Notice")
            .setMessage("This is a Kotlin alert dialog with two actions.")
            .setPositiveButton("Confirm") { dialog, _ ->
                dialog.dismiss()
                Toast.makeText(this, "Confirmed", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
            }
            .show()
    }
}
