package com.example.opencv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import com.example.opencv.ImgUtil.Companion.convertImageType
import com.example.opencv.ImgUtil.Companion.convertToBinaryImage
import com.example.opencv.ImgUtil.Companion.toBitmap
import com.example.opencv.ImgUtil.Companion.toMat
import com.example.opencv.databinding.FragmentConvertBinaryBinding
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc

class ConvertBinaryFragment : Fragment() {

    companion object { private const val TAG = "ConvertBinaryFragment" }

    private val binding by lazy { FragmentConvertBinaryBinding.inflate(layoutInflater) }

    private lateinit var src: Mat

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        initViews()

        initButtons()

        return binding.root
    }

    private fun initViews() {

        src = ContextCompat.getDrawable(requireContext(), R.drawable.emd1)?.toBitmap()?.toMat()?.convertImageType(Imgproc.COLOR_RGB2GRAY) ?: Mat()

        val result = src.convertToBinaryImage(0.0, Imgproc.THRESH_BINARY)

        binding.imageView.setImageBitmap(result.toBitmap())

    }

    private fun initButtons() {

        binding.valueBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                val result = src.convertToBinaryImage(p1.toDouble(), Imgproc.THRESH_BINARY)

                binding.imageView.setImageBitmap(result.toBitmap())
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

    }

}