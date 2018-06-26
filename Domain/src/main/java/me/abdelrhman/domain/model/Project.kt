package me.abdelrhman.domain.model

/**
 * Created by Abdelrhman
 * on 6/26/18.
 */
class Project(val id: String, val name: String, val fullName: String,
              val starCount: String, val dateCreated: String,
              val ownerName: String, val ownerAvatar: String,
              val isBookmarked: Boolean) {
}