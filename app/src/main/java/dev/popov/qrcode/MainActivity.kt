package dev.popov.qrcode

import android.os.Bundle
import android.util.Log
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.zxing.WriterException
import dev.popov.qrcode.constants.Constants
import dev.popov.qrcode.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(binding.root)
        generateQR(Constants.URL_QR_CODE)
        binding.buttonClose?.setOnClickListener{
            finish()
        }
        binding.generate?.setOnClickListener {
            generateQR("Done, it really works!!!")
        }
    }
    private fun generateQR(url: String){
        val qrGenerator = QRGEncoder(url, null, QRGContents.Type.TEXT, 900)
        try {
            val bMap = qrGenerator.bitmap
            binding.qrCode?.setImageBitmap(bMap)
        }catch (e: WriterException){
            Log.v("TAG", e.toString())
        }
    }
}