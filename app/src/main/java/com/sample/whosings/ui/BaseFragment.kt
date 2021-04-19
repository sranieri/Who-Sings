package com.sample.whosings.ui

import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    fun showLoader(activity: BaseActivity){
        activity.showLoader()
    }

    fun hideLoader(activity: BaseActivity){
        activity.hideLoader()
    }
}