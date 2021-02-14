package com.fuzaylofficial.skyweb.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.fuzaylofficial.skyweb.databinding.PicturesItemBinding
import com.fuzaylofficial.skyweb.model.Picture

class PictureViewHolder(private val binding: PicturesItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun Bind(picture: Picture?) {
        binding.picturedata = picture
    }

}