package net.yuzumone.mikutter.fcm.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import net.yuzumone.mikutter.fcm.entity.MikutterMessage

@Database(entities = [MikutterMessage::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class MikutterMessageDatabase : RoomDatabase() {
    abstract fun messageDao(): MikutterMessageDao
}