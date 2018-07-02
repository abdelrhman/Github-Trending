package me.abdelrhman.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import me.abdelrhman.cache.db.ConfigConstants
import me.abdelrhman.cache.model.Config

/**
 * Created by Abdelrhman
 * on 6/30/18.
 */

@Dao
abstract class ConfigDao {


    @Query(ConfigConstants.QUERY_CONFIG)
    abstract fun getConfig(): Flowable<Config>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertConfig(config: Config)


}