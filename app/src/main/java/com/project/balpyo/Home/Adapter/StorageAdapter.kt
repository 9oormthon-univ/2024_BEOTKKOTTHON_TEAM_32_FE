package com.project.balpyo.Home.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.balpyo.Script.Adapter.SubTopicAdapter
import com.project.balpyo.api.response.StorageListResult
import com.project.balpyo.databinding.RowStorageBinding
import com.project.balpyo.databinding.RowSubtopicBinding

class StorageAdapter (var result: List<StorageListResult>) :
    RecyclerView.Adapter<StorageAdapter.ViewHolder>() {
    private var onItemClickListener: ((Int) -> Unit)? = null
    private var context: Context? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int) {}
    }

    var itemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding =
            RowStorageBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = result?.get(position)!!.title
        if(result?.get(position)!!.voiceFilePath == null){
            holder.player.visibility = View.INVISIBLE
        }
    }

    override fun getItemCount() = result.size


    inner class ViewHolder(val binding: RowStorageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title = binding.textViewScriptTitle
        val player = binding.imageViewPlay

        init {
            binding.root.setOnClickListener {
                itemClickListener?.onItemClick(adapterPosition)
                true
            }
        }
    }

}