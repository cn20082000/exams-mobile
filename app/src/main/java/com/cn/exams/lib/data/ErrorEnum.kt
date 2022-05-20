package com.cn.exams.lib.data

import android.content.res.Resources
import com.cn.exams.R

enum class ErrorEnum {
    UNKNOWN_ERROR,
    LOCAL_ERROR,

    // user error
    MISSING_USERNAME,
    INVALID_USERNAME_LENGTH,
    MISSING_PASSWORD,
    INVALID_PASSWORD_LENGTH,
    MISSING_USER_NAME,
    INVALID_USER_NAME_LENGTH,
    MISSING_BIRTH,
    MISSING_TEL,
    EXIST_USERNAME,
    WRONG_USERNAME_PASSWORD,
    REQUIRED_LOGIN,
    NOT_FOUND_USER,
    PERMISSION_REQUIRED,

    // bank error
    MISSING_BANK_NAME,
    INVALID_BANK_NAME_LENGTH,
    MISSING_BANK_SCOPE,
    INVALID_BANK_SCOPE,
    NOT_FOUND_BANK,
    MISSING_BANK_ID,

    // question error
    MISSING_QUESTION_CONTENT,
    MISSING_CORRECT_ANSWER,
    INVALID_CORRECT_ANSWER,
    MISSING_ANSWER,
    MISSING_QUESTION_ID,
    NOT_FOUND_QUESTION,

    // answer error
    MISSING_ANSWER_CONTENT,

    // contest error
    NOT_FOUND_CONTEST,
    MISSING_CONTEST_NAME,
    INVALID_CONTEST_NAME_LENGTH,
    MISSING_CONTEST_QUANTITY,
    INVALID_CONTEST_QUANTITY,
    MISSING_CONTEST_SCOPE,
    MISSING_CONTEST_START_AT,
    MISSING_CONTEST_BANK,
    MISSING_CONTEST_ID,
    MISSING_CONTEST_CODE,
    WRONG_CODE_PASSWORD,
    NOT_IN_JOIN_TIME,
    NOT_ENOUGH_QUESTION,
    JOIN_CONTEST_FIRST,
}

fun ErrorEnum.message(resources: Resources): String {
    when (this) {
        ErrorEnum.UNKNOWN_ERROR -> return resources.getString(R.string.unknown_error)
        ErrorEnum.LOCAL_ERROR -> return resources.getString(R.string.unknown_error)
        ErrorEnum.MISSING_USERNAME -> return resources.getString(R.string.missing_username)
        ErrorEnum.INVALID_USERNAME_LENGTH -> return resources.getString(R.string.invalid_username_length)
        ErrorEnum.MISSING_PASSWORD -> return resources.getString(R.string.missing_password)
        ErrorEnum.INVALID_PASSWORD_LENGTH -> return resources.getString(R.string.invalid_password_length)
        ErrorEnum.MISSING_USER_NAME -> return ""
        ErrorEnum.INVALID_USER_NAME_LENGTH -> return ""
        ErrorEnum.MISSING_BIRTH -> return ""
        ErrorEnum.MISSING_TEL -> return ""
        ErrorEnum.EXIST_USERNAME -> return ""
        ErrorEnum.WRONG_USERNAME_PASSWORD -> return ""
        ErrorEnum.REQUIRED_LOGIN -> return ""
        ErrorEnum.NOT_FOUND_USER -> return ""
        ErrorEnum.PERMISSION_REQUIRED -> return ""
        ErrorEnum.MISSING_BANK_NAME -> return ""
        ErrorEnum.INVALID_BANK_NAME_LENGTH -> return ""
        ErrorEnum.MISSING_BANK_SCOPE -> return ""
        ErrorEnum.INVALID_BANK_SCOPE -> return ""
        ErrorEnum.NOT_FOUND_BANK -> return ""
        ErrorEnum.MISSING_BANK_ID -> return ""
        ErrorEnum.MISSING_QUESTION_CONTENT -> return ""
        ErrorEnum.MISSING_CORRECT_ANSWER -> return ""
        ErrorEnum.INVALID_CORRECT_ANSWER -> return ""
        ErrorEnum.MISSING_ANSWER -> return ""
        ErrorEnum.MISSING_QUESTION_ID -> return ""
        ErrorEnum.NOT_FOUND_QUESTION -> return ""
        ErrorEnum.MISSING_ANSWER_CONTENT -> return ""
        ErrorEnum.NOT_FOUND_CONTEST -> return ""
        ErrorEnum.MISSING_CONTEST_NAME -> return ""
        ErrorEnum.INVALID_CONTEST_NAME_LENGTH -> return ""
        ErrorEnum.MISSING_CONTEST_QUANTITY -> return ""
        ErrorEnum.INVALID_CONTEST_QUANTITY -> return ""
        ErrorEnum.MISSING_CONTEST_SCOPE -> return ""
        ErrorEnum.MISSING_CONTEST_START_AT -> return ""
        ErrorEnum.MISSING_CONTEST_BANK -> return ""
        ErrorEnum.MISSING_CONTEST_ID -> return ""
        ErrorEnum.MISSING_CONTEST_CODE -> return ""
        ErrorEnum.WRONG_CODE_PASSWORD -> return ""
        ErrorEnum.NOT_IN_JOIN_TIME -> return ""
        ErrorEnum.NOT_ENOUGH_QUESTION -> return ""
        ErrorEnum.JOIN_CONTEST_FIRST -> return ""
    }
}