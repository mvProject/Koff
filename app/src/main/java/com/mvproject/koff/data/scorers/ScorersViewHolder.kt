package com.mvproject.koff.data.scorers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mvproject.koff.R
import com.mvproject.koff.databinding.ScorerItemListBinding

class ScorersViewHolder(private val parent: ViewGroup,private val binding: ScorerItemListBinding = DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),R.layout.scorer_item_list,parent,false)
) : RecyclerView.ViewHolder(binding.root) {

    fun bindItem(scorer: Scorer) {
        binding.scorerModel = scorer

    }
}
