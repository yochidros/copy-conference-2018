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
import com.yochio.copy_conference2018.NavigationController
import com.yochio.copy_conference2018.R
import com.yochio.copy_conference2018.databinding.FragmentAllSessionsBinding
import com.yochio.copy_conference2018.di.Injectable
import com.yochio.copy_conference2018.presentation.Result
import com.yochio.copy_conference2018.presentation.sessions.item.DateSessionsSection
import com.yochio.copy_conference2018.presentation.sessions.item.SpeechSessionItem
import com.yochio.copy_conference2018.util.ProgressTimeLatch
import com.yochio.copy_conference2018.util.ext.observe
import com.yochio.copy_conference2018.util.ext.setLinearDivider
import kotlinx.android.synthetic.main.activity_top.*
import javax.inject.Inject

/**
 * Created by yochio on 2018/03/05.
 */

class AllSessionsFragment: Fragment(), Injectable {

    private lateinit var binding: FragmentAllSessionsBinding
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var navigationController: NavigationController

    private val sessionsSection = DateSessionsSection()

    private val sessionsViewModel: AllSessionsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(AllSessionsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAllSessionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecycerView()

        val progressTimeLatch = ProgressTimeLatch {
            binding.progress.visibility = if (it) View.VISIBLE else View.GONE
        }
        sessionsViewModel.sessions.observe(this, { result ->
            when (result) {
                is Result.Success -> {
                    val sessions = result.data

                    sessionsSection.updateSessions(sessions)
                    sessionsViewModel.onSuccessFetchSessions()
                }
                is Result.Failure -> {
                    println(result.e)
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

    private fun setupRecycerView() {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            add(sessionsSection)
            setOnItemClickListener({ item, _ ->
                val sessionItem = item as? SpeechSessionItem ?: return@setOnItemClickListener
                navigationController.navigateToSessionDetailActivity(sessionItem.session)
            })
        }

        binding.sessionsRecycler.apply {
            adapter = groupAdapter
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

            setLinearDivider(R.drawable.shape_divider_vertical_12dp, layoutManager as LinearLayoutManager)
        }
    }
    companion object {
        fun newInstance(): AllSessionsFragment = AllSessionsFragment()
    }
}