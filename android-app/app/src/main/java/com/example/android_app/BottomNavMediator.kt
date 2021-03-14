package com.example.android_app

import android.graphics.Typeface
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.android_app.databinding.ActivityMainBinding
import com.example.android_app.page_department.DepartmentFragment
import com.example.android_app.page_events.EventsFragment
import com.example.android_app.page_messages.MessagesFragment
import com.example.android_app.page_timeline.TimelineFragment

class BottomNavMediator(
    private val binding: ActivityMainBinding,
    private val fragmentManager: FragmentManager
) {

    private val eventsFragment by lazy { EventsFragment() }
    private val messagesFragment by lazy { MessagesFragment() }
    private val departmentFragment by lazy { DepartmentFragment() }
    private val timelineFragment by lazy { TimelineFragment() }

    private var currentPage = Page.EVENTS

    init {
        registerNavBarListeners()
        changePage(Page.EVENTS)
    }

    private fun registerNavBarListeners() = binding.run {
        navEvents.setOnClickListener { changePage(Page.EVENTS) }
        navMessages.setOnClickListener { changePage(Page.MESSAGES) }
        navDepartment.setOnClickListener { changePage(Page.DEPARTMENT) }
        navTimeline.setOnClickListener { changePage(Page.TIMELINE) }
    }

    private fun changePage(newPage: Page) {
        changeSelectedItem(newPage)
        changeDisplayedPage(newPage)
        currentPage = newPage
    }

    private fun changeSelectedItem(newPage: Page) {
        currentPage.getNavItemText().setUnselected()
        newPage.getNavItemText().setSelected()
    }

    private fun changeDisplayedPage(newPage: Page) {
        fragmentManager.commit {
            replace(R.id.fragments_frame, newPage.getFragment())
            setReorderingAllowed(true)
        }
    }

    private fun Page.getNavItemText(): TextView {
        return when (this) {
            Page.EVENTS -> binding.navEventsText
            Page.MESSAGES -> binding.navMessagesText
            Page.DEPARTMENT -> binding.navDepartmentText
            Page.TIMELINE -> binding.navTimelineText
        }
    }

    private fun Page.getFragment(): Fragment {
        return when (this) {
            Page.EVENTS -> eventsFragment
            Page.MESSAGES -> messagesFragment
            Page.DEPARTMENT -> departmentFragment
            Page.TIMELINE -> timelineFragment
        }
    }

    private fun TextView.setSelected() {
        setTypeface(null, Typeface.BOLD)
    }

    private fun TextView.setUnselected() {
        setTypeface(null, Typeface.NORMAL)
    }

    private enum class Page {
        EVENTS, MESSAGES, DEPARTMENT, TIMELINE
    }
}