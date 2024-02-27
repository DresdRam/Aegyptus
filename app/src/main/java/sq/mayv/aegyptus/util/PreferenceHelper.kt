package sq.mayv.aegyptus.util

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {

    private const val PREFERENCE_NAME = "AEGYPTUS"

    private const val AUTH_TOKEN = "AUTH_TOKEN"
    private const val BASE_URL = "BASE_URL"

    fun getPreference(context: Context): SharedPreferences =
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val edit = edit()
        operation(edit)
        edit.apply()
    }

    var SharedPreferences.token
        get() = getString(AUTH_TOKEN, "")!!
        set(value) {
            edit {
                it.putString(AUTH_TOKEN, value)
            }
        }

    var SharedPreferences.baseUrl
        get() = getString(BASE_URL, "https://aegyptus-api.onrender.com/")!!
        set(value) {
            edit {
                it.putString(BASE_URL, value)
            }
        }

}