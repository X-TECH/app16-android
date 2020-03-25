package am.xtech.app16.utils

import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

fun isValidEmail(email: String): Boolean {
    return if (email.isEmpty()) {
        false
    } else {
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}

fun isEmptyTIETAndSetErrors(vararg tvArray: TextInputLayout, emptyErrorMessage: String): Boolean {
    var status = false
    for (tv in tvArray) {
        if (tv.editText?.text?.isEmpty()!!) {
            tv.error = emptyErrorMessage
            status = true
        }
    }
    return status
}

fun setViewVisibility(view: View, boolean: Boolean) {
    if (boolean) view.visibility = View.VISIBLE
    else view.visibility = View.GONE
}

fun setSpanText(textView: TextView, text: String, start: Int, end: Int, listener: ClickableSpan) {
    val ss = SpannableString(text)

    ss.setSpan(listener, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

    textView.text = ss
    textView.movementMethod = LinkMovementMethod.getInstance()
}

fun addTIETsErrorHideListeners(vararg views: TextInputLayout) {
    for (item in views) {
        item.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                item.error = ""
            }
        })
    }
}
