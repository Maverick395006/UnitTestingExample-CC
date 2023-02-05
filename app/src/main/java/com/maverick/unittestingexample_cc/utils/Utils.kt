package com.maverick.unittestingexample_cc.utils

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.google.android.material.progressindicator.LinearProgressIndicator

fun Context.showToast(toastMessage: String, isLong: Boolean) {
    Toast.makeText(this, toastMessage, if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}

@BindingAdapter("visibleOrGone")
fun setVisibleOrGone(view: View, condition: Boolean) {
    view.visibility = if (condition) View.VISIBLE else View.GONE
}

@BindingAdapter("visibleOrInvisible")
fun setVisibleOrInvisible(view: View, condition: Boolean) {
    view.visibility = if (condition) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("goneWhenEmpty")
fun goneWhenEmpty(textView: TextView, goneWhenEmpty: Boolean) {
    textView.visibility =
        if (textView.text.isNullOrEmpty() && goneWhenEmpty) View.GONE else View.VISIBLE
}

