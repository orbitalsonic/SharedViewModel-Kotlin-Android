package com.orbitalsonic.sharedviewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.orbitalsonic.sharedviewmodel.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return getPersistentView(inflater, container, savedInstanceState, R.layout.fragment_home)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!hasInitializedRootView) {
            hasInitializedRootView = true

            binding.btnFavourite.setOnClickListener {
                if (findNavController().currentDestination?.id == R.id.homeFragment){
                    findNavController().navigate(R.id.action_homeFragment_to_favouriteFragment)
                }
            }
        }

        sharedViewModel.sendMessage("Home Send Message")
        sharedViewModel.message.observe(viewLifecycleOwner) {
            binding.tvHomeMessage.text = it
        }

    }

}