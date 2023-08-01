package com.example.inseminator.core.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inseminator.core.data.api.response.item.PengajuanItem
import com.example.inseminator.databinding.ListPengajuanBinding

class PengajuanAdapter : RecyclerView.Adapter<PengajuanAdapter.LibraryViewHolder>() {
    private var listGovernmentEffort = ArrayList<PengajuanItem>()
    private var onItemClickCallback: OnItemClickCallback? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newTernakItem: List<PengajuanItem>?) {
        if (newTernakItem == null) return
        listGovernmentEffort.clear()
        listGovernmentEffort.addAll(newTernakItem)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class LibraryViewHolder(private val binding: ListPengajuanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(PengajuanItem: PengajuanItem) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(PengajuanItem)
            }
            with(binding) {
                tvSapi.text = PengajuanItem.ternak.no_regis
                tvNamainseminator.text = PengajuanItem.insiminator.nama

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
        val binding =
            ListPengajuanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LibraryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
        val data = listGovernmentEffort[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listGovernmentEffort.size
    interface OnItemClickCallback {
        fun onItemClicked(news: PengajuanItem)
    }
}
