package com.yochio.copy_conference2018

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.yochio.copy_conference2018.presentation.common.fragment.Findable
import com.yochio.copy_conference2018.presentation.detail.SessionDetailActivity
import com.yochio.copy_conference2018.presentation.favorite.FavoriteSessionFragment
import com.yochio.copy_conference2018.presentation.search.SearchFragment
import com.yochio.copy_conference2018.presentation.sessions.AllSessionsFragment
import com.yochio.copy_conference2018.presentation.sessions.SessionsFragment
import com.yochio.model.Session
import javax.inject.Inject

/**
 * Created by yochio on 2018/03/02.
 */

class NavigationController @Inject constructor(private val activity: AppCompatActivity) {

    private val containerId: Int = R.id.content

    private val fragmentManager: FragmentManager = activity.supportFragmentManager


    fun navigateToAllSessions() {
        replaceFragment(SessionsFragment.newInstance())
    }

    fun navigateToFavoriteSessions() {
        replaceFragment(FavoriteSessionFragment.newInstance())
    }

    fun navigateToSearchSessions() {
        replaceFragment(SearchFragment.newInstance())
    }

    fun navigateToSessionDetailActivity(session: Session) {
        SessionDetailActivity.start(activity, session)
    }

    private fun replaceFragment(fragment: Fragment) {
        fragmentManager
                .beginTransaction()
                .replace(containerId, fragment, (fragment as? Findable)?.tagForFinding)
                .commitAllowingStateLoss()
    }
}