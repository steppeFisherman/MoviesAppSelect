package com.example.moviesappselect.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesappselect.databinding.MainItemRawBinding
import com.example.moviesappselect.model.ResultApp
import com.example.moviesappselect.utils.LoadImage

class MainFragmentAdapter(private val loadImage: LoadImage) :
    RecyclerView.Adapter<MainFragmentAdapter.MainHolder>() {

    private var mList = emptyList<ResultApp>()

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
                mList[position].multimedia.src
            )
            holder.binding.movieTitle.text = mList[position].display_title
            holder.binding.movieDesc.text = mList[position].summary_short
        }
    }

    override fun getItemCount() = mList.size

    class MainHolder(val binding: MainItemRawBinding) : RecyclerView.ViewHolder(binding.root)

    fun setData(newList: List<ResultApp>) {
        Log.d("AAA", "setData in MainFragmentAdapter")
        val diffUtil = MainFragmentDiffUtil(mList, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        mList = newList
        diffResult.dispatchUpdatesTo(this)
    }
}