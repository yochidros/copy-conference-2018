package com.yochio.copy_conference2018.presentation.search


import android.app.Activity
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SimpleItemAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import com.yochio.copy_conference2018.FragmentStateNullablePagerAdapter
import com.yochio.copy_conference2018.R
import com.yochio.copy_conference2018.databinding.FragmentSearchBinding
import com.yochio.copy_conference2018.presentation.sessions.item.SimpleSessionSection
import com.yochio.copy_conference2018.util.ext.setLinearDivider

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SearchFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding

    private val sessionsSection = SimpleSessionSection()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            add(sessionsSection)
        }

        binding.sessionsRecycler.apply {
            adapter = groupAdapter
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSearchBeforeTabs()
        setupRecyclerView()
    }

    private fun setupSearchBeforeTabs() {
        binding.sessionsViewPager.adapter =
                SearchBeforeViewPagerAdapter(activity!!, childFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.sessionsViewPager)
    }

    companion object {
        fun newInstance(): SearchFragment = SearchFragment()
    }
}

class SearchBeforeViewPagerAdapter(
        private val activity: Activity,
        fragmentManager: FragmentManager
) : FragmentStateNullablePagerAdapter(fragmentManager) {

    enum class Tab(@StringRes val title: Int) {
        Session(R.string.search_before_tab_session),
        Topic(R.string.search_before_tab_topic);
    }

    override fun getPageTitle(position: Int): CharSequence = activity.getString(Tab.values()[position].title)

    override fun getItem(position: Int): Fragment {
        val tab = Tab.values()[position]

        return when(tab) {
            Tab.Session -> SearchSessionsFragment.newInstance()
            Tab.Topic -> SearchTopicsFragment.newInstance()
        }
    }

    override fun getCount(): Int = SearchBeforeViewPagerAdapter.Tab.values().size

    override fun setPrimaryItem(container: ViewGroup, position: Int, o: Any?) {
        super.setPrimaryItem(container, position, o)
    }
}