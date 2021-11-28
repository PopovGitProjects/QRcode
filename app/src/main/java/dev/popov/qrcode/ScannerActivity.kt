package dev.popov.qrcode

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView

class ScannerActivity : AppCompatActivity(), ZBarScannerView.ResultHandler {
    private lateinit var zBarScannerView: ZBarScannerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        zBarScannerView = ZBarScannerView(this)
        setContentView(zBarScannerView)

    }

    override fun onPause() {
        super.onPause()
        zBarScannerView.stopCamera()
    }

    override fun onResume() {
        super.onResume()
        zBarScannerView.setResultHandler(this)
        zBarScannerView.startCamera()
    }

    override fun handleResult(resultScan: Result?) {
        val intent = Intent()
        intent.putExtra("key", resultScan?.contents)
        setResult(RESULT_OK, intent)
        finish()
    }
}