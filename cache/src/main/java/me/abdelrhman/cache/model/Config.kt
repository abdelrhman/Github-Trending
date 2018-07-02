package me.abdelrhman.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import me.abdelrhman.cache.db.ConfigConstants

/**
 * Created by Abdelrhman
 * on 6/30/18.
 */


@Entity(tableName = ConfigConstants.TABLE_NAME)
class Config(
        @PrimaryKey(autoGenerate = true)
        var id: Int = -1,
        val lastCacheTime: Long) {


}