package com.fuzaylofficial.skyweb.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ConcatAdapter
import com.fuzaylofficial.skyweb.R
import com.fuzaylofficial.skyweb.databinding.PicturesItemBinding
import com.fuzaylofficial.skyweb.model.Picture

class PictureAdapter(diffCallback: PictureDiffUtillCallback) : PagingDataAdapter<Picture, PictureViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.Bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: PicturesItemBinding = DataBindingUtil.inflate(inflater,
            R.layout.pictures_item, parent, false)
        return PictureViewHolder(binding)
    }

    fun withMySpecificFooter(
        footer: LoadStateAdapter<*>
    ): ConcatAdapter {
        addLoadStateListener { loadStates ->
            footer.loadState = when (loadStates.refresh) {
                is LoadState.NotLoading -> loadStates.append
                else -> loadStates.refresh
            }
        }
        return ConcatAdapter(this, footer)
    }

}