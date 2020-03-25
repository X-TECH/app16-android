package am.xtech.app16.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Davit Kamavosyan
 */
object DateUtils {

    const val yyyy_MM_dd = "yyyy-MM-dd"
    const val yyyy_MMM = "yyyy MMM"
    const val HH_mm = "HH:mm"
    const val datePattern = "yyyy-MM-dd HH:mm:ss"

    fun convertDateFormatToFormat(
        _date: String?,
        formatFrom: String,
        formatTo: String,
        _locale: Locale
    ): String {

        if (_date.isNullOrEmpty()) {
            return ""
        }

        //todo  use _locale  value
        var locale = Locale("ru")

        val originalFormat: DateFormat = SimpleDateFormat(formatFrom, locale)
        val targetFormat: DateFormat = SimpleDateFormat(formatTo, locale)
        val date: Date = originalFormat.parse(_date) ?: return ""
        return targetFormat.format(date)
    }

}