package pet.com.jetpetrescue.data.localstorage

import android.content.Context
import hoods.com.jetpetrescue.R
import pet.com.jetpetrescue.data.network.K.USER_TOKEN

class StoragePref(context: Context) {

    private val pref = context.getSharedPreferences(
        context.getString(R.string.app_name),
        Context.MODE_PRIVATE
    )

    fun getToken(): String =
        pref.getString(USER_TOKEN, null) ?: ""

    fun saveToken(token: String?) {
        pref.edit().apply {
            putString(USER_TOKEN, token)
            apply()
        }
    }
}