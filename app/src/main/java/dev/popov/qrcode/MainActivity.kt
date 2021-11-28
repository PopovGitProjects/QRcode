package dev.popov.qrcode

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.WriterException
import dev.popov.qrcode.constants.Constants
import dev.popov.qrcode.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var launcher: ActivityResultLauncher<Intent>? = null
    private var callback: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        generateQR(Constants.URL_QR_CODE)

        binding.buttonClose?.setOnClickListener{
            finish()
        }
        binding.generate?.setOnClickListener {
            if (binding.inputText?.text.toString() != ""){
                generateQR(binding.inputText?.text.toString())
            }
        }
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK){
                callback = result.data?.getStringExtra("key")
                binding.scannerResult?.text = callback
            }
        }
        binding.scanButton?.setOnClickListener {
            launcher?.launch(Intent(this, ScannerActivity::class.java))
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