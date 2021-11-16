package dev.popov.qrcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.popov.qrcode.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonBack?.setOnClickListener{
            this.finish()
        }
    }
}