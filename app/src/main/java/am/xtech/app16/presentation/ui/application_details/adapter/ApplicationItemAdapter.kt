package am.xtech.app16.presentation.ui.application_details.adapter

import am.xtech.app16.R
import am.xtech.app16.data.model.Application
import am.xtech.app16.presentation.ui.application_details.ModelDetailItem
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ApplicationItemAdapter():RecyclerView.Adapter<ApplicationDetailViewHolder>() {

    private var listItem = ArrayList<ModelDetailItem>()

    fun submitList(newList: List<ModelDetailItem>) {
        this.listItem.clear()
        this.listItem.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplicationDetailViewHolder {

        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_application_detail, parent, false) as View
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