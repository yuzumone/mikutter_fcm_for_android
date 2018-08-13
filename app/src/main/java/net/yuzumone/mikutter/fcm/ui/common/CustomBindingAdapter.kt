package net.yuzumone.mikutter.fcm.ui.common

import android.databinding.BindingAdapter
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

object CustomBindingAdapter {

    @BindingAdapter("date")
    @JvmStatic
    fun setDate(view: TextView, date: Date) {
        val format = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US)
        view.text = format.format(date)
    }

}
