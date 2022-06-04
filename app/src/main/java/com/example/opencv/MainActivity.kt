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
     * # cvtColor
     *
     * *  BGR : BLUE, GREEN, RED
     * * BGRA : BGR + ALPHA(투명도)
     * * RGB : RED, GREEN, BLUE
     * * RGBA : RGB + ALPHA(투명도)
     * * GRAY : 회색조 이미지
     * * BGR565 : BGR 16비트 이미지
     * * XYZ : X, Y, Z채널 CIE XYZ(CIE 1931 색공간)
     * * YCrCb : Y(휘도 : 밝기), Cb/Cr(색채, 크로마 : 색상 성분)
     * * HSV : Hue(색상), Saturation(채도), Value(밝기)으로 변환
     * * Lab : CIE Lab으로 변환 : L(밝기), A: RED-GREEN 색상 정도, B : YELLOW-BLUE 색상 정도
     * * Luv : CIE Luv으로 변환
     * * HLS : Hue(색상), Lightness(밝기), Saturation(채도)
     * * YUV : Y(밝기), U(밝기와 파란색과의 색상 차), V(밝기와 빨간색과의 색상 차)
     * *
     * * BGR : Blue, Green, Red 채널 -
     * * BGRA : Blue, Green, Red, Alpha 채널 -
     * * RGB : Red, Green, Blue 채널 -
     * * RGBA : Red, Green, Blue, Alpha 채널 -
     * * GRAY : 단일 채널 - 그레이스케일
     * * BGR565 : Blue, Green, Red 채널 - 16 비트 이미지
     * * XYZ : X, Y, Z 채널 - CIE 1931 색 공간
     * * YCrCb : Y(휘도 : 밝기), Cr/Cb(색차 : 색상 성분) 채널 - YCC (크로마)
     * * HSV : Hue, Saturation, Value 채널 - 색상, 채도, 명도(밝기)
     * * Lab : L, a, b 채널	- 반사율, 색도1, 색도2
     * * Luv : L, u, v 채널	- CIE Luv
     * * HLS : Hue, Lightness, Saturation 채널 - 색상, 밝기, 채도
     * * YUV : Y, U, V 채널	- 밝기, 색상1, 색상2
     * * BG, GB, RG : 디모자이킹	- 단일 색상 공간으로 변경
     * * _EA : 디모자이킹 - 가장자리 인식
     * * _VNG : 디모자이킹 - 그라데이션 사용
     */
    lateinit var openCV: Mat

    /**
     * 이미지를 흑백으로 변환하는 함수
     * @param imageMat Mat()
     * @return Mat()
     * @author EJS
     */
    fun convertToGrayScaleImage(imageMat: Mat): Mat {
        val grayMat = Mat()
        Imgproc.cvtColor(imageMat, grayMat, Imgproc.COLOR_BGR2GRAY)
        return grayMat
    }

    /**
     * 이미지 이진화 함
     * @param imageMat Mat()
     * @return Mat()
     * @author EJS
     */
    fun convertToBinaryImage(imageMat: Mat): Mat {
        val binaryMat = Mat()
        Imgproc.threshold(imageMat, binaryMat, 0.0, 255.0, Imgproc.THRESH_OTSU)
        return binaryMat
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