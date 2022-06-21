package com.example.opencv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.opencv.ImgUtil.Companion.convertImageType
import com.example.opencv.ImgUtil.Companion.detectCannyEdge
import com.example.opencv.ImgUtil.Companion.extractColors
import com.example.opencv.ImgUtil.Companion.toBitmap
import com.example.opencv.ImgUtil.Companion.toMat
import com.example.opencv.databinding.FragmentEdgeBinding
import org.opencv.core.Mat
import org.opencv.core.Scalar
import org.opencv.imgproc.Imgproc

class EdgeFragment : Fragment() {

    companion object { private const val TAG = "EdgeFragment" }

    private val binding by lazy { FragmentEdgeBinding.inflate(layoutInflater) }

    private lateinit var src: Mat

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        initViews()
        initButtons()

        return binding.root
    }

    private fun initViews() {

        val originBitmap = ContextCompat.getDrawable(requireContext(), R.drawable.emd1)?.toBitmap()
        src = originBitmap?.toMat()?.convertImageType(Imgproc.COLOR_RGB2GRAY)?: Mat()

        binding.edgeSlider.apply {
            setValues(valueFrom, valueTo)
            val bitmap = src.detectCannyEdge(valueFrom.toDouble(), valueTo.toDouble()).toBitmap()
            binding.imageView.setImageBitmap(bitmap)
        }

    }

    private fun initButtons() {

        binding.edgeSlider.addOnChangeListener { slider, value, fromUser ->
            val values = binding.edgeSlider.values
            val bitmap = src.detectCannyEdge(values[0].toDouble(), values[1].toDouble()).toBitmap()
            binding.imageView.setImageBitmap(bitmap)
        }

    }

}