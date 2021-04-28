package cz.tadeasvalenta.appliftingshowcaseapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import cz.tadeasvalenta.appliftingshowcaseapp.data.entity.Capsule
import cz.tadeasvalenta.appliftingshowcaseapp.databinding.CapsuleItemBinding
import java.util.*
import kotlin.collections.ArrayList

class CapsuleAdapter : RecyclerView.Adapter<CapsuleViewHolder>(), Filterable {

    private val items = ArrayList<Capsule>()
    private val allItems = ArrayList<Capsule>()

    fun setItems(items: ArrayList<Capsule>) {
        this.items.clear()
        this.items.addAll(items)
        this.allItems.clear()
        this.allItems.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CapsuleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CapsuleViewHolder(CapsuleItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CapsuleViewHolder, position: Int) {
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
                items.addAll(results?.values as List<Capsule>)
                notifyDataSetChanged()
            }
        }
    }

    private fun filterValue(it: Capsule, constraint: CharSequence): Boolean {
        val defaultLocale = Locale.getDefault()
        return it.lastUpdate.toLowerCase(defaultLocale).contains(constraint) ||
            it.serial.toLowerCase(defaultLocale).contains(constraint) ||
            it.status.toLowerCase(defaultLocale).contains(constraint) ||
            it.type.toLowerCase(defaultLocale).contains(constraint)
    }
}
