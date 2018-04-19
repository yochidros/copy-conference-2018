package com.yochio.copy_conference2018.presentation.sessions

import android.app.Activity
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.media.MediaCas
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yochio.copy_conference2018.FragmentStateNullablePagerAdapter
import com.yochio.copy_conference2018.R.id.room
import com.yochio.copy_conference2018.TopActivity
import com.yochio.copy_conference2018.databinding.FragmentSessionsBinding
import com.yochio.copy_conference2018.di.Injectable
import com.yochio.copy_conference2018.presentation.Result
import com.yochio.copy_conference2018.presentation.common.fragment.Findable
import com.yochio.copy_conference2018.presentation.sessions.item.SimpleSessionSection
import com.yochio.copy_conference2018.util.ProgressTimeLatch
import com.yochio.copy_conference2018.util.ext.observe
import com.yochio.model.Room
import javax.inject.Inject

/**
 * Created by yochio on 2018/03/07.
 */

class SessionsFragment : Fragment(), Injectable, Findable {
    private lateinit var binding: FragmentSessionsBinding
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var sessionsViewPagerAdapter: SessionsViewPagerAdapter

    private lateinit var sessionsViewModel: SessionsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSessionsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sessionsViewPagerAdapter = SessionsViewPagerAdapter(childFragmentManager, activity!!)

        binding.sessionsViewPager.adapter = sessionsViewPagerAdapter

        sessionsViewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(SessionsViewModel::class.java)

        val progressTimeLatch = ProgressTimeLatch {
            binding.progress.visibility = if (it) View.VISIBLE else View.GONE
        }

        sessionsViewModel.rooms.observe(this, { result ->
            when (result) {
                is Result.Success -> {
                    sessionsViewPagerAdapter.setRooms(result.data)
                }
            }
        })

        sessionsViewModel.isLoading.observe(this, { isLoading ->
            progressTimeLatch.loading = isLoading ?: false
        })

        sessionsViewModel.refreshResult.observe(this, { result ->
            when (result) {
                is Result.Failure -> {
                    Snackbar.make(view, "Failed to fetch sessions", Snackbar.LENGTH_LONG).apply {
                        setAction("Retry") {
                            sessionsViewModel.onRetrySessions()
                        }
                    }.show()
                }
            }
        })

        lifecycle.addObserver(sessionsViewModel)

        binding.tabLayout.setupWithViewPager(binding.sessionsViewPager)
    }

    override val tagForFinding: String = TopActivity.BottomNavigationItem.SESSION.name

    companion object {
        fun newInstance(): SessionsFragment = SessionsFragment()
    }
}

class SessionsViewPagerAdapter(
        fragmentManager: FragmentManager,
        private val activity: Activity
) : FragmentStateNullablePagerAdapter(fragmentManager) {

    private val tabs = arrayListOf<Tab>()
    private var roomTabs = mutableListOf<Tab.RoomTab>()

    private fun setupTabs() {
        tabs.clear()
        tabs.add(Tab.All)
        tabs.addAll(roomTabs)
        notifyDataSetChanged()
    }

    sealed class Tab(val title: String) {
        object All : Tab("All")
        data class RoomTab(val room: Room) : Tab(room.name)
    }

    override fun getPageTitle(position: Int): CharSequence = tabs[position].title

    override fun getCount(): Int = tabs.size

    override fun getItem(position: Int): Fragment {
        val tab = tabs[position]
        return when (tab) {
            Tab.All -> {
                AllSessionsFragment.newInstance()
            }
            is Tab.RoomTab -> {
                RoomSessionsFragment.newInstance(tab.room)
            }
        }
    }

    fun setRooms(rooms: List<Room>) {
        if (rooms == roomTabs.map { it.room }) {
            return
        }
        roomTabs = rooms.map {
            Tab.RoomTab(it)
        }.toMutableList()

        setupTabs()
    }

}