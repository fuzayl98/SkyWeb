package com.fuzaylofficial.skyweb.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.fuzaylofficial.skyweb.R
import com.fuzaylofficial.skyweb.databinding.LoadStateItemBinding

class LoadStateViewHolder(parent: ViewGroup, retry: () -> Unit) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.load_state_item, parent, false)
) {
    private val binding = LoadStateItemBinding.bind(itemView)
    private val progressBar: ProgressBar = binding.loadProgress
    private val errorMsg: TextView = binding.loadError
    private val button: Button = binding.loadRetry

    fun bind(loadState: LoadState,retry: () -> Unit) {
        if (loadState is LoadState.Error) {
            errorMsg.text = loadState.error.localizedMessage
        }

        button.setOnClickListener { retry.invoke() }
        progressBar.isVisible = loadState is LoadState.Loading
        button.isVisible = loadState is LoadState.Error
        errorMsg.isVisible = loadState is LoadState.Error
    }
}