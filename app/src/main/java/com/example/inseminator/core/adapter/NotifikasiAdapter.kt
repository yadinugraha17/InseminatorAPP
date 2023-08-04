package com.example.inseminator.core.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inseminator.R
import com.example.inseminator.databinding.ListNotifikasiBinding
import com.example.myapplication.core.data.api.response.item.NotifikasiItem
class NotifikasiAdapter : RecyclerView.Adapter<NotifikasiAdapter.LibraryViewHolder>() {
    private var listGovernmentEffort = ArrayList<NotifikasiItem>()
    private var onItemClickCallback: OnItemClickCallback? = null
    @SuppressLint("NotifyDataSetChanged")
    fun setData(newTernakItem: List<NotifikasiItem>?) {
        if (newTernakItem == null) return
        listGovernmentEffort.clear()
        listGovernmentEffort.addAll(newTernakItem)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {

        this.onItemClickCallback = onItemClickCallback
    }

    inner class LibraryViewHolder(private val binding: ListNotifikasiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(notifikasiItem: NotifikasiItem) {
            binding.root.setOnClickListener {

                onItemClickCallback?.onItemClicked(notifikasiItem)
            }
            with(binding) {
                tvNotif.text = notifikasiItem.notif
                if (notifikasiItem.isRead == "1") {
                    // Tampilan jika notifikasi telah dibaca
//                    cardview.visibility = View.GONE
                    clNotif.setBackgroundResource(R.color.read_notification_background)
                    // Atur ikon, teks, atau warna sesuai kebutuhan
                } else {
                    // Tampilan jika notifikasi belum dibaca
//                    cardview.visibility = View.VISIBLE
                    clNotif.setBackgroundResource(R.color.primary)

                    // Atur ikon, teks, atau warna sesuai kebutuhan
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
        val binding =
            ListNotifikasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LibraryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
        val data = listGovernmentEffort[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listGovernmentEffort.size
    interface OnItemClickCallback {
        fun onItemClicked(news: NotifikasiItem)
    }
}