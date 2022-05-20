package com.cn.exams.lib.mess

import android.app.Activity
import androidx.annotation.ColorRes
import com.cn.exams.R
import com.google.android.material.snackbar.Snackbar

object Mess {

    fun success(activity: Activity, message: String, isLong: Boolean = false) =
        makeText(R.color.success, activity, message, isLong)

    fun error(activity: Activity, message: String, isLong: Boolean = false) =
        makeText(R.color.error, activity, message, isLong)

    fun warning(activity: Activity, message: String, isLong: Boolean = false) =
        makeText(R.color.warning, activity, message, isLong)

    fun info(activity: Activity, message: String, isLong: Boolean = false) =
        makeText(R.color.info, activity, message, isLong)

    private fun makeText(
        @ColorRes color: Int,
        activity: Activity,
        message: String,
        isLong: Boolean = false
    ) {
        Snackbar.make(
            activity.findViewById(android.R.id.content),
            message,
            if (isLong) Snackbar.LENGTH_LONG else Snackbar.LENGTH_SHORT
        ).setBackgroundTint(activity.resources.getColor(color, null)).show()
    }

}