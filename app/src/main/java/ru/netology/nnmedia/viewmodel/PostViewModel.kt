package ru.netology.nnmedia.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.nnmedia.repository.PostRepository
import ru.netology.nnmedia.repository.PostRepositoryInMemory

class PostViewModel: ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemory()
    val data = repository.get()
    fun like() = repository.like()
    fun share () = repository.share()
}