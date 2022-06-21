package com.example.opencv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opencv.databinding.FragmentDrawingBinding
import com.example.opencv.util.DrawingView.*

class DrawingFragment : Fragment() {

    companion object { private const val TAG = "DrawingFragment" }

    private val binding by lazy { FragmentDrawingBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        initViews()

        initButtons()

        return binding.root
    }

    private fun initViews() {

        binding.drawingView.shapeType = ShapeType.LINE

    }

    private fun initButtons() {

        binding.line.setOnClickListener { binding.drawingView.shapeType = ShapeType.LINE }

        binding.rect.setOnClickListener { binding.drawingView.shapeType = ShapeType.RECTANGLE }

        binding.circle.setOnClickListener { binding.drawingView.shapeType = ShapeType.CIRCLE }

    }

}