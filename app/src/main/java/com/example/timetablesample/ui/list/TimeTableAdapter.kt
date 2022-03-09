package com.example.timetablesample.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.timetablesample.databinding.ItemTimeTableBinding
import com.example.timetablesample.domain.model.TimeTableModel

class TimeTableAdapter : RecyclerView.Adapter<TimeTableAdapter.MyViewHolder>() {



    private var listener :((TimeTableModel)->Unit)?=null

    var list = mutableListOf<TimeTableModel>()

    fun setContentList(list: MutableList<TimeTableModel>) {
        this.list = list
        notifyDataSetChanged()
    }


    class MyViewHolder(val viewHolder: ItemTimeTableBinding) :
        RecyclerView.ViewHolder(viewHolder.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TimeTableAdapter.MyViewHolder {
        val binding =
            ItemTimeTableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    fun itemClickListener(l:(TimeTableModel)->Unit){
        listener= l
    }

    override fun onBindViewHolder(holder: TimeTableAdapter.MyViewHolder, position: Int) {
        holder.viewHolder.timeTableItem = this.list[position]

        holder.viewHolder.root.setOnClickListener {
            listener?.let {
                it(this.list[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}