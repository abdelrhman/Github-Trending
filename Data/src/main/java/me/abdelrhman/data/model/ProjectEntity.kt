package me.abdelrhman.data.model

/**
 * Created by Abdelrhman
 * on 6/27/18.
 */
open class ProjectEntity(val id: String, val name: String, val fullName: String,
                         val starCount: String, val dateCreated: String,
                         val ownerName: String, val ownerAvatar: String,
                         val isBookmarked: Boolean = false)