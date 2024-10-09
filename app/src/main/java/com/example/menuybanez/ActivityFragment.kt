package com.example.menuybanez

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class FragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_activity)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ExampleFragment())
                .commit()
        }
    }
}
