package com.orbitalsonic.sharedviewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.orbitalsonic.sharedviewmodel.databinding.FragmentFavouriteBinding


class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>() {

    // 1st way
    private val sharedViewModel: SharedViewModel by activityViewModels()

    // 2nd way
    private lateinit var sharedViewModelFav: SharedViewModel2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return getPersistentView(inflater, container, savedInstanceState, R.layout.fragment_favourite)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!hasInitializedRootView) {
            hasInitializedRootView = true

            binding.btnRecent.setOnClickListener {
                if (findNavController().currentDestination?.id == R.id.favouriteFragment){
                    findNavController().navigate(R.id.action_favouriteFragment_to_recentFragment)
                }
            }
        }

        sharedViewModel.message.observe(viewLifecycleOwner) {
            binding.tvFavouriteMessage.text = it
        }

        sharedViewModelFav = ViewModelProvider(requireActivity()).get(SharedViewModel2::class.java)
        sharedViewModelFav.sendMessage("Favourite Send Message")
        sharedViewModelFav.message.observe(viewLifecycleOwner, Observer {
            binding.tvFav2ndMessage.text = it
        })

    }

}