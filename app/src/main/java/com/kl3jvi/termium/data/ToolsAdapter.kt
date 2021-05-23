package com.kl3jvi.termium.data

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.kl3jvi.termium.MainActivity
import com.kl3jvi.termium.R
import com.kl3jvi.termium.ToolDetails
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList
import android.view.animation.Animation
import android.view.animation.AnimationUtils


class ToolsAdapter(private val toolsList: MutableList<ToolItem>, private val context: Context) :
    RecyclerView.Adapter<ToolsAdapter.ToolsViewHolder>(), Filterable {

    var toolsFilterList = ArrayList<ToolItem>()
    private var lastPosition = -1
    init {

        toolsFilterList = toolsList as ArrayList<ToolItem>
    }

    class ToolsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_view);
        val title: TextView = itemView.findViewById(R.id.titleRow)
        val hint: TextView = itemView.findViewById(R.id.hintRow)
        val divider: View = itemView.findViewById(R.id.divider)
        val linearLayout: LinearLayout = itemView.findViewById(R.id.clickable)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.tools_row, parent, false)
        return ToolsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ToolsViewHolder, position: Int) {
        val currentItem = toolsFilterList[position]
        Picasso.get().load(currentItem.imageResource + currentItem.title).into(holder.imageView)
        holder.title.text = currentItem.title
        holder.hint.text = currentItem.hint
        val cd = ColorDrawable(randomColor)
        holder.divider.background = cd
        holder.linearLayout.setOnClickListener {
            val intent = Intent(context, ToolDetails::class.java)
            intent.putExtra("Title", currentItem.title)
            startActivity(context,intent,null)
        }
    }

    val randomColor: Int
        get() {
            return Color.rgb((30..200).random(), (30..200).random(), (30..200).random())
        }


    override fun getItemCount(): Int = toolsFilterList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    toolsFilterList = toolsList as ArrayList<ToolItem>
                } else {
                    val resultList = ArrayList<ToolItem>()
                    for (row in toolsList) {
                        if (row.title.lowercase(Locale.getDefault()).contains(constraint.toString()
                                .lowercase(Locale.getDefault()))) {
                            resultList.add(row)
                        }
                    }
                    toolsFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = toolsFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                toolsFilterList = results?.values as ArrayList<ToolItem>
                notifyDataSetChanged()
            }
        }
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            val animation: Animation =
                AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }
}