package com.example.opencv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.opencv.ImgUtil.Companion.setImageMat
import com.example.opencv.ImgUtil.Companion.toBitmap
import com.example.opencv.ImgUtil.Companion.toMat
import com.example.opencv.databinding.FragmentDiffBinding
import org.opencv.core.Mat

class DiffFragment : Fragment() {

    companion object { private const val TAG = "DiffFragment" }

    private val binding by lazy { FragmentDiffBinding.inflate(layoutInflater) }

    private lateinit var src1: Mat
    private lateinit var src2: Mat

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        initData()

        initButtons()

        return binding.root
    }

    private fun initData() {
        src1 = ContextCompat.getDrawable(requireContext(), R.drawable.card_symbol)?.toBitmap()?.toMat() ?: Mat()
        src2 = ContextCompat.getDrawable(requireContext(), R.drawable.card_diff)?.toBitmap()?.toMat() ?: Mat()
    }

    private fun initButtons() {

        binding.getDiff.setOnClickListener {
            binding.imageView.setImageMat(ImgUtil.getDiffOperation(src1, src2))
        }

    }

}