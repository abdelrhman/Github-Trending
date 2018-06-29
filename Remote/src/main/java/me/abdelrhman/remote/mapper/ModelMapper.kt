package me.abdelrhman.remote.mapper

/**
 * Created by Abdelrhman
 * on 6/28/18.
 */
interface ModelMapper <in M, out E>{

    fun mapFromModel(model : M): E

}