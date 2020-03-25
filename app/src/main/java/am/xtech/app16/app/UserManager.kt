package am.xtech.app16.app

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonParseException
import java.util.*

open class UserManager(context: Context) {

    private val mContext: Context = context

    private val USER_UUID = "USER_UUID"
    private val USER_DATA = "USER_DATA"
    private val PREFS_FILE = "am.xtech.corona.pref_file"

    fun getUUID(): String {

        val mSharedPreferences = mContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        val uuid = mSharedPreferences?.getString(USER_UUID, "")

        if (uuid.isNullOrEmpty()) {
            val uniqueID = UUID.randomUUID().toString()
            saveUUID(uniqueID)
            return getUUID()
        }

        return uuid
    }

    private fun saveUUID(uuid: String) {
        val mSharedPreferences = mContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        val mEditor = mSharedPreferences.edit()
        mEditor.putString(USER_UUID, uuid)
        mEditor.apply()
    }

    open fun saveUserData(fName: String, lName: String, mName: String) {

        val user =
            User(
                deviceToken = getUUID(),
                firstName = fName,
                lastName = lName,
                middleName = mName
            )


        val mSharedPreferences = mContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        val mEditor = mSharedPreferences.edit()
        val jsonData = Gson().toJson(user)
        mEditor.putString(USER_DATA, jsonData)
        mEditor.apply()
    }

    private fun removeUserData() {
        val mSharedPreferences = mContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        val mEditor = mSharedPreferences.edit()
        mEditor.remove(USER_DATA)
        mEditor.apply()
    }

    open fun getUser(): User? {

        val mSharedPreferences = mContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        val jsonData = mSharedPreferences?.getString(USER_DATA, "")

        return if (jsonData == null) {
            null
        } else {
            try {
                Gson().fromJson(jsonData, User::class.java)
            } catch (e: JsonParseException) {
                null
            }
        }
    }

    open fun isLoggedIn(): Boolean {
        return getUser() == null
    }
}