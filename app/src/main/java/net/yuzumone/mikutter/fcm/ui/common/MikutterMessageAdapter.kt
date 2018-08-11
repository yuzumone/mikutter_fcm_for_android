package net.yuzumone.mikutter.fcm.ui.common

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import net.yuzumone.mikutter.fcm.R
import net.yuzumone.mikutter.fcm.databinding.ItemMikutterMessageBinding
import net.yuzumone.mikutter.fcm.entity.MikutterMessage

class MikutterMessageAdapter(
        private val messageClickCallback: ((MikutterMessage) -> Unit)?
) : BindingRecyclerAdapter<MikutterMessage, ItemMikutterMessageBinding>() {

    override fun createBinding(parent: ViewGroup): ItemMikutterMessageBinding {
        val binding = DataBindingUtil.inflate<ItemMikutterMessageBinding>(LayoutInflater.from(parent.context),
                R.layout.item_mikutter_message, parent, false)
        binding.root.setOnClickListener {
            binding.message?.let {
                messageClickCallback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: ItemMikutterMessageBinding, item: MikutterMessage) {
        binding.message = item
    }

}