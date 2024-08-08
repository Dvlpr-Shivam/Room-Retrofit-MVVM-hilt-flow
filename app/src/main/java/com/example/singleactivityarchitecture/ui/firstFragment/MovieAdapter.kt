package com.example.singleactivityarchitecture.ui.firstFragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.singleactivityarchitecture.R
import com.example.singleactivityarchitecture.dataBase.table.Poster
import com.example.singleactivityarchitecture.databinding.MovieItemViewBinding

class MovieAdapter(val click:OnAdapterClick):RecyclerView.Adapter<MovieAdapter.Holder>() {
    private var list = ArrayList<Poster>()
    class Holder(val binding: MovieItemViewBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            MovieItemViewBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.apply {
            Glide.with(holder.itemView.context)
                .load(list[position].poster)
                .error(R.drawable.ic_launcher_background)
                .into(imageView)
            name.text =list[position].name
            releaseDate.text =list[position].release
            root.setOnClickListener {
             click.itemClick(list[position])
            }
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateView(data: List<Poster>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
    interface OnAdapterClick{
        fun itemClick(poster: Poster)
    }
}