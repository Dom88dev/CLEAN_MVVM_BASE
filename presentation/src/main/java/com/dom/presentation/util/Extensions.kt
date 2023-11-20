package com.dom.presentation.util

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationManagerCompat
import com.dom.presentation.R
import kotlin.math.roundToInt

object Extensions {
    fun Context.createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannelCompat
                .Builder(getString(R.string.default_notification_channel_id), NotificationManagerCompat.IMPORTANCE_DEFAULT)
                .setName("앱 알림")
                .setDescription(getString(R.string.notification_channel_desc))
                .build()


            //register the channel with the system
            with(NotificationManagerCompat.from(this)) {
                createNotificationChannel(channel)
            }
        }
    }

    // 앱 알림이 꺼져있는지 체크..(O 이상에서는 notificationChannel 이 추가되어서 각채널을 체크해 off 된 채널이 하나라도 있으면 false를 반환
    fun Context.areNotificationsEnabled(): Boolean {
        val notificationManagerCompat = NotificationManagerCompat.from(this)
        return when {
            notificationManagerCompat.areNotificationsEnabled().not() -> false
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
                notificationManagerCompat.notificationChannels.firstOrNull {
                    it.importance == NotificationManagerCompat.IMPORTANCE_NONE && it.id == getString(R.string.default_notification_channel_id)
                } == null
            }
            else -> true
        }
    }


    //region SharedPreferences
    inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    fun SharedPreferences.set(key: String, value: Any?) {
        when (value) {
            is String? -> edit { it.putString(key, value) }
            is Int -> edit { it.putInt(key, value.toInt()) }
            is Boolean -> edit { it.putBoolean(key, value) }
            is Float -> edit { it.putFloat(key, value.toFloat()) }
            is Long -> edit { it.putLong(key, value.toLong()) }
            else -> {}
        }
    }

    fun SharedPreferences.clear() {
        edit { it.clear() }
    }

    fun SharedPreferences.remove(key: String) {
        edit { it.remove(key) }
    }
    //endregionn

    inline fun <reified T : java.io.Serializable> Intent.serializableExtra(key: String): T? = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializableExtra(key, T::class.java)
        else -> getSerializableExtra(key) as? T
    }

    fun Context.dpToPx(dp: Float): Int {
        return (resources.displayMetrics.density * dp).roundToInt()
    }

    fun AppCompatActivity.getScreenHeight(): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = windowManager.currentWindowMetrics
            val insets = windowMetrics.windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.height() - insets.top - insets.bottom
        } else {
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.heightPixels
        }
    }


}