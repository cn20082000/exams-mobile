package com.cn.exams.util

import androidx.fragment.app.FragmentActivity
import java.util.*
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

fun Date?.toCalendar(): Calendar {
    val cal = Calendar.getInstance()
    if (this.isNull()) return cal
    cal.time = this
    return cal
}

/**
 * Check null cho 1 biến bất kì
 *
 * Created at 5/7/2022 by NCChinh
 */
@OptIn(ExperimentalContracts::class)
fun <T> T.isNull(): Boolean {
    contract {
        returns(false) implies (this@isNull != null)
    }
    if (this != null) return false
    return true
}

/**
 * Check không null cho 1 biến bất kì
 *
 * Created at 5/7/2022 by NCChinh
 */
@OptIn(ExperimentalContracts::class)
fun <T> T.isNotNull(): Boolean {
    contract {
        returns(true) implies (this@isNotNull != null)
    }
    if (this != null) return true
    return false
}

