package co.com.ceiba.mobile.pruebadeingreso.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.data.entities.Post
import co.com.ceiba.mobile.pruebadeingreso.databinding.PostListItemBinding

class PostAdapter(): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private val items = mutableListOf<Post>()

    fun setItems(items: List<Post>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    class PostViewHolder(private val itemBinding: PostListItemBinding): RecyclerView.ViewHolder(itemBinding.root){

        fun bind (post: Post){
            itemBinding.title.text = post.title
            itemBinding.body.text = post.body
        }
    }
}