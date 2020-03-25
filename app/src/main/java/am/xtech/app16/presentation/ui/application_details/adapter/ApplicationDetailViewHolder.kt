package am.xtech.app16.presentation.ui.application_details.adapter

import am.xtech.app16.R
import am.xtech.app16.data.model.Application
import am.xtech.app16.presentation.base.BaseViewHolder
import am.xtech.app16.presentation.ui.application_details.ModelDetailItem
import am.xtech.app16.utils.DateUtils
import android.view.View
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.*


class ApplicationDetailViewHolder(
    itemView: View
) : BaseViewHolder<ModelDetailItem>(itemView) {

    private val title = itemView.findViewById<TextInputLayout>(R.id.til_item_application_detail_title)
    private val description = itemView.findViewById<TextInputEditText>(R.id.et_item_application_detail_description)

    override fun bind(item: ModelDetailItem) {
        title.hint = item.title
        description.setText(item.description)
    }
}


