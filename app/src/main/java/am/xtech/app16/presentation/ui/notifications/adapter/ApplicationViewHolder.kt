package am.xtech.app16.presentation.ui.notifications.adapter

import am.xtech.app16.R
import am.xtech.app16.data.model.Application
import am.xtech.app16.presentation.base.BaseViewHolder
import am.xtech.app16.utils.DateUtils
import android.view.View
import android.widget.TextView
import java.util.*


class ApplicationViewHolder(
    itemView: View
) : BaseViewHolder<Application>(itemView) {

    private val createdDate = itemView.findViewById<TextView>(R.id.tv_item_history_date)
    private val startTime = itemView.findViewById<TextView>(R.id.tv_item_history_start_date)
    private val endTime = itemView.findViewById<TextView>(R.id.tv_item_history_end_date)

    override fun bind(item: Application) {

        val date = DateUtils.convertDateFormatToFormat(
            item.outDatetime, DateUtils.datePattern, DateUtils.yyyy_MM_dd,
            Locale.ENGLISH
        )
        val time1 = DateUtils.convertDateFormatToFormat(
            item.outDatetime,
            DateUtils.datePattern,
            DateUtils.HH_mm,
            Locale.ENGLISH
        )
        val time2 = DateUtils.convertDateFormatToFormat(
            item.plannedReturnDatetime,
            DateUtils.datePattern,
            DateUtils.HH_mm,
            Locale.ENGLISH
        )

        createdDate.text = date
        val text1 =  itemView.context.getString(R.string.out_time) +" "+ time1
        val text2 =  itemView.context.getString(R.string.back_time) +" "+ time2

        startTime.text = text1
        endTime.text = text2
    }


}


