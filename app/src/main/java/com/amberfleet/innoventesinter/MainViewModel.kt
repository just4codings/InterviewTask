package com.amberfleet.innoventesinter

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.amberfleet.innoventesinter.extension.isNullOrEmpty
import com.amberfleet.innoventesinter.extension.isPanValid
import com.amberfleet.innoventesinter.extension.isValidDate
import com.amberfleet.innoventesinter.extension.showToastMessage
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val context: Context
) : ViewModel() {

    var Enabled = ObservableBoolean(true)
    var pancard = ObservableField<String>("")
    var date = ObservableField<String>("")
    var month = ObservableField<String>("")
    var year = ObservableField<String>("")

    fun onNextClick() {

        when {
            isNullOrEmpty(pancard.get()) -> showToastMessage(
                context,
                context.getString(R.string.EmptypanMsg)
            )

            isNullOrEmpty(date.get()) -> showToastMessage(
                context,
                context.getString(R.string.EmptyDate)
            )
            isNullOrEmpty(month.get()) -> showToastMessage(
                context,
                context.getString(R.string.EmptyMonth)
            )
            isNullOrEmpty(year.get()) -> showToastMessage(
                context,
                context.getString(R.string.EmptyYear)
            )

            pancard.get()?.let { !isPanValid(it) }!! -> {
                showToastMessage(context, context.getString(R.string.InvalidpanMsg))
                Enabled.set(false)
            }
            !isValidDate(check(date.get()) + "/" + check(month.get()) + "/" + year.get()) -> {
                showToastMessage(context, context.getString(R.string.InvalidDate))
                Enabled.set(false)
            }
            else -> {
                showToastMessage(context, context.getString(R.string.SuccessMsg))
            }


        }


    }

    fun check(s: String?): String {
        if (s?.length == 1) {
            return "0" + s
        } else {
            return s!!
        }
    }

    fun onpancardechanged(
        s: CharSequence, start: Int, before: Int,
        count: Int
    ) {

        Enabled.set(true)

    }

    fun onDateechanged(
        s: CharSequence, start: Int, before: Int,
        count: Int
    ) {

        Enabled.set(true)

    }

    fun onMonthechanged(
        s: CharSequence, start: Int, before: Int,
        count: Int
    ) {

        Enabled.set(true)
    }

    fun onYearechanged(
        s: CharSequence, start: Int, before: Int,
        count: Int
    ) {

        Enabled.set(true)
    }

    fun Dontclick(view: View) {

        val activity = view.getContext() as Activity
        activity.finish()
    }
}


