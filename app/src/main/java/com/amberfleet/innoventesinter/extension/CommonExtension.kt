package com.amberfleet.innoventesinter.extension

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


var pattern: Pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}")
private const val DATE_PATTERN =
    "(0?[1-9]|1[012]) [/.-] (0?[1-9]|[12][0-9]|3[01]) [/.-] ((19|20)\\d\\d)"
var patternDate: Pattern = Pattern.compile(DATE_PATTERN)


fun showToastMessage(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(context, message, duration).show()


fun isPanValid(number: String): Boolean {
    return pattern.matcher(number).matches()
}

@SuppressLint("SimpleDateFormat")
fun isValidDate(date: String?): Boolean {

    val sdf = SimpleDateFormat("dd/MM/yyyy")
    var testDate: Date? = null
    try {
        testDate = sdf.parse(date)
    } catch (e: ParseException) {

        return false
    }
    if (!sdf.format(testDate).equals(date)) {

        return false
    }
    //should not be greater than current date.
    if (testDate.after(Calendar.getInstance().getTime())) {
        return false
    }
    return true
}

/*private fun validateMonthWithMaxDate(day: String, month: String): Boolean = day == "31" && (month == "4" || month == "6" || month == "9" || month == "11" || month == "04" || month == "06" || month == "09")
private fun isFebruaryMonth(month: String): Boolean = month == "2" || month == "02"
private fun isLeapYear(year: String): Boolean = year.toInt() % 4 == 0
private fun leapYearWith29Date(day: String): Boolean = !(day == "30" || day == "31")
private fun notLeapYearFebruary(day: String): Boolean = !(day == "29" || day == "30" || day == "31")

fun isValidDate(date: String): Boolean {


    val regex = "^(0[0-9]||1[0-2])/([0-2][0-9]||3[0-1])/([0-9][0-9])?[0-9][0-9]$"
    val matcher = Pattern.compile(regex).matcher(date)
    return if (matcher.matches()) {
        matcher.reset()
        if (matcher.find()) {
            val dateDetails = date.split("/")
            val day: String = dateDetails[1]
            val month: String = dateDetails[0]
            val year: String = dateDetails[2]
            if (validateMonthWithMaxDate(day, month)) {
                false
            } else if (isFebruaryMonth(month)) {
                if (isLeapYear(year)) {
                    leapYearWith29Date(day)
                } else {
                    notLeapYearFebruary(day)
                }
            } else {
                true
            }
        } else {
            false
        }
    } else {
        false
    }
}*/

fun isNullOrEmpty(value: String?): Boolean =
    value == null || value.isEmpty() || value.equals("null", ignoreCase = true)