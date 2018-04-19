package com.yochio.copy_conference2018.presentation.sessions

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SimpleItemAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import com.yochio.copy_conference2018.R
import com.yochio.copy_conference2018.databinding.FragmentRoomSessionsBinding
import com.yochio.copy_conference2018.di.Injectable
import com.yochio.copy_conference2018.presentation.Result
import com.yochio.copy_conference2018.presentation.sessions.item.DateSessionsSection
import com.yochio.copy_conference2018.util.ProgressTimeLatch
import com.yochio.copy_conference2018.util.ext.observe
import com.yochio.copy_conference2018.util.ext.setLinearDivider
import com.yochio.model.Room
import javax.inject.Inject

/**
 * Created by yochio on 2018/03/09.
 */

class RoomSessionsFragment : Fragment(), Injectable {
    private lateinit var binding: FragmentRoomSessionsBinding
    private lateinit var roomName: String

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private val sessionsSection = DateSessionsSection()

    private val sessionsViewModel: RoomSessionsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(RoomSessionsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        roomName = arguments!!.getString(ARG_ROOM_NAME)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRoomSessionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecylerView()

        val progressTimeLatch = ProgressTimeLatch {
            binding.progress.visibility = if (it) View.VISIBLE else View.GONE
        }

        sessionsViewModel.roomName = roomName
        sessionsViewModel.sessions.observe(this, { result ->
            when (result) {
                is Result.Success -> {
                    val sessions = result.data
                    sessionsSection.updateSessions(sessions)
                    sessionsViewModel.onSuccessFetchSessions()
                }
                is Result.Failure -> {
                    println(result.e.message)
                }
            }
        })

        sessionsViewModel.isLoading.observe(this, { isLoading ->
            progressTimeLatch.loading = isLoading ?: false
        })

        sessionsViewModel.refreshFocusCurrentSession.observe(this, {
            if (it != true) return@observe
        })

    }

    private fun setupRecylerView() {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            add(sessionsSection)
        }

        binding.sessionsRecycler.apply {
            adapter = groupAdapter
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

            setLinearDivider(R.drawable.shape_divider_vertical_12dp, layoutManager as LinearLayoutManager)
        }
    }

    companion object {
        private const val ARG_ROOM_NAME = "room_name"

        fun newInstance(room: Room): RoomSessionsFragment = RoomSessionsFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_ROOM_NAME, room.name)
            }
        }
    }
}