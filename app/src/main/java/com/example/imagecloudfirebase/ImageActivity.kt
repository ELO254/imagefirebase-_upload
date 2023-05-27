package com.example.imagecloudfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.imagecloudfirebase.databinding.ActivityImageBinding

class ImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}