package com.project.balpyo.BottomSheetAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.project.balpyo.BottomSheet.BottomSheetListener
import com.project.balpyo.BottomSheetData.BottomSheetItem
import com.project.balpyo.Home.ViewModel.StorageViewModel
import com.project.balpyo.MainActivity
import com.project.balpyo.R
class BottomSheetAdapter(
    private val items: MutableList<BottomSheetItem>,
    var scriptId: MutableList<Long>,
    var viewModel: StorageViewModel,
    var mainActivity: MainActivity,
    var listener: BottomSheetListener
) : RecyclerView.Adapter<BottomSheetAdapter.ViewHolder>() {
    private var selectedPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bottomsheet_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = items[position].title

        // 선택된 항목에 따라 테두리 색상 변경
        if (selectedPosition == position) {
            holder.background.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.selectted_title)
        } else {
            holder.background.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.unselected_title)
        }


        // Button에 대한 클릭 리스너 추가
        holder.title.setOnClickListener {
            handleItemClick(position, holder)
        }
    }

    private fun handleItemClick(position: Int, holder: ViewHolder) {
        selectedPosition = position
        notifyDataSetChanged()
        listener.onItemClicked(position)
    }


    override fun getItemCount(): Int = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var background : ConstraintLayout  = itemView.findViewById(R.id.BottomSheetCL)
        val title: Button = itemView.findViewById(R.id.BottomSheetBtn)
    }
}
