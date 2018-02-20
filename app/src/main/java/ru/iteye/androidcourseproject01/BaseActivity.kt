package ru.iteye.androidcourseproject01

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import android.content.DialogInterface
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.widget.TextView
import android.view.Gravity
import android.widget.FrameLayout


open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("CREATE", "BaseActivity")
    }

    /**
     * Показываем всплывашку
     */
    //TODO на самом деле, как бы ты не любил Toast, он немного устарел.
    // Его надо использовать, если совсем плохо все и нельзя показать красивый SnackBar. Пусть будет он

    //TODO: SnackBar закрывается при вызове новой активности. Он остается на старой. Поэтому я пользовал тост.
    private fun showToast(msg: String) {
        //Log.d("***", this.packageName.toString())
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    private fun showSnack(msg: String) {
        val snack: Snackbar = Snackbar.make(window.decorView.rootView, msg, Snackbar.LENGTH_LONG)
        val view = snack.view
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
        snack.show()
    }

    fun showMessage(msg: String, isToast: Boolean) {
        if (isToast) {
            showToast(msg)
        } else {
            showSnack(msg)
        }
    }

    /**
     * Simple alert dialog for some debug purposes
     */
    fun showCustomAlert(customMessage: String, isExit: Boolean){
        val dialog = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.alert_dialog,null)
        val alertMessage = dialogView.findViewById<TextView>(R.id.textAlertMessage)
        dialog.setView(dialogView)
        //dialog.setCancelable(true)
        dialog.setPositiveButton("Ok",{ dialogInterface: DialogInterface, i: Int ->
            //TODO а что это?
            //TODO а это если захочу убить приложение при завершении диалога, это для отладки
            if (isExit) System.exit(0)
        })
        alertMessage.text = customMessage
        val customDialog = dialog.create()
        customDialog.show()

    }

}


