package ru.netology.nnmedia.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.myapplication.dto.Post
import ru.netology.nnmedia.repository.PostRepository
import ru.netology.nnmedia.repository.PostRepositoryInMemory

private val  empty = Post(
    id = 0,
    author = "",
    content = "",
    published = "",
    likedByMe = false
)
class PostViewModel: ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemory()
    val data = repository.getAll()
    val edited = MutableLiveData(empty)

    fun changeContent (content: String){
        edited.value?.let {
            val text = content.trim()
            if (it.content == text){
                return
            }
            edited.value = it.copy(content = text)
        }
    }

    fun edit(post: Post) {
        edited.value = post
    }

    fun save(){
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }

    fun like(id: Long) = repository.like(id)
    fun share (id: Long) = repository.share(id)
    fun remove(id: Long) = repository.remove(id)
}