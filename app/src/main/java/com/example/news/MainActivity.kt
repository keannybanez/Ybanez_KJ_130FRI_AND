package com.example.news

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, HeadlineListFragment())
                    .commit()
            } else {

                val headlineListFragment = HeadlineListFragment()
                val newsContentFragment = NewsContentFragment()

                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, headlineListFragment)
                    .commit()

                supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, newsContentFragment)
                    .commit()
            }
        }
    }
}
