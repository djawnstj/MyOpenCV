package com.example.opencv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.opencv.ImgUtil.Companion.setBrightness
import com.example.opencv.ImgUtil.Companion.toBitmap
import com.example.opencv.ImgUtil.Companion.toMat
import com.example.opencv.databinding.FragmentBrightnessBinding
import org.opencv.core.Mat

class BrightnessFragment : Fragment() {

    companion object { private const val TAG = "BrightnessFragment" }

    private val binding by lazy { FragmentBrightnessBinding.inflate(layoutInflater) }

    private lateinit var src: Mat

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        initView()

        initButtons()

        return binding.root
    }

    private fun initView() {

        val bitmap = ContextCompat.getDrawable(requireContext(), R.drawable.emd1)?.toBitmap()
        src = bitmap?.toMat() ?: Mat()

        binding.imageView.setImageBitmap(bitmap)

    }

    private fun initButtons() {

        binding.brightBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                val result = src.setBrightness(p1)
                binding.imageView.setImageBitmap(result.toBitmap())
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })

    }

}