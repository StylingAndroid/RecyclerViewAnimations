package com.stylingandroid.recyclerviewanimations

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MyAdapter(private val string: String) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    private val items: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item, parent, false)
                    .run {
                        ViewHolder(this)
                    }

    override fun getItemCount(): Int = items.size

    fun appendItem(newString: String) =
            items.add(uniqueString(newString)).also {
                notifyItemInserted(itemCount - 1)
            }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            bind(items[position])
        }
    }

    private fun uniqueString(base: String) =
            "$base ${(Math.random() * 1000).toInt()}"

    inner class ViewHolder(
            itemView: View,
            private val textView: TextView = itemView.findViewById(android.R.id.text1),
            addButton: View = itemView.findViewById(R.id.add),
            removeButton: View = itemView.findViewById(R.id.remove)
    ) : RecyclerView.ViewHolder(itemView) {

        init {
            addButton.setOnClickListener(insert())
            removeButton.setOnClickListener(remove())
        }

        private fun insert(): (View) -> Unit = {
            layoutPosition.also { currentPosition ->
                items.add(currentPosition, uniqueString(string))
                notifyItemInserted(currentPosition)
            }
        }

        private fun remove(): (View) -> Unit = {
            layoutPosition.also { currentPosition ->
                items.removeAt(currentPosition)
                notifyItemRemoved(currentPosition)
            }
        }

        fun bind(text: String) {
            textView.text = text
        }
    }
}
