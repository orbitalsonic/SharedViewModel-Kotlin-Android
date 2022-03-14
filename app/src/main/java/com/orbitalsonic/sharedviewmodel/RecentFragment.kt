package com.orbitalsonic.sharedviewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.orbitalsonic.sharedviewmodel.databinding.FragmentRecentBinding


class RecentFragment : BaseFragment<FragmentRecentBinding>() {

    // 2nd way
    private lateinit var sharedViewModelFav: SharedViewModel2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return getPersistentView(inflater, container, savedInstanceState, R.layout.fragment_recent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!hasInitializedRootView) {
            hasInitializedRootView = true

        }

        /**
         * we have created the object of SharedViewModel which is the same object as we are using the same single activity as an owner.
         * This is the reason it is shared.
         * Notice that we have used the requireActivity()
         */
        sharedViewModelFav = ViewModelProvider(requireActivity()).get(SharedViewModel2::class.java)
        sharedViewModelFav.message.observe(viewLifecycleOwner) {
            binding.tvRecentMessage.text = it
        }

    }

}