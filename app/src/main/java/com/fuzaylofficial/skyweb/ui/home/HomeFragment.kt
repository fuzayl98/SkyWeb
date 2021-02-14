package com.fuzaylofficial.skyweb.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.fuzaylofficial.skyweb.ui.adapters.PictureAdapter
import com.fuzaylofficial.skyweb.ui.adapters.PictureDiffUtillCallback
import com.fuzaylofficial.skyweb.databinding.FragmentHomeBinding
import com.fuzaylofficial.skyweb.di.ViewModelFactory
import com.fuzaylofficial.skyweb.ui.adapters.PictureLoadStateAdapter
import com.fuzaylofficial.skyweb.ui.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    lateinit var homeBinding: FragmentHomeBinding
    lateinit var homeViewModel: HomeViewModel

    var pictureAdapter: PictureAdapter = PictureAdapter(PictureDiffUtillCallback())

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeBinding = FragmentHomeBinding.inflate(inflater,container,false)
        homeViewModel = ViewModelProvider(this,viewModelFactory).get(HomeViewModel::class.java)
        setUpViews()
        fetchPicturesObservable()
        return homeBinding.root
    }

    private fun fetchPictures() {
        lifecycleScope.launch {
            homeViewModel.fetchPictures().distinctUntilChanged().collectLatest {
                pictureAdapter.submitData(it)
            }
        }
    }

    //RxJava
    @SuppressLint("CheckResult")
    private fun fetchPicturesObservable() {
        homeViewModel.fetchPicturesObservable().subscribe {
            lifecycleScope.launch {
                pictureAdapter.submitData(it)
            }
        }
    }

    //LiveData
    private fun fetchPicturesLiveData() {
        homeViewModel.fetchPicturesLiveData().observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {
                pictureAdapter.submitData(it)
            }
        })
    }

    private fun setUpViews() {

        homeBinding.pictureRv.apply {
            layoutManager = GridLayoutManager(context, 1)
            setHasFixedSize(true)
            adapter = pictureAdapter.withLoadStateFooter(footer = PictureLoadStateAdapter{pictureAdapter.retry()})
        }

        lifecycleScope.launch {
            pictureAdapter.loadStateFlow.collectLatest {
                homeBinding.progressLoading.isVisible = it.refresh is LoadState.Loading
                homeBinding.retry.isVisible = it.refresh is LoadState.Error
                homeBinding.errorMsg.isVisible = it.refresh is LoadState.Error
            }
        }
        homeBinding.retry.setOnClickListener { pictureAdapter.refresh() }
    }

}