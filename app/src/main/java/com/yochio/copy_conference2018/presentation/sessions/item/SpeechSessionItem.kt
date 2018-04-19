package com.yochio.copy_conference2018.presentation.sessions.item

import com.xwray.groupie.databinding.BindableItem
import com.yochio.copy_conference2018.R
import com.yochio.copy_conference2018.databinding.ItemSpeechSessionBinding
import com.yochio.model.Session

/**
 * Created by yochio on 2018/03/08.
 */

data class SpeechSessionItem(
        override val session: Session.SpeechSession,
        private val simplify: Boolean = false,
        private val userIdInDetail: String? = null
) : BindableItem<ItemSpeechSessionBinding>(
        session.id.toLong()
), SessionItem {
    override fun bind(viewBinding: ItemSpeechSessionBinding, position: Int) {
        viewBinding.session = session
        viewBinding.simplify = simplify

    }

    override fun getLayout(): Int = R.layout.item_speech_session

}