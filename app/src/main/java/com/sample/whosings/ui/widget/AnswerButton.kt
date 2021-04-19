package com.sample.whosings.ui.widget

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.sample.whosings.R


class AnswerButton : AppCompatButton {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        setBackgroundResource(R.drawable.button_background)
        reset()
    }

    fun onSuccess(){
        background.colorFilter = PorterDuffColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        setTextColor(ContextCompat.getColor(context, R.color.darkBlue))
    }

    fun onError(){
        background.colorFilter = PorterDuffColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
    }

    fun reset(){
        background.colorFilter = PorterDuffColorFilter((ContextCompat.getColor(context, R.color.colorPrimary)), PorterDuff.Mode.SRC_ATOP)
        setTextColor(ContextCompat.getColor(context, R.color.white))
    }
}