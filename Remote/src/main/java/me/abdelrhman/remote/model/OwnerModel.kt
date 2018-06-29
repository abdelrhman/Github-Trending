package me.abdelrhman.remote.model

import com.google.gson.annotations.SerializedName
import javax.inject.Inject

/**
 * Created by Abdelrhman
 * on 6/28/18.
 */

class OwnerModel(@SerializedName("login") val ownerName: String,
                 @SerializedName("avatar_url") val ownerAvatar: String)
