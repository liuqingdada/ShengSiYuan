package com.android.cooper.app.paging.qr

import android.os.Bundle
import android.util.Log
import android.util.Size
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import com.android.common.utils.SerialExecutor
import com.android.cooper.app.paging.R
import com.android.cooper.app.paging.databinding.ActivityVisionBinding
import com.android.lib.uicommon.viewBinding
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions

/**
 * Created by cooper
 * 2021/6/22.
 * Email: 1239604859@qq.com
 */
class VisionActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "VisionActivity"
    }

    private val binding by viewBinding<ActivityVisionBinding>()

    private val processCameraProvider by lazy {
        ProcessCameraProvider.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vision)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        reqPermission()
    }

    private fun reqPermission() {
        XXPermissions.with(this)
            .permission(Permission.CAMERA)
            .request { _, all ->
                if (all) {
                    openCamera()
                }
            }
    }

    private fun openCamera() {
        binding.preview.run {
            post {
                val cameraProvider = processCameraProvider.get()
                val preview = Preview.Builder().build()
                preview.setSurfaceProvider(surfaceProvider)

                val cameraSelector = CameraSelector.Builder()
                    .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                    .build()

                val imageAnalysis = ImageAnalysis.Builder()
                    .setTargetResolution(Size(width, height))
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()

                imageAnalysis.setAnalyzer(
                    SerialExecutor.THREAD_POOL_EXECUTOR,
                    QrCodeAnalyser { barcode, imageProxy ->
                        cameraProvider.unbindAll()
                        Log.d(TAG, "analyse: ${barcode.rawValue}")
                    })

                val camera = cameraProvider.bindToLifecycle(
                    this@VisionActivity,
                    cameraSelector,
                    imageAnalysis,
                    preview
                )
            }
        }
    }
}