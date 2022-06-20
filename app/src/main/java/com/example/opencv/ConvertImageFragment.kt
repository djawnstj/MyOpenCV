package com.example.opencv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.opencv.ImgUtil.Companion.convertImageType
import com.example.opencv.ImgUtil.Companion.toBitmap
import com.example.opencv.ImgUtil.Companion.toMat
import com.example.opencv.databinding.FragmentConvertImageBinding
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc

class ConvertImageFragment : Fragment() {

    companion object { private const val TAG = "ConvertImageFragment" }

    private val binding by lazy { FragmentConvertImageBinding.inflate(layoutInflater) }

    private lateinit var src: Mat

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        initViews()

        initButtons()

        return binding.root
    }

    private fun initViews() {

        val bitmap = ContextCompat.getDrawable(requireContext(), R.drawable.emd1)?.toBitmap()
        src = bitmap?.toMat() ?: Mat()

        binding.imageView.setImageBitmap(bitmap)

    }

    private fun initButtons() {

        binding.bgraBtn.setOnClickListener {
            val result = src.convertImageType(Imgproc.COLOR_RGB2BGRA)
            binding.imageView.setImageBitmap(result.toBitmap())
        }

        binding.rgbaBtn.setOnClickListener {
            val result = src.convertImageType(Imgproc.COLOR_RGB2RGBA)
            binding.imageView.setImageBitmap(result.toBitmap())
        }

        binding.grayBtn.setOnClickListener {
            val result = src.convertImageType(Imgproc.COLOR_RGB2GRAY)
            binding.imageView.setImageBitmap(result.toBitmap())
        }

        binding.xyzBtn.setOnClickListener {
            val result = src.convertImageType(Imgproc.COLOR_RGB2XYZ)
            binding.imageView.setImageBitmap(result.toBitmap())
        }

        binding.yccBtn.setOnClickListener {
            val result = src.convertImageType(Imgproc.COLOR_RGB2YCrCb)
            binding.imageView.setImageBitmap(result.toBitmap())
        }

        binding.hsvBtn.setOnClickListener {
            val result = src.convertImageType(Imgproc.COLOR_RGB2HSV)
            binding.imageView.setImageBitmap(result.toBitmap())
        }

        binding.labBtn.setOnClickListener {
            val result = src.convertImageType(Imgproc.COLOR_RGB2Lab)
            binding.imageView.setImageBitmap(result.toBitmap())
        }

        binding.luvBtn.setOnClickListener {
            val result = src.convertImageType(Imgproc.COLOR_RGB2Luv)
            binding.imageView.setImageBitmap(result.toBitmap())
        }

        binding.hlsBtn.setOnClickListener {
            val result = src.convertImageType(Imgproc.COLOR_RGB2HLS)
            binding.imageView.setImageBitmap(result.toBitmap())
        }

        binding.yuvBtn.setOnClickListener {
            val result = src.convertImageType(Imgproc.COLOR_RGB2YUV)
            binding.imageView.setImageBitmap(result.toBitmap())
        }

    }

}