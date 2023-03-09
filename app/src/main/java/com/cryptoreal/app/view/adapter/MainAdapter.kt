package com.cryptoreal.app.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cryptoreal.app.databinding.ResItemCryptoBinding
import com.cryptoreal.app.models.Currency

class MainAdapter : ListAdapter<Pair<String, Currency>, MainViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ResItemCryptoBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val currencyPair = getItem(position)
        holder.bind(currencyPair)
    }
}

class MainViewHolder(private val binding: ResItemCryptoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(pair: Pair<String, Currency>) {
        binding.title.text = "${pair.second.unit} ${pair.second.value}"
        Glide.with(itemView.context)
            .load(pair.second.urlImage)
            .into(binding.imageView)
    }

}

object DiffCallback : DiffUtil.ItemCallback<Pair<String, Currency>>() {
    override fun areItemsTheSame(
        oldItem: Pair<String, Currency>,
        newItem: Pair<String, Currency>
    ): Boolean {
        return oldItem.first == newItem.first
    }

    override fun areContentsTheSame(
        oldItem: Pair<String, Currency>,
        newItem: Pair<String, Currency>
    ): Boolean {
        return oldItem.second.value == newItem.second.value
    }
}

