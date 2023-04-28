package com.aliozdemir.artbooktesting.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aliozdemir.artbooktesting.ArtBookApplication
import com.aliozdemir.artbooktesting.R
import com.aliozdemir.artbooktesting.adapter.ArtRecyclerAdapter
import com.aliozdemir.artbooktesting.databinding.FragmentArtsBinding
import com.aliozdemir.artbooktesting.viewmodel.ArtViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ArtFragment @Inject constructor(val artRecyclerAdapter: ArtRecyclerAdapter) : Fragment(R.layout.fragment_arts) {

    private var fragmentBinding : FragmentArtsBinding? = null


    //val viewModel: ArtViewModel by viewModels()
    //private val viewModel: ArtViewModel by viewModels()
    lateinit var viewModel : ArtViewModel




    private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) : Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition = viewHolder.layoutPosition
            val selectedArt = artRecyclerAdapter.arts[layoutPosition]
            viewModel.deleteArt(selectedArt)
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(requireActivity()).get(ArtViewModel::class.java)


        val binding = FragmentArtsBinding.bind(view)
        fragmentBinding = binding

        subscribeToObservers()

        fragmentBinding!!.recyclerViewArt.adapter = artRecyclerAdapter
        fragmentBinding!!.recyclerViewArt.layoutManager = LinearLayoutManager(requireContext())
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(fragmentBinding!!.recyclerViewArt)

        fragmentBinding!!.fab.setOnClickListener{
            findNavController().navigate(ArtFragmentDirections.actionArtFragmentToArtDetailsFragment())
        }

    }


    private fun subscribeToObservers() {
        viewModel.artList.observe(viewLifecycleOwner, Observer {
            artRecyclerAdapter.arts = it
        })
    }


    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }

}