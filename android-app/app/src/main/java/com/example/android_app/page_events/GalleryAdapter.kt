package com.example.android_app.page_events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_app.databinding.ItemGalleryImageBinding

class GalleryAdapter(
    private val imagesUrls: List<String>
) : RecyclerView.Adapter<GalleryImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemGalleryImageBinding.inflate(inflater, parent, false)
        return GalleryImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryImageViewHolder, position: Int) {
        holder.setImage(imagesUrls[position])
    }

    override fun getItemCount(): Int {
        return imagesUrls.size
    }

}