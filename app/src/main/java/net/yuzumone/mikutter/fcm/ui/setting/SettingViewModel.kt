package net.yuzumone.mikutter.fcm.ui.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.yuzumone.mikutter.fcm.perf.SharedPreferenceStorage
import javax.inject.Inject

class SettingViewModel @Inject constructor(private val preferenceStorage: SharedPreferenceStorage) :
        ViewModel() {

    private var messageCount = MutableLiveData<Int>()

    init {
        messageCount.value = preferenceStorage.messageCount
    }

    fun getMessageCount(): MutableLiveData<Int> {
        return messageCount
    }

    fun setMessageCount(count: Int) {
        preferenceStorage.messageCount = count
        messageCount.value = count
    }
}