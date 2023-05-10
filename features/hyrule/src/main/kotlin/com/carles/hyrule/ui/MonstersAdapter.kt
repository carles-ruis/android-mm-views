package com.carles.hyrule.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.carles.common.ui.extensions.setDebounceClickListener
import com.carles.hyrule.Monster
import com.carles.hyrule.databinding.ItemMonsterBinding

class MonstersAdapter(private val onClick: (Monster) -> Unit) :
    ListAdapter<Monster, MonstersAdapter.MonstersViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonstersViewHolder {
        val binding = ItemMonsterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MonstersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MonstersViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class DiffCallback : DiffUtil.ItemCallback<Monster>() {
        override fun areItemsTheSame(oldItem: Monster, newItem: Monster): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Monster, newItem: Monster): Boolean {
            return oldItem == newItem
        }
    }

    inner class MonstersViewHolder(private val binding: ItemMonsterBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setDebounceClickListener { onClick(getItem(layoutPosition)) }
        }

        fun bind(item: Monster) {
            binding.monsterTitle.text = item.name
        }
    }
}