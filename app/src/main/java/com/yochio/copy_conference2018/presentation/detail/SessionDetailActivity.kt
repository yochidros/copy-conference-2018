package com.yochio.copy_conference2018.presentation.detail

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.yochio.copy_conference2018.FragmentStateNullablePagerAdapter
import com.yochio.copy_conference2018.R
import com.yochio.copy_conference2018.databinding.ActivitySessionDetailBinding
import com.yochio.copy_conference2018.di.ViewModelFactory
import com.yochio.copy_conference2018.presentation.Result
import com.yochio.copy_conference2018.util.ext.observe
import com.yochio.model.Session
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class SessionDetailActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory


    private val sessionDetailViewModel: SessionDetailViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SessionDetailViewModel::class.java)
    }

    private val binding: ActivitySessionDetailBinding by lazy {
        DataBindingUtil
                .setContentView<ActivitySessionDetailBinding>(
                        this,
                        R.layout.activity_session_detail
                )
    }

    private val pagerAdapter = SessionDetailFragmentAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowTitleEnabled(false)
        }

        sessionDetailViewModel.sessions.observe(this, { result ->
            when (result) {
                is Result.Success -> {
                    val sessions = result.data
                    bindSessions(sessions)
                }
            }
        })
        binding.detailSessionsPager.adapter = pagerAdapter
    }

    private fun bindSessions(sessions: List<Session.SpeechSession>) {
        val fistAssign = pagerAdapter.sessions.isEmpty() && sessions.isNotEmpty()
        pagerAdapter.sessions = sessions
        if (fistAssign) {
            val sessionId = intent.getStringExtra(EXTRA_SESSION_ID)
            val position = sessions.indexOfFirst { it.id == sessionId }
            binding.detailSessionsPager
                    .setCurrentItem(position, false)
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    class SessionDetailFragmentAdapter(
            fragmentManager: FragmentManager
    ) : FragmentStateNullablePagerAdapter(fragmentManager) {
        var sessions: List<Session.SpeechSession> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


        override fun getItem(position: Int): Fragment {
            return SessionDetailFragment.newInstance(sessions[position].id)
        }

        override fun getCount(): Int = sessions.size

    }

    companion object {
        const val EXTRA_SESSION_ID = "EXTRA_SESSION_ID"
        fun start(context: Context, session: Session) {
            context.startActivity(createIntent(context, session.id))
        }

        fun createIntent(context: Context, sessionId: String): Intent {
            return Intent(context, SessionDetailActivity::class.java).apply {
                putExtra(EXTRA_SESSION_ID, sessionId)
            }
        }
    }
}
