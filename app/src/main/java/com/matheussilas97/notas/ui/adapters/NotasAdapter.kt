 package com.matheussilas97.notas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.matheussilas97.notas.data.Nota
import kotlinx.android.synthetic.main.item_note.view.*

class NotasViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun bindView(item: Nota) {
        with(view) {
            tv_nota.text = item.text
        }
    }
}

class NotasAdapter(val data: MutableList<Nota> = mutableListOf(), val context: Context) :
    RecyclerView.Adapter<NotasViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotasViewHolder {
        return NotasViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NotasViewHolder, position: Int) {
        holder.bindView(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun add(item: Nota) {
        data.add(item)
        notifyDataSetChanged()
    }

    fun addAll(itens: List<Nota>){
        data.clear()
        data.addAll(itens)
        notifyDataSetChanged()
    }

    fun remove(item: Nota) {
        data.remove(item)
        notifyDataSetChanged()
    }
}