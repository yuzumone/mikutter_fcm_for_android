package net.yuzumone.mikutter.fcm.ui.list

import androidx.lifecycle.ViewModel
import net.yuzumone.mikutter.fcm.db.MikutterMessageDatabase
import net.yuzumone.mikutter.fcm.perf.PreferenceStorage
import javax.inject.Inject

class MessageListViewModel @Inject constructor(
        database: MikutterMessageDatabase,
        private val preferenceStorage: PreferenceStorage
) : ViewModel() {

    private val dao = database.messageDao()

    fun getMessagesOrderDescLimit() =
            dao.findOrderDescLimit(preferenceStorage.messageCount)
}