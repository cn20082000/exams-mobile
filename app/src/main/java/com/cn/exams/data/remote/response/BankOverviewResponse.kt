package com.cn.exams.data.remote.response

import android.os.Parcelable
import com.cn.exams.util.enumi.BankScopeEnum
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class BankOverviewResponse(
    val id: Long,
    val createdAt: Date,
    val updatedAt: Date,
    val name: String,
    val description: String?,
    val scope: BankScopeEnum,
    val owner: RegisterResponse
) : Parcelable
