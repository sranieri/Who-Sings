package com.sample.whosings.ui

import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    var loaderDialog: LoaderDialog? = null

    fun showLoader(){
        manageLoader(true)
    }

    fun hideLoader(){
        manageLoader(false)
    }

    fun manageLoader(visible: Boolean){
        try {
            if(visible){
                loaderDialog?.dismiss()
                loaderDialog = LoaderDialog()
                loaderDialog?.show(supportFragmentManager.beginTransaction(), LoaderDialog.TAG)
            } else {
                loaderDialog?.dismiss()
            }
        } catch (e: Throwable) {
        }
    }
}