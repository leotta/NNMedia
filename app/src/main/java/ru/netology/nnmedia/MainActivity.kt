@file:Suppress("UNREACHABLE_CODE")

package ru.netology.nnmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.netology.myapplication.dto.Post
import ru.netology.nnmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            counterLikes = 1099,
            counterShare = 0,
            counterEye = 0,
            viewed = 0,
            likedByMe = false
        )
        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            counterLikes.text = getFormatNumbers(post.counterLikes)
            counterShare.text = getFormatNumbers(post.counterShare)
            counterEye.text = getFormatNumbers(post.counterEye)
            if (post.likedByMe) {
                like.setImageResource(R.drawable.ic_liked_24)
            }

//            root.setOnClickListener {
//                Log.d("stuff", "stuff")
//            }
//
//            avatar.setOnClickListener {
//                Log.d("stuff", "avatar")
//            }

            like.setOnClickListener {
                Log.d("tag", "like")
                post.likedByMe = !post.likedByMe
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
                )
                if (post.likedByMe) post.counterLikes++ else post.counterLikes--
                counterLikes.text = getFormatNumbers(post.counterLikes)
            }
            share.setOnClickListener {
                Log.d("tag", "share")
                share.setImageResource(
                    R.drawable.ic_baseline_share_24
                )
                post.counterShare++
                counterShare.text = post.counterShare.toString()
            }

        }
    }
}

fun getFormatNumbers(count: Int): String {
    var counts = when {
        count in 1000..9999 -> if (count.toString()[1]=='0') count.toString()[0] + "K" else count.toString()[0] + "." + count.toString()[1] + "K"
        count in 10_000..99_999 -> "${count.toString()[0]} ${count.toString()[1]} K"
        count in 100_000..999_999 -> "${count.toString()[0]} ${count.toString()[1]} ${count.toString()[2]} K"
        count in 1_000_000..9_999_999 -> if (count.toString()[1]=='0') count.toString()[0] + "М" else count.toString()[0] + "." + count.toString()[1] + "М"
        else -> "$count"
    }
    return counts
}
