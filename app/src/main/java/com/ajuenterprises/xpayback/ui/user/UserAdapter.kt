package com.ajuenterprises.xpayback.ui.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ajuenterprises.xpayback.R
import com.ajuenterprises.xpayback.model.User
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class UserAdapter(private var items: List<User>, private val itemClickListener: (User) -> Unit) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvMail: TextView = view.findViewById(R.id.tvMail)
        val tvPhone: TextView = view.findViewById(R.id.tvPhone)
        val ivUser: ImageView = view.findViewById(R.id.ivUser)
        val cardView: CardView = view.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvName.text = item.firstName
        holder.tvMail.text = item.email
        holder.tvPhone.text = item.phone
        Glide.with(holder.itemView.context)
            .load(item.image)
            .apply(
                RequestOptions()
                    .circleCrop()
            )
            .into(holder.ivUser)
        holder.cardView.setOnClickListener { itemClickListener(item) }

    }

    override fun getItemCount(): Int = items.size
    fun updateUserList(users: List<User>) {
        items = users
        notifyDataSetChanged()
    }
}