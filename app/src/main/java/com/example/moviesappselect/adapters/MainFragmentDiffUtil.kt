package com.example.moviesappselect.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.moviesappselect.model.MovieApp

class MainFragmentDiffUtil (
    private val oldList: List<MovieApp>,
    private val newList: List<MovieApp>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> false
            oldList[oldItemPosition].copyright != newList[newItemPosition].copyright -> false
            oldList[oldItemPosition].has_more != newList[newItemPosition].has_more -> false
            oldList[oldItemPosition].num_results != newList[newItemPosition].num_results -> false
            oldList[oldItemPosition].results.size != newList[newItemPosition].results.size -> false
            oldList[oldItemPosition].status != newList[newItemPosition].status -> false
            else -> true
        }
    }
}