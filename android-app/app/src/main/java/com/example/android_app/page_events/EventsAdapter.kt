package com.example.android_app.page_events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_app.databinding.ItemEventBinding
import com.example.android_app.model.Event
import com.squareup.picasso.Picasso

class EventsAdapter : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    private val eventsList = mutableListOf<Event>()

    fun updateEvents(list: List<Event>) {
        eventsList.clear()
        eventsList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEventBinding.inflate(inflater, parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventsList[position]
        holder.run {
            setImageUrl(event.imageUrl)
            setName(event.name)
            setDate(event.date)
        }
    }

    class EventViewHolder(
        private val binding: ItemEventBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setImageUrl(imageUrl: String) {
            if (imageUrl.isEmpty()) return
            Picasso.get()
                .load(imageUrl)
                .fit()
                .centerCrop()
                .into(binding.eventImage)
        }

        fun setName(name: String) {
            binding.eventName.text = name
        }

        fun setDate(date: String) {
            binding.eventDate.text = date
        }
    }
}