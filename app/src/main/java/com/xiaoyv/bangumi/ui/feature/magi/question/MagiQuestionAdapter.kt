package com.xiaoyv.bangumi.ui.feature.magi.question

import com.xiaoyv.bangumi.databinding.FragmentMagiQuestionItemBinding
import com.xiaoyv.common.api.parser.entity.MagiQuestionEntity
import com.xiaoyv.common.helper.callback.IdDiffItemCallback
import com.xiaoyv.common.kts.GoogleAttr
import com.xiaoyv.common.kts.tint
import com.xiaoyv.widget.binder.BaseQuickBindingHolder
import com.xiaoyv.widget.binder.BaseQuickDiffBindingAdapter
import com.xiaoyv.widget.kts.getAttrColor

/**
 * Class: [MagiQuestionAdapter]
 *
 * @author why
 * @since 12/11/23
 */
class MagiQuestionAdapter : BaseQuickDiffBindingAdapter<MagiQuestionEntity.Option,
        FragmentMagiQuestionItemBinding>(IdDiffItemCallback()) {

    private var selectIndex = -1

    override fun onBindViewHolder(
        holder: BaseQuickBindingHolder<FragmentMagiQuestionItemBinding>,
        position: Int,
        item: MagiQuestionEntity.Option?
    ) {
        super.onBindViewHolder(holder, position, item)
        if (position == selectIndex) {
            holder.binding.tvOption.setTextColor(context.getAttrColor(GoogleAttr.colorOnPrimarySurface))
            holder.binding.tvOption.backgroundTintList =
                context.getAttrColor(GoogleAttr.colorPrimarySurface).tint
        } else {
            holder.binding.tvOption.setTextColor(context.getAttrColor(GoogleAttr.colorOnSurface))
            holder.binding.tvOption.backgroundTintList =
                context.getAttrColor(GoogleAttr.colorSurfaceContainer).tint
        }
    }

    override fun BaseQuickBindingHolder<FragmentMagiQuestionItemBinding>.converted(item: MagiQuestionEntity.Option) {
        binding.tvOption.text = item.label
    }

    fun select(it: MagiQuestionEntity.Option) {
        val oldIndex = selectIndex
        selectIndex = itemIndexOfFirst(it)
        if (oldIndex != -1) notifyItemChanged(oldIndex)
        if (selectIndex != -1) notifyItemChanged(selectIndex)
    }
}