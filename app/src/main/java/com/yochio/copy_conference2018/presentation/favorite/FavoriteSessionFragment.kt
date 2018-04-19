package com.yochio.copy_conference2018.presentation.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yochio.copy_conference2018.databinding.FragmentFavoriteSessionsBinding
import com.yochio.copy_conference2018.util.ext.setVisible

/**
 * Created by yochio on 2018/03/05.
 */

class FavoriteSessionFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteSessionsBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFavoriteSessionsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.mysessionInactiveGroup.setVisible(true)
    }


    companion object {
        fun newInstance(): FavoriteSessionFragment = FavoriteSessionFragment()
    }
}