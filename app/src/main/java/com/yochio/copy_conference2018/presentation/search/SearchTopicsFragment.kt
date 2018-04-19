package com.yochio.copy_conference2018.presentation.search

import android.animation.Animator
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import com.yochio.copy_conference2018.databinding.FragmentSearchTopicsBinding
import com.yochio.copy_conference2018.di.Injectable
import com.yochio.copy_conference2018.presentation.Result
import com.yochio.copy_conference2018.presentation.search.item.TopicItem
import com.yochio.copy_conference2018.presentation.search.item.TopicsGroup
import com.yochio.copy_conference2018.util.ext.observe
import javax.inject.Inject

/**
 * Created by yochio on 2018/03/05.
 */

class SearchTopicsFragment : Fragment(), Injectable {

    private lateinit var binding: FragmentSearchTopicsBinding
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private val searchTopicsViewModel: SearchTopicsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SearchTopicsViewModel::class.java)
    }

    private val topicsGroup = TopicsGroup()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchTopicsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        searchTopicsViewModel.topics.observe(this, { result ->
            when(result) {
                is Result.Success -> {
                    topicsGroup.updateTopics(result.data)
                }
                is Result.Failure -> {
                    println(result.errorMessage)
                }
            }
        })
    }

    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            setOnItemClickListener { item, _ ->
                val topicItem = item as? TopicItem ?: return@setOnItemClickListener
            }
            add(topicsGroup)
        }
        val linearLayoutManager = LinearLayoutManager(context)
        binding.searchSessionRecycler.apply {
            layoutManager = linearLayoutManager
            adapter = groupAdapter
            addItemDecoration(DividerItemDecoration(context, linearLayoutManager.orientation))
        }
    }

    companion object {
        fun newInstance(): SearchTopicsFragment = SearchTopicsFragment()
    }
}
