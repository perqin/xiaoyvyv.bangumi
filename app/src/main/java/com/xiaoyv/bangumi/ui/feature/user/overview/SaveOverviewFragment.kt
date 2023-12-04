package com.xiaoyv.bangumi.ui.feature.user.overview

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.xiaoyv.bangumi.R
import com.xiaoyv.bangumi.databinding.FragmentUserOverviewBinding
import com.xiaoyv.bangumi.helper.RouteHelper
import com.xiaoyv.bangumi.ui.feature.user.UserViewModel
import com.xiaoyv.blueprint.base.binding.BaseBindingFragment
import com.xiaoyv.blueprint.kts.launchUI
import com.xiaoyv.common.api.parser.entity.MediaDetailEntity
import com.xiaoyv.common.kts.setOnDebouncedChildClickListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Class: [SaveOverviewFragment]
 *
 * @author why
 * @since 12/4/23
 */
class SaveOverviewFragment : BaseBindingFragment<FragmentUserOverviewBinding>() {

    private val itemAdapter by lazy { SaveOverviewAdapter() }
    private val activityViewModel: UserViewModel by activityViewModels()

    override fun initView() {
        binding.rvContent.adapter = itemAdapter
    }

    override fun initData() {

    }

    override fun initListener() {
        itemAdapter.setOnDebouncedChildClickListener(R.id.item_grid) {
            if (it is MediaDetailEntity.MediaRelative && it.id.isNotBlank()) {
                RouteHelper.jumpMediaDetail(it.id)
            }
        }
    }

    override fun LifecycleOwner.initViewObserver() {
        activityViewModel.onUserInfoLiveData.observe(this) {
            val entity = it ?: return@observe
            launchUI {
                itemAdapter.submitList(withContext(Dispatchers.IO) {
                    listOf(
                        entity.anime,
                        entity.book,
                        entity.music,
                        entity.game,
                        entity.real
                    ).filter { overview ->
                        overview.isEmpty.not()
                    }.flatMap { overview ->
                        val itemArr = arrayListOf<Any>()
                        itemArr.add(overview)
                        if (overview.doing.isNotEmpty()) {
                            itemArr.addAll(overview.doing.limitOrFill(6))
                        }
                        if (overview.collect.isNotEmpty()) {
                            itemArr.addAll(overview.collect.limitOrFill(6))
                        }
                        itemArr
                    }
                })

                binding.pbProgress.hide()
            }
        }
    }

    private fun List<MediaDetailEntity.MediaRelative>.limitOrFill(count: Int): List<MediaDetailEntity.MediaRelative> {
        val relatives = toMutableList()
        if (relatives.size > count) {
            return relatives.subList(0, count)
        }
        if (relatives.size == count) return relatives
        repeat(count - relatives.size) {
            relatives.add(MediaDetailEntity.MediaRelative())
        }
        return relatives
    }

    companion object {
        fun newInstance(): SaveOverviewFragment {
            return SaveOverviewFragment()
        }
    }
}
