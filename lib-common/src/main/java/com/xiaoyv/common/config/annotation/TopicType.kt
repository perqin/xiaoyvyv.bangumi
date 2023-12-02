package com.xiaoyv.common.config.annotation

import androidx.annotation.StringDef

/**
 * Class: [TopicType]
 *
 * @author why
 * @since 11/25/23
 */
@StringDef(
    TopicType.TYPE_UNKNOWN,
    TopicType.TYPE_EP,
    TopicType.TYPE_GROUP,
    TopicType.TYPE_PERSON,
    TopicType.TYPE_CRT,
    TopicType.TYPE_SUBJECT
)
@Retention(AnnotationRetention.SOURCE)
annotation class TopicType {
    companion object {
        const val TYPE_UNKNOWN = ""
        const val TYPE_EP = "ep"
        const val TYPE_GROUP = "group"
        const val TYPE_PERSON = "prsn"
        const val TYPE_CRT = "crt"
        const val TYPE_SUBJECT = "subject"
    }
}
