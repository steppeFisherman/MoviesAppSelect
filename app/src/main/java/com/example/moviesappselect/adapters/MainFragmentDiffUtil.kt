package com.example.moviesappselect.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.moviesappselect.model.ResultApp

class MainFragmentDiffUtil(
    private val oldList: List<ResultApp>,
    private val newList: List<ResultApp>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].display_title == newList[newItemPosition].display_title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            /*
            oldList[oldItemPosition].summary_short != newList[newItemPosition].summary_short -> false
            oldList[oldItemPosition].byline != newList[newItemPosition].byline -> false
            oldList[oldItemPosition].critics_pick != newList[newItemPosition].critics_pick -> false
            oldList[oldItemPosition].date_updated != newList[newItemPosition].date_updated -> false
            oldList[oldItemPosition].headline != newList[newItemPosition].headline -> false
            oldList[oldItemPosition].mpaa_rating != newList[newItemPosition].mpaa_rating -> false
            oldList[oldItemPosition].multimedia != newList[newItemPosition].multimedia -> false
            oldList[oldItemPosition].opening_date != newList[newItemPosition].opening_date -> false
            oldList[oldItemPosition].publication_date != newList[newItemPosition].publication_date -> false
             */
            oldList[oldItemPosition] != newList[newItemPosition] -> false
            else -> true
        }
    }
}
