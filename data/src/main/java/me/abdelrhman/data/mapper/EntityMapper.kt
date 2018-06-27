package me.abdelrhman.data.mapper

/**
 * Created by Abdelrhman
 * on 6/27/18.
 */
interface EntityMapper<E, D> {
    fun mapFromEntity(entity: E): D

    fun mapToEntity(domain: D): E
}