package com.example.opencv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.opencv.databinding.ActivityMainBinding
import org.opencv.android.BaseLoaderCallback
import org.opencv.android.LoaderCallbackInterface
import org.opencv.android.OpenCVLoader
import org.opencv.core.Mat
import org.opencv.core.Point
import org.opencv.core.Scalar
import org.opencv.imgproc.Imgproc

class MainActivity : AppCompatActivity() {

    companion object { private const val TAG = "MainActivity" }

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val mLoaderCallback: BaseLoaderCallback = object : BaseLoaderCallback(this) {
        override fun onManagerConnected(status: Int) {
            when (status) {
                SUCCESS -> {
                    println("OpenCV loaded successfully")
                }
                else -> {
                    super.onManagerConnected(status)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }

    /**
     * 이미지 변환 함수
     * @param Mat [Mat]
     * @param code [Imgproc.code]
     * @return [Mat]
     * @author EJS
     */
    fun Mat.convertToGrayScaleImage(code: Int): Mat {
        val cvtMat = Mat()
        Imgproc.cvtColor(this, cvtMat, code)
        return cvtMat
    }

    /**
     * 이미지 전역 이진화 함수
     * @param Mat [Mat]
     * @return [Mat]
     * @author EJS
     */
    fun Mat.convertToBinaryImage(): Mat {
        val binaryMat = Mat()
        Imgproc.threshold(this, binaryMat, 0.0, 255.0, Imgproc.THRESH_OTSU)
        return binaryMat
    }

    /**
     * 직선 그리기 함수
     */
    fun Mat.drawingLine () {
        Imgproc.line(this, Point(15.0, 20.0), Point(300.0, 350.0), Scalar(0.0, 255.0, 0.0), 10, 8)
    }

    override fun onResume() {
        super.onResume()
        if (!OpenCVLoader.initDebug()) {
            println("Internal OpenCV library not found. Using OpenCV Manager for initialization")
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback)
        } else {
            println("OpenCV library found inside package. Using it!")
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS)
        }
    }

    private fun println(msg: String?) {
        Log.d(TAG, msg ?: "msg is null")
    }

    private fun showToast(msg: String?) {
        Toast.makeText(this, msg ?: "msg is null", Toast.LENGTH_SHORT).show()
    }

}