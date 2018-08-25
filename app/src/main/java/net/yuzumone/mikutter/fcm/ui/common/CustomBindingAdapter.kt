package net.yuzumone.mikutter.fcm.ui.common

import android.databinding.BindingAdapter
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

object CustomBindingAdapter {

    @BindingAdapter("date")
    @JvmStatic
    fun setDate(view: TextView, date: Date) {
        val today = java.sql.Date(Date().time)
        val received = java.sql.Date(date.time)
        val format = if (today.toString() == received.toString()) {
            SimpleDateFormat("HH:mm", Locale.US)
        } else {
            SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.US)
        }
        view.text = format.format(date)
    }

}
