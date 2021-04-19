package com.sample.whosings.utils

import android.widget.EditText

fun EditText.getSingleLineText() : String{
    return text?.toSingleLineString() ?: ""
}

fun CharSequence.toSingleLineString() : String{
    return this.toString().replace("\n", "")
}
