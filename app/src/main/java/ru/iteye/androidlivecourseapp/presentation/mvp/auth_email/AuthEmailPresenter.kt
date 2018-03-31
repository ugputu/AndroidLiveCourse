package ru.iteye.androidlivecourseapp.presentation.mvp.auth_email

import android.util.Log
import com.google.firebase.auth.FirebaseAuthException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.iteye.androidlivecourseapp.data.repositories.AuthRepositoryImpl
import ru.iteye.androidlivecourseapp.domain.auth.AuthInteractor
import ru.iteye.androidlivecourseapp.presentation.mvp.global.BasePresenter
import ru.iteye.androidlivecourseapp.presentation.ui.auth_email.AuthEmailActivity
import ru.iteye.androidlivecourseapp.utils.errors.ErrorsTypes
import ru.iteye.androidlivecourseapp.utils.errors.FirebaseExpection
import java.lang.Thread.currentThread


class AuthEmailPresenter : BasePresenter<AuthEmailActivity>() {

    private var interactor = AuthInteractor(AuthRepositoryImpl())

    fun signIn(email: String, password: String) {
        Log.d("***", "AuthEmailPresenter -> signIn")

        disposables.add(
                interactor.authByMail(email, password)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ isAuthSuccess ->
                            Log.d("***", "AuthEmailPresenter -> signIn -> thread name: " + currentThread().name)
                            Log.d("***", "AuthEmailPresenter -> signIn -> observableAuthResult: " + isAuthSuccess.toString())
                            onUserAuthenticated()
                        }, { error ->
                            error.printStackTrace()
                            afterAuthentificationError(error)
                        })
        )
    }

    private fun afterAuthentificationError(error: Throwable) {
        if (error is FirebaseExpection) {
            when (error.type) {
                ErrorsTypes.ERROR_WRONG_PASSWORD -> onUserWrongPassword()
                ErrorsTypes.ERROR_USER_NOT_FOUND -> onUserNotFound()

            }
        } else {
            getView()?.showError(error.message!!)
        }
    }


    private fun onUserAuthenticated() {
        getView()?.onSuccessAuth()
    }

    private fun onUserWrongPassword() {
        getView()?.onUserWrongPassword()
    }

    private fun onUserNotFound(){
        getView()?.onUserNotFound()
    }

}