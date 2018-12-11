package net.yuzumone.mikutter.fcm.ui.common

import androidx.databinding.ViewDataBinding
import androidx.annotation.UiThread
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup

abstract class BindingRecyclerAdapter<T, V : ViewDataBinding> :
        RecyclerView.Adapter<BindingHolder<V>>(), Iterable<T> {

    private var items: List<T>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder<V> {
        val binding = createBinding(parent)
        return BindingHolder(binding)
    }

    protected abstract fun createBinding(parent: ViewGroup): V

    override fun onBindViewHolder(holder: BindingHolder<V>, position: Int) {
        bind(holder.binding, items!![position])
        holder.binding.executePendingBindings()
    }

    protected abstract fun bind(binding: V, item: T)

    @UiThread
    fun update(update: List<T>?) {
        when {
            items == null -> {
                if (update == null) {
                    return
                }
                items = update
                notifyDataSetChanged()
            }
            update == null -> {
                val oldSize = items!!.size
                items = null
                notifyItemRangeRemoved(0, oldSize)
            }
            else -> {
                val result = DiffUtil.calculateDiff(RecyclerDiffCallback(items!!, update), false)
                items = update
                result.dispatchUpdatesTo(this)
            }
        }
    }

    fun getItem(position: Int): T {
        return items!![position]
    }

    override fun getItemCount(): Int {
        return if (items == null) 0 else items!!.size
    }

    override fun iterator(): Iterator<T> {
        return items!!.iterator()
    }

    inner class RecyclerDiffCallback(private val old: List<T>, private val new :List<T>) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = old.size

        override fun getNewListSize(): Int = new.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return old[oldItemPosition] == new[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return old[oldItemPosition] == new[newItemPosition]
        }
    }

}