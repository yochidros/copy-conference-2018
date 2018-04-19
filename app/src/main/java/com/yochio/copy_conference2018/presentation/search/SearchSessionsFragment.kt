package com.yochio.copy_conference2018.presentation.search

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import com.yochio.copy_conference2018.databinding.FragmentSearchSessionsBinding
import com.yochio.copy_conference2018.di.Injectable
import com.yochio.copy_conference2018.presentation.Result
import com.yochio.copy_conference2018.presentation.search.item.LevelSessionsSection
import com.yochio.copy_conference2018.util.ext.observe
import javax.inject.Inject


/**
 * Created by yochio on 2018/03/08.
 */

class SearchSessionsFragment : Fragment(), Injectable {
    private lateinit var binding: FragmentSearchSessionsBinding
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private val sessionSection = LevelSessionsSection(this)

    private val searchSessionsViewModel: SearchSessionsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SearchSessionsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchSessionsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        searchSessionsViewModel.levelSessions.observe(this, { result ->
           when (result) {
               is Result.Success -> {
                   val levelSessions = result.data
                   sessionSection.updateSections(levelSessions)
               }
               is Result.Failure -> {
                   println(result.errorMessage)
               }
           }
        })
    }

    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            add(sessionSection)
        }
        binding.searchSessionRecycler.apply {
            adapter = groupAdapter
        }
    }

    companion object {
        fun newInstance(): SearchSessionsFragment = SearchSessionsFragment()
    }
}