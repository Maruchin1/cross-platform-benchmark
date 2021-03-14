package com.example.android_app

import android.graphics.Typeface
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.android_app.databinding.ActivityMainBinding

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
        currentPage.getNavItem().setUnselected()
        newPage.getNavItem().setSelected()
    }

    private fun changeDisplayedPage(newPage: Page) {
        fragmentManager.commit {
            replace(R.id.fragments_frame, newPage.getFragment())
            setReorderingAllowed(true)
        }
    }

    private fun Page.getNavItem(): TextView {
        return when (this) {
            Page.EVENTS -> binding.navEvents
            Page.MESSAGES -> binding.navMessages
            Page.DEPARTMENT -> binding.navDepartment
            Page.TIMELINE -> binding.navTimeline
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