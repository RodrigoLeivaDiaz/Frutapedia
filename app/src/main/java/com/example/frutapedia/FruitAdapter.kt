package com.example.frutapedia

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FruitAdapter(private var fruitsApiList: List<FruitsApi>, val context: Context) :
    RecyclerView.Adapter<FruitAdapter.ViewHolder>() {

    lateinit var onItemClickListener: (FruitsApi) -> Unit
    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val tvName: TextView = view.findViewById(R.id.textViewNane)
        private val tvImage: ImageView = view.findViewById(R.id.imageViewFrutas)

        fun bind(fruits: FruitsApi) {
            tvName.text = fruits.name
            Glide.with(context).load(fruits.image).into(tvImage)
            view.setOnClickListener {
                onItemClickListener(fruits)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruitsResponse = fruitsApiList[position]
        holder.bind(fruitsResponse)
    }

    override fun getItemCount(): Int {
        return fruitsApiList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.listitem, parent, false)
        return ViewHolder(view)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<FruitsApi>() {
        override fun areItemsTheSame(oldItem: FruitsApi, newItem: FruitsApi): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FruitsApi, newItem: FruitsApi): Boolean {
            return oldItem == newItem
        }
    }
}
