package com.example.opencv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opencv.databinding.FragmentDiffBinding

class DiffFragment : Fragment() {

    companion object { private const val TAG = "DiffFragment" }

    private val binding by lazy { FragmentDiffBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return binding.root
    }

}