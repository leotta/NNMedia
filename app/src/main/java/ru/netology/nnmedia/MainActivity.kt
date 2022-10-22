package ru.netology.nnmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nnmedia.databinding.ActivityMainBinding
import ru.netology.nnmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                counterLikes.text = getFormatNumbers(post.counterLikes)
                counterShare.text = getFormatNumbers(post.counterShare)
                counterEye.text = getFormatNumbers(post.counterEye)
                like.setImageResource(if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24)
            }
        }

            binding.like.setOnClickListener {
                viewModel.like()
            }
            binding.share.setOnClickListener {
                viewModel.share()
            }
    }
}

    fun getFormatNumbers(count: Int): String {
        return when (count) {
            in 1000..9999 -> if (count.toString()[1] == '0') count.toString()[0] + "K" else count.toString()[0] + "." + count.toString()[1] + "K"
            in 10_000..99_999 -> "${count.toString()[0]} ${count.toString()[1]} K"
            in 100_000..999_999 -> "${count.toString()[0]} ${count.toString()[1]} ${count.toString()[2]} K"
            in 1_000_000..9_999_999 -> if (count.toString()[1] == '0') count.toString()[0] + "М" else count.toString()[0] + "." + count.toString()[1] + "М"
            else -> "$count"
        }
    }
