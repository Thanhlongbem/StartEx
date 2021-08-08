package com.example.stardemoapp.core.rest

object Constants {
    const val BASE_URL = "https://api.github.com/"
    const val USER_TRANFER = "USER_TRANFER"

    object API{
        const val PATH_GET_LIST_USER = "users"
        const val PATH_GET_FOLLOWER = "users/{user}/followers"
        const val PATH_GET_REPOS = "users/{user}/repos"
    }
}