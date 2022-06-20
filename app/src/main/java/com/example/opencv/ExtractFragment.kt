package com.example.opencv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.opencv.ImgUtil.Companion.convertImageType
import com.example.opencv.ImgUtil.Companion.extractColors
import com.example.opencv.ImgUtil.Companion.toBitmap
import com.example.opencv.ImgUtil.Companion.toMat
import com.example.opencv.databinding.FragmentExtractBinding
import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.core.Scalar
import org.opencv.imgproc.Imgproc

/**
 * * Core.inRange()
 *
 * - H : 색상
 * - S : 채도(색 깊이?)
 * - V : 명도(밝기)
 *
 * @param src 원본 이미지
 * @param lowerb 최소 색상값
 * @param upperb 최대 색상값
 */
class ExtractFragment : Fragment() {

    companion object { private const val TAG = "ExtractFragment" }

    private val binding by lazy { FragmentExtractBinding.inflate(layoutInflater) }

    lateinit var src: Mat

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        initViews()
        initButtons()

        return binding.root
    }

    private fun initViews() {

        val originBitmap = ContextCompat.getDrawable(requireContext(), R.drawable.emd1)?.toBitmap()
        val mat = originBitmap?.toMat()?: Mat()
        src = mat.convertImageType(Imgproc.COLOR_BGR2HSV)

        val lowerb = Scalar(0.0, 0.0, 0.0)
        val upperb = Scalar(255.0, 255.0, 255.0)

        binding.imageView1.setImageBitmap(originBitmap)
        binding.imageView2.setImageBitmap(src.extractColors(lowerb, upperb).toBitmap())

        binding.rangeH.apply { setValues(valueFrom, valueTo) }
        binding.rangeS.apply { setValues(valueFrom, valueTo) }
        binding.rangeV.apply { setValues(valueFrom, valueTo) }
    }

    private fun initButtons() {

        binding.rangeH.addOnChangeListener { slider, value, fromUser ->
            extractColor(src)
        }
        binding.rangeS.addOnChangeListener { slider, value, fromUser ->
            extractColor(src)
        }
        binding.rangeV.addOnChangeListener { slider, value, fromUser ->
            extractColor(src)
        }

    }

    private fun extractColor(src: Mat) {

        val h = binding.rangeH.values
        val s = binding.rangeS.values
        val v = binding.rangeV.values

        val lowerb = Scalar(h[0].toDouble(), s[0].toDouble(), v[0].toDouble())
        val upperb = Scalar(h[1].toDouble(), s[1].toDouble(), v[1].toDouble())
        val result = src.extractColors(lowerb, upperb)

        binding.imageView2.setImageBitmap(result.toBitmap())
    }

}