package com.example.moviesappselect.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesappselect.databinding.MainItemRawBinding
import com.example.moviesappselect.model.MovieApp
import com.example.moviesappselect.utils.LoadImage

class MainFragmentAdapter(private val loadImage: LoadImage) :
    RecyclerView.Adapter<MainFragmentAdapter.MainHolder>() {

    private var mList = emptyList<MovieApp>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = MainItemRawBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MainHolder, position: Int) {

        holder.binding.apply {
            loadImage.load(
                holder.binding.movieImg,
                mList[position].results[position].multimedia.src
            )
            holder.binding.movieTitle.text = mList[position].results[position].display_title
            holder.binding.movieDesc.text = mList[position].results[position].summary_short
        }
    }

    override fun getItemCount() = mList.size

    class MainHolder(val binding: MainItemRawBinding) : RecyclerView.ViewHolder(binding.root)

    fun setData(newList: List<MovieApp>) {
        val diffUtil = MainFragmentDiffUtil(mList, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        mList = newList
        diffResult.dispatchUpdatesTo(this)
    }
}