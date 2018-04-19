package com.yochio.copy_conference2018.presentation.search.item

import android.databinding.DataBindingUtil
import android.view.View
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder
import com.yochio.copy_conference2018.R
import com.yochio.copy_conference2018.databinding.ItemTopicBinding
import com.yochio.copy_conference2018.util.lang
import com.yochio.model.Lang
import com.yochio.model.Topic

/**
 * Created by yochio on 2018/03/06.
 */

data class TopicItem(val topic: Topic) : BindableItem<ItemTopicBinding>(topic.id.toLong()) {
    override fun createViewHolder(itemView: View): ViewHolder<ItemTopicBinding> {
        val viewDataBinding = DataBindingUtil.bind<ItemTopicBinding>(itemView)
        return ViewHolder(viewDataBinding)
    }

    override fun bind(viewBinding: ItemTopicBinding, position: Int) {
        if (lang() == Lang.JA) {
            viewBinding.topicName.text = topic.getNameByLang(Lang.JA)
            viewBinding.topicTranslation.text = topic.getNameByLang(Lang.EN)
        } else {
            viewBinding.topicName.text = topic.getNameByLang(Lang.EN)
            viewBinding.topicTranslation.text = topic.getNameByLang(Lang.JA)
        }
    }

    override fun getLayout(): Int = R.layout.item_topic
}