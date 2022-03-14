package com.orbitalsonic.sharedviewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


open class BaseFragment<T : ViewDataBinding> : Fragment() {

    var hasInitializedRootView = false
    private var rootView: View? = null
    lateinit var binding: T

    fun getPersistentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
        layout: Int
    ): View? {
        if (rootView == null) {
            binding = DataBindingUtil.inflate(inflater, layout, container, false)
            rootView = binding.root
        } else {

            (rootView?.parent as? ViewGroup)?.removeView(rootView)
        }

        return rootView
    }

    fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}