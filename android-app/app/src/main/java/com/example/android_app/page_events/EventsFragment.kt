package com.example.android_app.page_events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_app.DI
import com.example.android_app.databinding.FragmentEventsBinding

class EventsFragment : Fragment() {

    private val repository by lazy { DI.repository }

    private var binding: FragmentEventsBinding? = null
    private var eventsAdapter: EventsAdapter? = null
    private var onScrollPageLoader: OnScrollPageLoader? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEventsList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.eventsList?.removeOnScrollListener(onScrollPageLoader!!)
        binding = null
    }

    private fun setupEventsList() = binding?.let {
        eventsAdapter = EventsAdapter(it)
        it.eventsList.adapter = eventsAdapter
        repository.getAllEvents().asLiveData().observe(viewLifecycleOwner) { events ->
            eventsAdapter?.updateEvents(events)
        }

        val layoutManager = it.eventsList.layoutManager as LinearLayoutManager
        onScrollPageLoader = OnScrollPageLoader(layoutManager)
        it.eventsList.addOnScrollListener(onScrollPageLoader!!)
    }

}