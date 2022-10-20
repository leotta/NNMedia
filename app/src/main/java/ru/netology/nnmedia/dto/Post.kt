package ru.netology.myapplication.dto

class Post (
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var counterLikes: Int = 0,
    var likedByMe: Boolean = false,
    var counterShare: Int = 0,
    var viewed: Int = 0,
    var counterEye: Int = 0
)