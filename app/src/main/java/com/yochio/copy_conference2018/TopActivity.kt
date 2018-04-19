package com.yochio.copy_conference2018

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.annotation.MenuRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import com.yochio.copy_conference2018.databinding.ActivityTopBinding
import com.yochio.copy_conference2018.di.ViewModelFactory
import com.yochio.copy_conference2018.util.ext.elevationForPostLollipop
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class TopActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject lateinit var navigationController: NavigationController

    private val binding: ActivityTopBinding by lazy {
        DataBindingUtil.setContentView<ActivityTopBinding>(this, R.layout.activity_top)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)

        setupBottomNavigation(savedInstanceState)
    }

    private fun setupBottomNavigation(savedInstanceState: Bundle?) {

//        binding.bottomNavigation.disableShiftMode()
        binding.bottomNavigation.itemIconTintList = null

        binding.bottomNavigation.setOnNavigationItemSelectedListener({ item ->
            val navigationItem = BottomNavigationItem.forId(item.itemId)

            setupToolbar(navigationItem)

            navigationItem.navigate(navigationController)
             true
        })

        if (savedInstanceState == null) {
            binding.bottomNavigation.selectedItemId = R.id.navigation_session
        }

        binding.bottomNavigation.setOnNavigationItemReselectedListener { item ->
            val navigationItem = BottomNavigationItem.forId(item.itemId)
            val fragment = supportFragmentManager.findFragmentByTag(navigationItem.name)
            if (fragment is BottomNavigationItem.OnSelectedListener) {
                fragment.onReselected()
            }
        }

    }

    private fun setupToolbar(navigationItem: BottomNavigationItem) {
        binding.toolbar.elevationForPostLollipop = if (navigationItem.isUserToolbarElevation) {
            resources.getDimensionPixelSize(R.dimen.elevation_app_bar).toFloat()
        } else {
            0F
        }

        supportActionBar?.apply {
            title = if ( navigationItem.imageRes != null) {
                setDisplayShowHomeEnabled(true)
                setIcon(navigationItem.imageRes)
                null
            } else {
                setDisplayShowHomeEnabled(false)
                setIcon(null)
                getString(navigationItem.titleRes!!)
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        setupToolbar(BottomNavigationItem.forId(binding.bottomNavigation.selectedItemId))
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    enum class BottomNavigationItem(
            @MenuRes val menuId: Int,
            @StringRes val titleRes: Int?,
            @DrawableRes val imageRes: Int?,
            val isUserToolbarElevation: Boolean,
            val navigate: NavigationController.() -> Unit
    ) {
        SESSION(R.id.navigation_session, R.string.session_title, null, false, {
            navigateToAllSessions()
        }),
        SEARCH(R.id.navigation_search, R.string.search_title, null, false, {
            navigateToSearchSessions()
        }),
        FAVORITE(R.id.navigation_favorite, R.string.favorite_title, null, false, {
            navigateToFavoriteSessions()
        }),
        FEED(R.id.navigation_feed, R.string.feed_title, null, false, {});

        interface OnSelectedListener {
            fun onReselected()
        }

        companion object {
            fun forId(@IdRes id: Int): BottomNavigationItem {
                return values().first { it.menuId == id }
            }
        }
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, TopActivity::class.java)

        fun start(context: Context) {
            createIntent(context).let {
                context.startActivity(it)
            }
        }
    }
}
