package com.xiaoyv.common.config.annotation

import androidx.annotation.StringDef

/**
 * Class: [TimelineType]
 *
 * @author why
 * @since 11/25/23
 */
@StringDef(
    TimelineType.TYPE_ALL,
    TimelineType.TYPE_SAY,
    TimelineType.TYPE_SUBJECT,
    TimelineType.TYPE_PROGRESS,
    TimelineType.TYPE_BLOG,
    TimelineType.TYPE_MONO,
    TimelineType.TYPE_RELATION,
    TimelineType.TYPE_GROUP,
    TimelineType.TYPE_WIKI,
    TimelineType.TYPE_INDEX,
    TimelineType.TYPE_WINDOW,
)
@Retention(AnnotationRetention.SOURCE)
annotation class TimelineType {
    companion object {
        const val TYPE_ALL = ""
        const val TYPE_SAY = "say"
        const val TYPE_SUBJECT = "subject"
        const val TYPE_PROGRESS = "process"
        const val TYPE_BLOG = "blog"
        const val TYPE_MONO = "mono"
        const val TYPE_RELATION = "relation"
        const val TYPE_GROUP = "group"
        const val TYPE_WIKI = "wiki"
        const val TYPE_INDEX = "index"
        const val TYPE_WINDOW = "doujin"
    }
}