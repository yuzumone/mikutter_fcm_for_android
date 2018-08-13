package net.yuzumone.mikutter.fcm.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
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