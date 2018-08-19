package net.yuzumone.mikutter.fcm.perf

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.support.annotation.WorkerThread
import javax.inject.Inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface PreferenceStorage {
    var messageCount: Int
}

class SharedPreferenceStorage @Inject constructor(context: Context) : PreferenceStorage {

    private val prefs = context.applicationContext.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "net.yuzumone.mikutter.fcm.prefs"
        private const val PREF_MESSAGE_COUNT = "pref_message_count"
    }

    override var messageCount by IntPreference(prefs, PREF_MESSAGE_COUNT, 200)
}

class IntPreference(
        private val preferences: SharedPreferences,
        private val name: String,
        private val defaultValue: Int
) : ReadWriteProperty<Any, Int> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): Int {
        return preferences.getInt(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
        preferences.edit().putInt(name, value).apply()
    }
}
