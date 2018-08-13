package net.yuzumone.mikutter.fcm.ui.list

import android.arch.lifecycle.ViewModel
import net.yuzumone.mikutter.fcm.db.MikutterMessageDatabase
import javax.inject.Inject

class MessageListViewModel @Inject constructor(database: MikutterMessageDatabase) : ViewModel() {
    private val dao = database.messageDao()

    val allMessages = dao.findAll()

    fun getMessagesOrderDescLimit(limit: Int) = dao.findOrderDescLimit(limit)
}