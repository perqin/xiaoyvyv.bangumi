package com.xiaoyv.common.widget.card

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.xiaoyv.common.api.parser.entity.HomeImageCardEntity
import com.xiaoyv.common.databinding.ViewHomeCardBinding
import com.xiaoyv.common.databinding.ViewHomeCardItemBinding
import com.xiaoyv.common.kts.loadImageAnimate
import com.xiaoyv.common.kts.setOnDebouncedItemClickListener
import com.xiaoyv.widget.binder.BaseQuickBindingAdapter
import com.xiaoyv.widget.binder.BaseQuickBindingHolder
import com.xiaoyv.widget.callback.setOnFastLimitClickListener
import com.xiaoyv.widget.kts.loadImage

/**
 * Class: [HomeCardView]
 *
 * @author why
 * @since 11/24/23
 */
class HomeCardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {
    private val binding = ViewHomeCardBinding.inflate(LayoutInflater.from(context), this)
    private val itemAdapter by lazy { ItemAdapter() }

    var data: HomeImageCardEntity? = null
        set(value) {
            field = value
            refreshCardImages()
        }

    var onItemClick: (HomeImageCardEntity.HomeImageEntity) -> Unit = {}

    init {
        binding.rvSmall.adapter = itemAdapter
        itemAdapter.setOnDebouncedItemClickListener {
            onItemClick(it)
        }
    }

    private fun refreshCardImages() {
        val images = data?.images.orEmpty()
        if (images.isEmpty()) return

        val bigCard = images.first()
        binding.cardTitle.text = data?.title
        binding.cardBigTitle.text = bigCard.title
        binding.cardBigAttention.text = bigCard.attention
        binding.cardBig.loadImageAnimate(bigCard.image)
        binding.cardBig.setOnFastLimitClickListener {
            onItemClick(bigCard)
        }

        if (images.size > 1) {
            itemAdapter.submitList(images.subList(1, images.size))
        }
    }

    class ItemAdapter :
        BaseQuickBindingAdapter<HomeImageCardEntity.HomeImageEntity, ViewHomeCardItemBinding>() {
        override fun BaseQuickBindingHolder<ViewHomeCardItemBinding>.converted(item: HomeImageCardEntity.HomeImageEntity) {
            binding.cardSmall.loadImage(item.image)
            binding.cardSmallTitle.text = item.title
            binding.cardSmallAttention.text = item.attention
        }
    }
}