package moe.nikolay.nwcode.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import moe.nikolay.nwcode.R
import moe.nikolay.nwcode.repository.api.pixaby.models.PixabayImagesModel

class CategoriesAdapter: RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    var callback: Callback? = null
    private var categories = PixabayImagesModel.Categories.values()

    interface Callback {
        fun onClick(categoryId: Int)
    }

    override fun getItemId(position: Int): Long {
        return categories[position].id
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.component_categories_card, parent, false)
        return ViewHolder(view, viewType)
    }

    override fun onBindViewHolder(holder: CategoriesAdapter.ViewHolder, position: Int) {
        val nowCategory = categories[position]
        holder.title.text = if (nowCategory.category_ru.isBlank()) nowCategory.category_en else nowCategory.category_ru
        holder.view.setOnClickListener {
            callback?.onClick(getItemId(position).toInt())
        }
    }

    class ViewHolder internal constructor(view: View, val viewType: Int) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.category_card_title)
        val view = view
    }

}