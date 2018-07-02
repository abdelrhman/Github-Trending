package me.abdelrhman.cache.test.factory

import me.abdelrhman.cache.model.Config

/**
 * Created by Abdelrhman
 * on 6/30/18.
 */
object ConfigDataFactory {

    fun makeCachedConfig(): Config {
        return Config(DataFactory.randomInt(), DataFactory.randomLong())
    }
}