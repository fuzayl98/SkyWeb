package com.fuzaylofficial.skyweb.ui.adapters;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class CustomBindingAdapter {

    @BindingAdapter("bindImage")
    public static void setImageUrl(ImageView view, String url) {
        if (url == null) {
            view.setImageDrawable(null);
        } else {
            Glide.with(view).load(url).transition(DrawableTransitionOptions.withCrossFade(1000)).into(view);
        }
    }
}
