package com.example.opencv

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.opencv.databinding.ActivityMainBinding
import org.opencv.android.BaseLoaderCallback
import org.opencv.android.LoaderCallbackInterface
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.core.*
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

    /** 비트맵으로 변환 함수 */
    fun Mat.toBitmap(): Bitmap {
        val bmp = Bitmap.createBitmap(this.cols(), this.rows(), Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(this, bmp)
        return bmp
    }

    /** 이미지 변환 함수 */
    fun Mat.convertToGrayScaleImage(code: Int): Mat {
        val cvtMat = Mat()
        Imgproc.cvtColor(this, cvtMat, code)
        return cvtMat
    }

    /** 이미지 전역 이진화 함수 */
    fun Mat.convertToBinaryImage(): Mat {
        val binaryMat = Mat()
        Imgproc.threshold(this, binaryMat, 0.0, 255.0, Imgproc.THRESH_OTSU)
        return binaryMat
    }

    /** 직선 그리기 함수 */
    fun Mat.drawingLine () {
        Imgproc.line(this, Point(15.0, 20.0), Point(300.0, 350.0), Scalar(0.0, 255.0, 0.0), 10, Imgproc.LINE_AA)
    }

    /** 사각형 그리는 함수 */
    fun Mat.drawingRectangle() {
        Imgproc.rectangle(this, Point(130.0, 50.0), Point(300.0, 280.0), Scalar(0.0, 255.0, 0.0))
        val rec = Rect(130, 50, 170, 230)
        Imgproc.rectangle(this, rec, Scalar(0.0, 255.0, 0.0))
    }

    /** 다각형 그리는 함수 */
    fun Mat.drawingPolylines() {
        val points: List<MatOfPoint> = listOf(
            MatOfPoint(Point(208.0, 71.0), Point(421.0, 161.0), Point(226.0, 232.0), Point(332.0, 52.0), Point(363.0, 250.0))
        )
        Imgproc.polylines(this, points, true, Scalar(0.0, 255.0, 0.0))
    }

    /** 원 그리는 함수 */
    fun Mat.drawingCircle() {
        Imgproc.circle(this, Point(130.0, 50.0), 10, Scalar(0.0, 255.0, 0.0))
    }

    /** 외곽선 추출 함수 */
    fun Mat.sample() {
        val mat = Mat()
        Imgproc.Canny(this, mat, 50.0, 100.0)
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