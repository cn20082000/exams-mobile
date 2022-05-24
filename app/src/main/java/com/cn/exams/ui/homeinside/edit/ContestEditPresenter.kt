package com.cn.exams.ui.homeinside.edit

import com.cn.exams.common.Constant
import com.cn.exams.core.BasePresenter
import com.cn.exams.lib.data.ErrorEnum
import com.cn.exams.util.enumi.ContestScopeEnum
import java.text.ParseException

class ContestEditPresenter(
    view: ContestEditContract.View,
    private val mode: Int,
    private val bankId: Long,
) : BasePresenter<ContestEditContract.View>(view), ContestEditContract.Presenter {

    private var infoExpanded = false

    override fun getBankInfo(bankId: Long) {
        dataManager.getBank(bankId)
            .onSuccess { view.getBankInfoSuccess(it) }
            .onFailure { view.getBankInfoFailed(it.error) }
            .call()
    }

    override fun infoToggle() {
        infoExpanded = !infoExpanded
        view.requestInfoToggle(infoExpanded)
    }

    override fun personalChange(isPublic: Boolean) {
        view.requestPersonalChange(isPublic)
    }

    override fun action(
        name: String?,
        password: String?,
        quantity: String?,
        startAt: String?,
        endAt: String?,
        isPublic: Boolean?
    ) {
        val nameC = name ?: ""
        val passwordC = password ?: ""
        val quantityC = quantity ?: "0"
        val startAtC = startAt ?: ""
        val endAtC = endAt ?: ""
        val isPublicC = isPublic ?: false

        view.requestAction()
        val startAtTime = run {
            try {
                Constant.dateFormat.parse(startAtC)
            } catch (ex: ParseException) {
                view.actionFailed(ErrorEnum.INVALID_START_FORMAT)
                return
            }
        }
        val endAtTime = run {
            try {
                Constant.dateFormat.parse(endAtC)
            } catch (ex: ParseException) {
                view.actionFailed(ErrorEnum.INVALID_END_FORMAT)
                return
            }
        }
        if (startAtTime.time >= endAtTime.time) {
            view.actionFailed(ErrorEnum.INVALID_END_BEFORE_START)
            return
        }

        if (mode == ContestEditFragment.MODE_ADD) {
            dataManager.createContest(
                nameC,
                passwordC,
                quantityC.toInt(),
                startAtTime,
                endAtTime,
                if (isPublicC) ContestScopeEnum.PUBLIC else ContestScopeEnum.PRIVATE,
                bankId
            ).onSuccess { view.actionSuccess(it) }
                .onFailure { view.actionFailed(it.error) }
                .call()
        }
    }
}