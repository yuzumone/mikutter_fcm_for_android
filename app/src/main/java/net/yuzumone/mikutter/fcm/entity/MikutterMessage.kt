package net.yuzumone.mikutter.fcm.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "message")
data class MikutterMessage (
        @PrimaryKey(autoGenerate = true) var id: Long?,
        var title: String? = null,
        var body: String? = null,
        var url: String? = null,
        @ColumnInfo(name = "url_title") var urlTitle: String? = null,
        var priority: Int = 0,
        var image: String? = null,
        var timestamp: Date? = null
) {
    constructor(data: Map<String, String>, now: Date) : this (
            id = null,
            title = data["title"],
            body = data["body"],
            url = data["url"],
            urlTitle = data["url_title"],
            priority = data["priority"]?.toInt() ?: 0,
            image = data["image"],
            timestamp = now
    )
}