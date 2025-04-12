package com.example.podejscie1

import CartFragment
import LocationFragment
import ProfileFragment
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val fragmentContainer = findViewById<FrameLayout>(R.id.fragmentContainer)
        if (fragmentContainer == null) {
            Log.e("MYTAG", "Fragment container not found!")
        } else {
            Log.i("MYTAG", "Fragment container found.")
        }
    }

    private fun openFragment(fragment: Fragment) {
        Log.i("MYTAG", "Attempting to add fragment: $fragment")
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commitNow()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onStart() {
        super.onStart()
        val buttonId = intent.getStringExtra("buttonId")
        Log.i("MYTAG", "Second On Create $buttonId")
        when (buttonId) {
            "location" -> openFragment(LocationFragment())
            "cart" -> openFragment(CartFragment())
            "profile" -> openFragment(ProfileFragment())
        }
    }
}