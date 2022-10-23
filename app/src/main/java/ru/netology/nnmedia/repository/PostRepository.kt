package ru.netology.nnmedia.repository

import androidx.lifecycle.LiveData
import ru.netology.myapplication.dto.Post

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun like (id: Long)
    fun share(id: Long)
}