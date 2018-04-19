package com.yochio.copy_conference2018.presentation.search.item

import android.support.v4.app.Fragment
import android.view.View
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder
import com.yochio.copy_conference2018.R
import com.yochio.copy_conference2018.databinding.ItemHorizontalSessionBinding
import com.yochio.copy_conference2018.util.ViewSizeCalculator.calculateViewSizeByScreenAndCount
import com.yochio.copy_conference2018.util.ext.context
import com.yochio.copy_conference2018.util.ext.displaySize
import com.yochio.copy_conference2018.util.ext.getFloat
import com.yochio.model.Session

/**
 * Created by yochio on 2018/03/08.
 */

class HorizontalSessionItem(
        val session: Session.SpeechSession,
        private val fragment: Fragment
) : BindableItem<ItemHorizontalSessionBinding>(
        session.id.hashCode().toLong()
) {

    override fun bind(viewBinding: ItemHorizontalSessionBinding, position: Int) {
        viewBinding.session = session
    }

    override fun createViewHolder(itemView: View): ViewHolder<ItemHorizontalSessionBinding> {
        val viewHolder = super.createViewHolder(itemView)
        val binding = viewHolder.binding
        val width = calculateViewSizeByScreenAndCount(
                binding.context.displaySize().width,
                binding.context.resources.getFloat(R.dimen.horizontal_visible_item_count))
        itemView.layoutParams.width = width
        return viewHolder
    }

    override fun getLayout(): Int = R.layout.item_horizontal_session

    override fun equals(other: Any?): Boolean {
        if (this == other) return true
        if (javaClass != other?.javaClass) return false

        other as HorizontalSessionItem

        if (session != other.session) return false
        return true
    }

    override fun hashCode(): Int {
        return session.hashCode()
    }
}