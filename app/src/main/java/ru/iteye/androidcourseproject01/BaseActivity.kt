package ru.iteye.androidcourseproject01

import android.os.Bundle

class BaseActivity : AppActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}


