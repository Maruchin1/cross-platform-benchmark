package com.example.android_app.page_events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.android_app.Repository
import com.example.android_app.databinding.FragmentEventsBinding
import com.example.android_app.model.Event

class EventsFragment : Fragment() {

    private val eventsAdapter by lazy { EventsAdapter() }

    private var binding: FragmentEventsBinding? = null

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
        binding = null
    }

    private fun setupEventsList() {
        binding?.eventsList?.adapter = eventsAdapter
        Repository.getAllEvents().asLiveData().observe(viewLifecycleOwner) {
            eventsAdapter.updateEvents(it)
        }
    }

}