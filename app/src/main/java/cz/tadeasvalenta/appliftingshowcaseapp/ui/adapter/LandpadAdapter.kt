package cz.tadeasvalenta.appliftingshowcaseapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import cz.tadeasvalenta.appliftingshowcaseapp.data.entity.Landpad
import cz.tadeasvalenta.appliftingshowcaseapp.databinding.LandpadItemBinding
import java.util.*
import kotlin.collections.ArrayList

class LandpadAdapter(private val listener: LandpadItemListener) :
    RecyclerView.Adapter<LandpadViewHolder>(), Filterable {

    interface LandpadItemListener {
        fun onClickedLandpad(id: String)
    }

    private val items = ArrayList<Landpad>()
    private val allItems = ArrayList<Landpad>()

    fun setItems(items: List<Landpad>) {
        this.items.clear()
        this.items.addAll(items)
        this.allItems.clear()
        this.allItems.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandpadViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LandpadViewHolder(LandpadItemBinding.inflate(inflater, parent, false), listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: LandpadViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredItems = FilterResults()
                if (constraint.isNullOrBlank()) {
                    filteredItems.count = allItems.size
                    filteredItems.values = allItems
                } else {
                    val filtered = allItems.filter {
                        filterValue(it, constraint)
                    }
                    filteredItems.count = filtered.size
                    filteredItems.values = filtered
                }
                return filteredItems
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                items.clear()
                items.addAll(results?.values as List<Landpad>)
                notifyDataSetChanged()
            }
        }
    }

    private fun filterValue(it: Landpad, constraint: CharSequence): Boolean {
        val defaultLocale = Locale.getDefault()
        return it.fullName.toLowerCase(defaultLocale).contains(constraint) ||
            it.name.toLowerCase(defaultLocale).contains(constraint) ||
            it.status.toLowerCase(defaultLocale).contains(constraint) ||
            it.region.toLowerCase(defaultLocale).contains(constraint) ||
            it.type.toLowerCase(defaultLocale).contains(constraint) ||
            it.region.toLowerCase(defaultLocale).contains(constraint) ||
            it.locality.toLowerCase(defaultLocale).contains(constraint)
    }
}
