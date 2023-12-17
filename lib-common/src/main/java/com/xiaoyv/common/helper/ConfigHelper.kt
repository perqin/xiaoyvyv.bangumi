package com.xiaoyv.common.helper

import com.blankj.utilcode.util.SPStaticUtils

/**
 * Class: [ConfigHelper]
 *
 * @author why
 * @since 12/10/23
 */
object ConfigHelper {
    private const val KEY_BAIDU_TRANSLATE_APP_ID = "baidu-translate-app-id"
    private const val KEY_BAIDU_TRANSLATE_APP_SECRET = "baidu-translate-secret"
    private const val KEY_ROBOT_DISABLE = "robot-disable"
    private const val KEY_ROBOT_VOICE_DISABLE = "robot-voice-disable"
    private const val KEY_IMAGE_ANIMATION = "image-animation"
    private const val KEY_IMAGE_COMPRESS = "image-compress"

    fun configBaiduTranslateId(appId: String) {
        SPStaticUtils.put(KEY_BAIDU_TRANSLATE_APP_ID, appId)
    }

    fun configBaiduTranslateSecret(secret: String) {
        SPStaticUtils.put(KEY_BAIDU_TRANSLATE_APP_SECRET, secret)
    }

    fun readBaiduTranslateConfig(): Pair<String, String> {
        return SPStaticUtils.getString(KEY_BAIDU_TRANSLATE_APP_ID).orEmpty() to
                SPStaticUtils.getString(KEY_BAIDU_TRANSLATE_APP_SECRET).orEmpty()
    }

    fun isRobotDisable(): Boolean {
        return SPStaticUtils.getBoolean(KEY_ROBOT_DISABLE, true)
    }

    fun setRobotDisable(disable: Boolean) {
        return SPStaticUtils.put(KEY_ROBOT_DISABLE, disable)
    }

    fun isRobotVoiceDisable(): Boolean {
        return SPStaticUtils.getBoolean(KEY_ROBOT_VOICE_DISABLE, true)
    }

    fun setRobotVoiceDisable(disable: Boolean) {
        return SPStaticUtils.put(KEY_ROBOT_VOICE_DISABLE, disable)
    }

    fun isImageAnimation(): Boolean {
        return SPStaticUtils.getBoolean(KEY_IMAGE_ANIMATION, false)
    }

    fun setImageAnimation(enable: Boolean) {
        return SPStaticUtils.put(KEY_IMAGE_ANIMATION, enable)
    }

    fun isImageCompress(): Boolean {
        return SPStaticUtils.getBoolean(KEY_IMAGE_COMPRESS, true)
    }

    fun setImageCompress(enable: Boolean) {
        return SPStaticUtils.put(KEY_IMAGE_COMPRESS, enable)
    }

}