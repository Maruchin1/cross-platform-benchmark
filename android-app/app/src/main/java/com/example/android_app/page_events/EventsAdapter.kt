package com.example.android_app.page_events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.example.android_app.databinding.FragmentEventsBinding
import com.example.android_app.databinding.ItemEventBinding
import com.example.android_app.databinding.ItemEventExpandedBinding
import com.example.android_app.model.Event

class EventsAdapter(
    private val binding: FragmentEventsBinding
) : RecyclerView.Adapter<EventViewHolder>(), EventViewHolder.Callback {

    private val eventsList = mutableListOf<Event>()
    private var expandedItemPosition: Int? = null

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
        return when (viewType) {
            COLLAPSED -> createCollapsedViewHolder(inflater, parent)
            EXPANDED -> createExpandedViewHolder(inflater, parent)
            else -> throw IllegalStateException("Not matching view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == expandedItemPosition) EXPANDED else COLLAPSED
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.setData(position, eventsList[position])
    }

    override fun openEventDetails(position: Int) {
        val layoutManager = binding.eventsList.layoutManager as LinearLayoutManager
        layoutManager.scrollToPositionWithOffset(position, -200)
        TransitionManager.beginDelayedTransition(binding.eventsList)
        expandedItemPosition = position
        notifyDataSetChanged()
    }

    override fun closeEventDetails() {
        TransitionManager.beginDelayedTransition(binding.eventsList)
        expandedItemPosition = null
        notifyDataSetChanged()
    }

    private fun createCollapsedViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): EventViewHolder {
        val binding = ItemEventBinding.inflate(inflater, parent, false)
        return EventViewHolder.Collapsed(binding, this)
    }

    private fun createExpandedViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): EventViewHolder {
        val binding = ItemEventExpandedBinding.inflate(inflater, parent, false)
        return EventViewHolder.Expanded(binding, this)
    }

    companion object {
        private const val COLLAPSED = 1
        private const val EXPANDED = 2
    }
}