package com.example.android_app.page_events

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_app.databinding.ItemEventBinding
import com.example.android_app.databinding.ItemEventExpandedBinding
import com.example.android_app.model.Event
import com.squareup.picasso.Picasso

sealed class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    protected var position: Int? = null

    open fun setData(position: Int, event: Event) {
        this.position = position
    }

    protected fun setImageUrl(imageView: ImageView, imageUrl: String) {
        if (imageUrl.isEmpty()) return
        Picasso.get()
            .load(imageUrl)
            .fit()
            .centerCrop()
            .into(imageView)
    }

    class Collapsed(
        private val binding: ItemEventBinding,
        private val callback: Callback
    ) : EventViewHolder(binding.root) {

        init {
            binding.card.setOnClickListener {
                position?.let { callback.openEventDetails(it) }
            }
        }

        override fun setData(position: Int, event: Event) {
            super.setData(position, event)
            setImageUrl(binding.eventImage, event.imageUrl)
            binding.eventName.text = event.name
            binding.eventDate.text = event.date
        }
    }

    class Expanded(
        private val binding: ItemEventExpandedBinding,
        private val callback: Callback
    ) : EventViewHolder(binding.root) {

        init {
            binding.closeButton.setOnClickListener {
                callback.closeEventDetails()
            }
        }

        override fun setData(position: Int, event: Event) {
            super.setData(position, event)
            setImageUrl(binding.eventImage, event.imageUrl)
            binding.eventName.text = event.name
            binding.eventDate.text = event.date
            binding.eventPlace.text = event.place
            binding.eventDescription.text = event.description
            binding.eventGallery.adapter = GalleryAdapter(event.galleryImagesUrls)
        }
    }

    interface Callback {

        fun openEventDetails(position: Int)

        fun closeEventDetails()
    }
}