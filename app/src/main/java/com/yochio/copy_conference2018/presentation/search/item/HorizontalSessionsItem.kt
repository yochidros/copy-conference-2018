package com.yochio.copy_conference2018.presentation.search.item

import android.support.v4.app.Fragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.Section
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder
import com.yochio.copy_conference2018.R
import com.yochio.copy_conference2018.databinding.ItemSearchHorizontalSessionsBinding
import com.yochio.model.Level
import com.yochio.model.Session

/**
 * Created by yochio on 2018/03/08.
 */

class HorizontalSessionsItem(
        val level: Level,
        var sessions: List<Session.SpeechSession>,
        private val fragment: Fragment
) : BindableItem<ItemSearchHorizontalSessionsBinding>(
        level.id.toLong()
) {

    private val section = Section()

    override fun bind(viewBinding: ItemSearchHorizontalSessionsBinding, position: Int) {
        println(sessions)
        val items = mutableListOf<Item<*>>()
        var groupAdapter = GroupAdapter<com.xwray.groupie.ViewHolder>().apply {
            add(section)
        }
        viewBinding.searchSessionRecycler.swapAdapter(groupAdapter, false)

        sessions.forEach {
            items.add(
                    HorizontalSessionItem(
                            it,
                            fragment
                    )
            )
        }
        section.update(items)
    }

    override fun getLayout(): Int = R.layout.item_search_horizontal_sessions

    override fun equals(other: Any?): Boolean {
        if (this == other) return true
        if (javaClass != other?.javaClass) return false

        other as HorizontalSessionsItem

        if (level != other.level) return false
        if (sessions != other.sessions) return false

        return true
    }

    override fun hashCode(): Int {
        var result = level.hashCode()
        result = 31 * result + sessions.hashCode()
        return result
    }
}
