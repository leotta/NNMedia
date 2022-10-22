package ru.netology.myapplication.dto

data class Post (
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val counterLikes: Int = 0,
    val likedByMe: Boolean = false,
    val counterShare: Int = 0,
    val viewed: Int = 0,
    val counterEye: Int = 0
)