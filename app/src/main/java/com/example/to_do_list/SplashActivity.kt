package com.example.to_do_list

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        // Get reference to the ImageView
        val splashImage: ImageView = findViewById(R.id.splashImage)

        // Add touch listener to the ImageView
        splashImage.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                // If the image is touched, navigate to the task page
                navigateToTaskActivity()
                true
            } else {
                false
            }
        }
    }

    private fun navigateToTaskActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Finish the splash activity
    }
}
