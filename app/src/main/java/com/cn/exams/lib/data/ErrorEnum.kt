package com.cn.exams.lib.data

import android.content.res.Resources
import com.cn.exams.R

enum class ErrorEnum {
    UNKNOWN_ERROR,

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

    // from android
    LOCAL_ERROR,
    INVALID_BIRTH_FORMAT,
    INVALID_CF_PASSWORD
}

fun ErrorEnum.message(resources: Resources): String {
    return when (this) {
        ErrorEnum.UNKNOWN_ERROR -> resources.getString(R.string.unknown_error)
        ErrorEnum.MISSING_USERNAME -> resources.getString(R.string.missing_username)
        ErrorEnum.INVALID_USERNAME_LENGTH -> resources.getString(R.string.invalid_username_length)
        ErrorEnum.MISSING_PASSWORD -> resources.getString(R.string.missing_password)
        ErrorEnum.INVALID_PASSWORD_LENGTH -> resources.getString(R.string.invalid_password_length)
        ErrorEnum.MISSING_USER_NAME -> resources.getString(R.string.missing_user_name)
        ErrorEnum.INVALID_USER_NAME_LENGTH -> resources.getString(R.string.invalid_user_name_length)
        ErrorEnum.MISSING_BIRTH -> resources.getString(R.string.missing_birth)
        ErrorEnum.MISSING_TEL -> resources.getString(R.string.missing_tel)
        ErrorEnum.EXIST_USERNAME -> resources.getString(R.string.exist_username)
        ErrorEnum.WRONG_USERNAME_PASSWORD -> resources.getString(R.string.wrong_username_password)
        ErrorEnum.REQUIRED_LOGIN -> ""
        ErrorEnum.NOT_FOUND_USER -> ""
        ErrorEnum.PERMISSION_REQUIRED -> ""
        ErrorEnum.MISSING_BANK_NAME -> ""
        ErrorEnum.INVALID_BANK_NAME_LENGTH -> ""
        ErrorEnum.MISSING_BANK_SCOPE -> ""
        ErrorEnum.INVALID_BANK_SCOPE -> ""
        ErrorEnum.NOT_FOUND_BANK -> ""
        ErrorEnum.MISSING_BANK_ID -> ""
        ErrorEnum.MISSING_QUESTION_CONTENT -> ""
        ErrorEnum.MISSING_CORRECT_ANSWER -> ""
        ErrorEnum.INVALID_CORRECT_ANSWER -> ""
        ErrorEnum.MISSING_ANSWER -> ""
        ErrorEnum.MISSING_QUESTION_ID -> ""
        ErrorEnum.NOT_FOUND_QUESTION -> ""
        ErrorEnum.MISSING_ANSWER_CONTENT -> ""
        ErrorEnum.NOT_FOUND_CONTEST -> ""
        ErrorEnum.MISSING_CONTEST_NAME -> ""
        ErrorEnum.INVALID_CONTEST_NAME_LENGTH -> ""
        ErrorEnum.MISSING_CONTEST_QUANTITY -> ""
        ErrorEnum.INVALID_CONTEST_QUANTITY -> ""
        ErrorEnum.MISSING_CONTEST_SCOPE -> ""
        ErrorEnum.MISSING_CONTEST_START_AT -> ""
        ErrorEnum.MISSING_CONTEST_BANK -> ""
        ErrorEnum.MISSING_CONTEST_ID -> ""
        ErrorEnum.MISSING_CONTEST_CODE -> ""
        ErrorEnum.WRONG_CODE_PASSWORD -> ""
        ErrorEnum.NOT_IN_JOIN_TIME -> ""
        ErrorEnum.NOT_ENOUGH_QUESTION -> ""
        ErrorEnum.JOIN_CONTEST_FIRST -> ""
        ErrorEnum.LOCAL_ERROR -> resources.getString(R.string.unknown_error)
        ErrorEnum.INVALID_BIRTH_FORMAT -> resources.getString(R.string.invalid_birth_format)
        ErrorEnum.INVALID_CF_PASSWORD -> resources.getString(R.string.invalid_cf_password)
//        else -> resources.getString(R.string.unknown_error)
    }
}