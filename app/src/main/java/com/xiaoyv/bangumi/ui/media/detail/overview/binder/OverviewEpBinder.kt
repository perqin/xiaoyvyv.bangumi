package com.xiaoyv.bangumi.ui.media.detail.overview.binder

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.chad.library.adapter.base.BaseMultiItemAdapter
import com.xiaoyv.bangumi.R
import com.xiaoyv.bangumi.databinding.FragmentOverviewEpBinding
import com.xiaoyv.bangumi.databinding.FragmentOverviewEpItemBinding
import com.xiaoyv.common.api.parser.entity.MediaDetailEntity
import com.xiaoyv.common.config.annotation.InterestType
import com.xiaoyv.common.config.bean.AdapterTypeItem
import com.xiaoyv.common.helper.callback.IdDiffItemCallback
import com.xiaoyv.common.helper.callback.RecyclerItemTouchedListener
import com.xiaoyv.common.kts.CommonColor
import com.xiaoyv.common.kts.GoogleAttr
import com.xiaoyv.common.kts.forceCast
import com.xiaoyv.common.kts.inflater
import com.xiaoyv.common.kts.setOnDebouncedChildClickListener
import com.xiaoyv.common.kts.tint
import com.xiaoyv.widget.binder.BaseQuickBindingHolder
import com.xiaoyv.widget.binder.BaseQuickDiffBindingAdapter
import com.xiaoyv.widget.kts.getAttrColor
import com.xiaoyv.widget.kts.subListLimit


/**
 * Class: [OverviewEpBinder]
 *
 * @author why
 * @since 11/30/23
 */
class OverviewEpBinder(
    private val touchedListener: RecyclerItemTouchedListener,
    private val clickItemListener: (MediaDetailEntity.MediaProgress) -> Unit,
) : BaseMultiItemAdapter.OnMultiItemAdapterListener<AdapterTypeItem, BaseQuickBindingHolder<FragmentOverviewEpBinding>> {

    /**
     * 最大显示 48 个
     */
    private val subSize = 24

    private val itemAdapter by lazy {
        ItemAdapter().apply {
            setOnDebouncedChildClickListener(R.id.item_ep, block = clickItemListener)
        }
    }

    override fun onBind(
        holder: BaseQuickBindingHolder<FragmentOverviewEpBinding>,
        position: Int,
        item: AdapterTypeItem?,
    ) {
        item ?: return
        holder.binding.tvTitleEp.title = item.title

        item.entity.forceCast<MediaDetailEntity>().apply {
            holder.binding.pbMedia.max = totalProgress
            holder.binding.pbMedia.setProgress(myProgress, true)

            // 进度文字颜色
            if (totalProgress == 0 || myProgress < totalProgress / 2) {
                holder.binding.tvEpMyProgress.setTextColor(
                    holder.binding.pbMedia.context.getAttrColor(GoogleAttr.colorOnSurfaceVariant)
                )
            } else {
                holder.binding.tvEpMyProgress.setTextColor(
                    holder.binding.pbMedia.context.getAttrColor(GoogleAttr.colorOnPrimary)
                )
            }

            holder.binding.tvEpMyProgress.isVisible =
                (collectState.interest != InterestType.TYPE_UNKNOWN && collectState.interest != InterestType.TYPE_WISH)
            holder.binding.ivAdd.isVisible =
                holder.binding.tvEpMyProgress.isVisible && myProgress != totalProgress

            holder.binding.tvEpMyProgress.text =
                String.format("我的完成度：%d/%d", myProgress, totalProgress)

            // 设置进度格子
            itemAdapter.submitList(progressList.subListLimit(subSize))
            itemAdapter.notifyItemRangeChanged(0, itemAdapter.itemCount)
        }
    }

    override fun onCreate(
        context: Context,
        parent: ViewGroup,
        viewType: Int,
    ): BaseQuickBindingHolder<FragmentOverviewEpBinding> {
        val binding = FragmentOverviewEpBinding.inflate(context.inflater, parent, false)
        binding.rvEp.adapter = itemAdapter
        binding.rvEp.addOnItemTouchListener(touchedListener)
        return BaseQuickBindingHolder(binding)
    }

    /**
     * Class: [ItemEpAdapter]
     *
     * @param selectedMode 是否为完成格子时的选取模式
     */
    class ItemAdapter : BaseQuickDiffBindingAdapter<MediaDetailEntity.MediaProgress,
            FragmentOverviewEpItemBinding>(IdDiffItemCallback()) {

        override fun BaseQuickBindingHolder<FragmentOverviewEpItemBinding>.converted(item: MediaDetailEntity.MediaProgress) {
            binding.tvEp.text = item.number
            binding.tvEp.paintFlags = binding.tvEp.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            when {
                // 看过
                item.collectType == InterestType.TYPE_COLLECT -> {
                    binding.tvEp.backgroundTintList =
                        context.getColor(CommonColor.save_collect).tint
                    binding.tvEp.setTextColor(context.getColor(CommonColor.save_collect_text))
                }
                // 想看
                item.collectType == InterestType.TYPE_WISH -> {
                    binding.tvEp.backgroundTintList = context.getColor(CommonColor.save_wish).tint
                    binding.tvEp.setTextColor(context.getColor(CommonColor.save_wish_text))
                }
                // 抛弃
                item.collectType == InterestType.TYPE_DROPPED -> {
                    binding.tvEp.backgroundTintList =
                        context.getColor(CommonColor.save_dropped).tint
                    binding.tvEp.setTextColor(context.getColor(CommonColor.save_dropped_text))
                    binding.tvEp.paintFlags = binding.tvEp.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                }
                // 放送中
                item.isAiring -> {
                    binding.tvEp.setTextColor(context.getColor(CommonColor.state_airing_text))
                    binding.tvEp.backgroundTintList =
                        context.getColor(CommonColor.state_airing).tint
                }
                // 已播出
                item.isAired -> {
                    binding.tvEp.backgroundTintList =
                        context.getColor(CommonColor.state_aired).tint
                    binding.tvEp.setTextColor(context.getColor(CommonColor.state_aired_text))
                }
                // 未播出
                else -> {
                    binding.tvEp.setTextColor(context.getAttrColor(GoogleAttr.colorOnSurface))
                    binding.tvEp.backgroundTintList = ColorStateList.valueOf(
                        context.getAttrColor(GoogleAttr.colorSurfaceContainer)
                    )
                }
            }
        }
    }
}