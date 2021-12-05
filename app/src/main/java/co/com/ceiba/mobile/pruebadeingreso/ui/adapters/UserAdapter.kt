package co.com.ceiba.mobile.pruebadeingreso.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.data.entities.User
import co.com.ceiba.mobile.pruebadeingreso.databinding.UserListItemBinding

class UserAdapter (private val listener: OnUserClickListener): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val items = mutableListOf<User>()

    fun setItems(items: List<User>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    class UserViewHolder(private val itemBinding: UserListItemBinding, private val listener: OnUserClickListener): RecyclerView.ViewHolder(itemBinding.root){

        fun bind (user: User){
            itemBinding.name.text = user.name
            itemBinding.phone.text = user.phone
            itemBinding.email.text = user.email
            itemBinding.root.setOnClickListener() {
                listener.onClickUser(user.id)
            }
            itemBinding.btnViewPost.setOnClickListener() {
                listener.onClickUser(user.id)
            }
        }
    }

    interface OnUserClickListener{
        fun onClickUser(id: Int)
    }
}