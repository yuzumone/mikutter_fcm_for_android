package net.yuzumone.mikutter.fcm.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import net.yuzumone.mikutter.fcm.entity.MikutterMessage

@Dao
interface MikutterMessageDao {

    @Query("SELECT * from message ")
    fun findAll(): LiveData<List<MikutterMessage>>

    @Query("SELECT * from message ORDER BY id DESC LIMIT :limit ")
    fun findOrderDescLimit(limit: Int): LiveData<List<MikutterMessage>>

    @Query("SELECT * FROM message WHERE id = :id ")
    fun findOne(id: String): LiveData<MikutterMessage>

    @Insert
    fun insert(message: MikutterMessage)

    @Delete
    fun delete(message: MikutterMessage)
}