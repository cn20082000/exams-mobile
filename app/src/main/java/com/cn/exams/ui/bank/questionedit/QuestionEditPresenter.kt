package com.cn.exams.ui.bank.questionedit

import android.util.Log
import com.cn.exams.core.BasePresenter
import com.cn.exams.data.remote.response.QuestionResponse

class QuestionEditPresenter(
    view: QuestionEditContract.View,
    private val mode: Int,
    private val bankId: Long,
    private val question: QuestionResponse?
) : BasePresenter<QuestionEditContract.View>(view), QuestionEditContract.Presenter {

    override fun addAnswer() {
        view.requestAddAnswer()
    }

    override fun action(content: String?, explanation: String?) {
        Log.e("Question content", content.toString())
        Log.e("Question expla", explanation.toString())
        Log.e("Answer list", view.requestAnswerList().toString())
        Log.e("Correct ans", view.requestCorrectAnswer().toString())
    }

    override fun action(
        content: String?,
        explanation: String?,
        answer1: String?,
        answer2: String?,
        answer3: String?,
        answer4: String?
    ) {
        val contentC = content ?: ""
        val explanationC = explanation ?: ""
        val answer1C = answer1 ?: ""
        val answer2C = answer2 ?: ""
        val answer3C = answer3 ?: ""
        val answer4C = answer4 ?: ""
        val correctAns = view.requestCorrectAnswer()

        if (mode == QuestionEditFragment.MODE_ADD) {
            dataManager.createQuestion(
                bankId,
                contentC,
                explanationC,
                correctAns,
                answer1C,
                answer2C,
                answer3C,
                answer4C
            ).onSuccess { view.actionSuccess() }
                .onFailure { view.actionFailed(it.error) }
                .call()
        } else if (mode == QuestionEditFragment.MODE_EDIT) {
            question?.let {
                dataManager.updateQuestion(
                    it.id,
                    contentC,
                    explanationC,
                    correctAns,
                    getAnsId(0),
                    answer1C,
                    getAnsId(1),
                    answer2C,
                    getAnsId(2),
                    answer3C,
                    getAnsId(3),
                    answer4C,
                ).onSuccess { view.actionSuccess() }
                    .onFailure { er -> view.actionFailed(er.error) }
                    .call()
            }
        }
    }

    private fun getAnsId(at: Int): Long {
        question?.let {
            if (at >= 0 && at < it.answer.size) {
                return it.answer[at].id
            }
        }
        return -1
    }
}