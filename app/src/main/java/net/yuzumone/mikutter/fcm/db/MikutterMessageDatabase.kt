package net.yuzumone.mikutter.fcm.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import net.yuzumone.mikutter.fcm.entity.MikutterMessage

@Database(entities = [MikutterMessage::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class MikutterMessageDatabase : RoomDatabase() {
    abstract fun messageDao(): MikutterMessageDao
}