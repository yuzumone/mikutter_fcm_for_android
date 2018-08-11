package net.yuzumone.mikutter.fcm.ui.common

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

class BindingHolder<out T : ViewDataBinding>(val binding: T) :
        RecyclerView.ViewHolder(binding.root)