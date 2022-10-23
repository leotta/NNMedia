package ru.netology.nnmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.myapplication.dto.Post
import ru.netology.nnmedia.R
import ru.netology.nnmedia.databinding.CardPostBinding


typealias OnLikeListener = (Post) -> Unit
typealias OnShareListener = (Post) -> Unit

class PostsAdapter(
    private val likeListener: OnLikeListener,
    private val shareListener: OnShareListener
) :
    ListAdapter <Post, PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(view, likeListener, shareListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val likeListener: OnLikeListener,
    private val shareListener: OnShareListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            counterLikes.text = getFormatNumbers(post.counterLikes)
            counterShare.text = getFormatNumbers(post.counterShare)
            counterEye.text = getFormatNumbers(post.counterEye)
            like.setImageResource(if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24)
            like.setOnClickListener {
                likeListener(post)
            }
            share.setOnClickListener {
                shareListener(post)
            }
        }
    }
}

class PostDiffCallback: DiffUtil.ItemCallback<Post>(){
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}

fun getFormatNumbers(count: Int): String {
    return when (count) {
        in 1000..9999 -> if (count.toString()[1] == '0') count.toString()[0] + "K" else count.toString()[0] + "." + count.toString()[1] + "K"
        in 10_000..99_999 -> "${count.toString()[0]}${count.toString()[1]} K"
        in 100_000..999_999 -> "${count.toString()[0]}${count.toString()[1]}${count.toString()[2]} K"
        in 1_000_000..9_999_999 -> if (count.toString()[1] == '0') count.toString()[0] + "М" else count.toString()[0] + "." + count.toString()[1] + "М"
        else -> "$count"
    }
}