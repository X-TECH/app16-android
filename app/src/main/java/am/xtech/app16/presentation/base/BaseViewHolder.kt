package am.xtech.app16.presentation.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T> internal constructor(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: T)
}