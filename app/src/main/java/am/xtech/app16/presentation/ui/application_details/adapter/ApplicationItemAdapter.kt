package am.xtech.app16.presentation.ui.application_details.adapter

import am.xtech.app16.R
import am.xtech.app16.data.model.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HistoryItemAdapter():RecyclerView.Adapter<ApplicationDetailViewHolder>() {

    private var listItem = ArrayList<Application>()

    fun submitList(newList: List<Application>) {
        this.listItem.clear()
        this.listItem.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplicationDetailViewHolder {

        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_application, parent, false) as View
        return ApplicationDetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: ApplicationDetailViewHolder, position: Int) {
        val item = listItem[position]
        holder.bind(item = listItem[position])
    }
}