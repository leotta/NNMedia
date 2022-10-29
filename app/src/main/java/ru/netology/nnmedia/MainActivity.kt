package ru.netology.nnmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import ru.netology.myapplication.dto.Post
import ru.netology.nnmedia.adapter.OnInteractionListener
import ru.netology.nnmedia.adapter.PostsAdapter
import ru.netology.nnmedia.databinding.ActivityMainBinding
import ru.netology.nnmedia.util.AndroidUtils
import ru.netology.nnmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter(object : OnInteractionListener {
            override fun onEdit(post: Post) {
                viewModel.edit(post)
            }

            override fun onRemove(post: Post) {
                viewModel.remove(post.id)
            }

            override fun onLike(post: Post) {
                viewModel.like(post.id)
            }

            override fun onShare(post: Post) {
                viewModel.share(post.id)
            }

        })
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            val newPost = adapter.itemCount < posts.size
            adapter.submitList(posts) {
                if (newPost) {
                    binding.list.smoothScrollToPosition(0)
                }
            }
        }
        viewModel.edited.observe(this) {
            if (it.id != 0L) {
                binding.postsEditText.text = it.content
                binding.groupEdit.visibility = View.VISIBLE
                binding.content.requestFocus()
                binding.content.setText(it.content)
                binding.icCancel.setOnClickListener {
                    with(binding.content) {
                        setText("")
                        clearFocus()
                        AndroidUtils.hideKeyboard(it)
                        binding.groupEdit.visibility = View.GONE
                        viewModel.cancelEdit()
                    }
                }
            } else {
                with(binding.content) {
                    setText("")
                    clearFocus()
                    binding.groupEdit.visibility = View.GONE
                }
            }
        }

        binding.save.setOnClickListener {
            with(binding.content) {
                val text = text.toString()
                if (text.isBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        R.string.error_empty_content,
                        Toast.LENGTH_LONG
                    ).show()
                    return@setOnClickListener
                }
                viewModel.changeContent(text)
                viewModel.save()

                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(it)
            }
        }
    }
}

