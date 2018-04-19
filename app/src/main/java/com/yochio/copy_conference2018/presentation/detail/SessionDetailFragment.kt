package com.yochio.copy_conference2018.presentation.detail

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yochio.copy_conference2018.databinding.FragmentSessionDetailBinding
import com.yochio.copy_conference2018.di.ViewModelFactory
import com.yochio.copy_conference2018.presentation.Result
import com.yochio.copy_conference2018.util.ext.observe
import com.yochio.model.Session
import javax.inject.Inject

/**
 * Created by yochio on 2018/03/09.
 */

class SessionDetailFragment : Fragment() {

    private lateinit var binding: FragmentSessionDetailBinding
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private val sessionDetailViewModel: SessionDetailViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SessionDetailViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSessionDetailBinding.inflate(inflater, container!!, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       val sessionId = arguments!!.getString(EXTRA_SESSION_ID)
        sessionDetailViewModel.sessions.observe(this, { result ->
            when(result) {
                is Result.Success -> {
                    val sessions = result.data
                    val position = sessions.indexOfFirst { it.id == sessionId }

                }
            }
        })
    }

    private fun bindSession(session: Session.SpeechSession) {

    }

    companion object {
        const val EXTRA_SESSION_ID = "EXTRA_SESSION_ID"

        fun newInstance(sessionId: String): SessionDetailFragment = SessionDetailFragment().apply {
            arguments = Bundle().apply {
                putString(EXTRA_SESSION_ID, sessionId)
            }
        }
    }
}