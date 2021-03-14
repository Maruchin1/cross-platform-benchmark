package com.example.android_app.page_events

import androidx.recyclerview.widget.RecyclerView
import com.example.android_app.databinding.ItemGalleryImageBinding
import com.squareup.picasso.Picasso

class GalleryImageViewHolder(
    private val binding: ItemGalleryImageBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun setImage(imageUrl: String) {
        if (imageUrl.isEmpty()) return
        Picasso.get()
            .load(imageUrl)
            .fit()
            .centerCrop()
            .into(binding.image)
    }
}