package com.example.opencv

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.opencv.databinding.ActivityMainBinding
import org.opencv.android.BaseLoaderCallback
import org.opencv.android.LoaderCallbackInterface
import org.opencv.android.OpenCVLoader

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

        initButtons()

    }

    private fun initButtons() {

        // 이미지 변환 프래그먼트
        binding.convertImageBtn.setOnClickListener {
            moveFragment(ConvertImageFragment())
        }

        // 이미지 이진화 프래그먼트
        binding.convertBinaryBtn.setOnClickListener {
            moveFragment(ConvertBinaryFragment())
        }

        // 이미지 위에 그리기 프래그먼트
        binding.drawingImageBtn.setOnClickListener {
            moveFragment(DrawingFragment())
        }

        // 외곽선 추출 프래그먼트
        binding.detectCannyBtn.setOnClickListener {
            moveFragment(EdgeFragment())
        }

        // 이미지 밝기 조절 프래그먼트
        binding.setBrightnessBtn.setOnClickListener {
            moveFragment(BrightnessFragment())
        }

        // 두 이미지 간 차이 구하기 프래그먼트
        binding.getDiffOperationBtn.setOnClickListener {
            moveFragment(DiffFragment())
        }

        // 색상 추출 프래그먼트
        binding.extractFragmentBtn.setOnClickListener {
            moveFragment(ExtractFragment())
        }

    }

    private fun moveFragment(frg: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentView, frg).commit()
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