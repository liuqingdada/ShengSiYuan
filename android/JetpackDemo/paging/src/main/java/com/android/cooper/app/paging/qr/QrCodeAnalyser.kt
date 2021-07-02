package com.android.cooper.app.paging.qr

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

/**
 * Created by cooper
 * 2021/7/2.
 * Email: 1239604859@qq.com
 */
class QrCodeAnalyser(private val result: Result) : ImageAnalysis.Analyzer {
    companion object {
        private const val TAG = "QrCodeAnalyser"
    }

    private val options = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
        .build()

    private val scanner = BarcodeScanning.getClient(options)

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image ?: run {
            imageProxy.close()
            return
        }

        // 另外其他 fromxxx方法, 支持从文件, 图库等等方式生成 InputImage 对象
        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
        scanner.process(image)
            .addOnSuccessListener {
                if (it.isNotEmpty()) {
                    result.onSuccess(it[0], imageProxy)
                    scanner.close()
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "analyze error: ", it)
            }
            .addOnCompleteListener {
                imageProxy.close()
            }
    }

    fun interface Result {
        fun onSuccess(barcode: Barcode, imageProxy: ImageProxy)
    }
}