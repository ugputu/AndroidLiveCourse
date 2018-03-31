package ru.iteye.androidlivecourseapp.presentation.mvp.auth_email

import android.widget.EditText
import ru.iteye.androidlivecourseapp.presentation.view.base.BaseView

interface AuthEmailView: BaseView {
    fun onWrongEmail(email: EditText)
    fun onWeakPassword(password: EditText)

    fun onSuccessAuth()
    fun onUserWrongPassword()
    fun onUserNotFound()

}