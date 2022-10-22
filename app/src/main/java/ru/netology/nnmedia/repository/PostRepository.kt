package ru.netology.nnmedia.repository

import androidx.lifecycle.LiveData
import ru.netology.myapplication.dto.Post

interface PostRepository {
    fun get(): LiveData<Post>
    fun like ()
    fun share()
}