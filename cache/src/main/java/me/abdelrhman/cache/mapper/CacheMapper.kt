package me.abdelrhman.cache.mapper

/**
 * Created by Abdelrhman
 * on 6/30/18.
 */
interface CacheMapper<C, E> {

    fun mapFromCached(type: C): E

    fun mapToCached(type: E): C

}