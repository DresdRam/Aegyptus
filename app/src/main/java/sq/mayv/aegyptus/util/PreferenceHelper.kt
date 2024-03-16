package sq.mayv.aegyptus.util

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {

    private const val PREFERENCE_NAME = "AEGYPTUS"

    private const val AUTH_TOKEN = "AUTH_TOKEN"
    private const val USER_NAME = "USER_NAME"
    private const val USER_EMAIL = "USER_EMAIL"
    private const val BASE_URL = "BASE_URL"
    private const val FIRST_LAUNCH = "FIRST_LAUNCH"
    private const val NEARBY_THRESHOLD = "NEARBY_THRESHOLD"
    private const val MAP_TYPE = "MAP_TYPE"
    private const val TRAFFIC_MODE = "TRAFFIC_MODE"

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

    var SharedPreferences.userName
        get() = getString(USER_NAME, "Tout Ankh Amon")!!
        set(value) {
            edit {
                it.putString(USER_NAME, value)
            }
        }

    var SharedPreferences.userEmail
        get() = getString(USER_EMAIL, "ankh@aegyptus.com")!!
        set(value) {
            edit {
                it.putString(USER_EMAIL, value)
            }
        }

    var SharedPreferences.nearbyThreshold
        get() = getInt(NEARBY_THRESHOLD, 1)!!
        set(value) {
            edit {
                it.putInt(NEARBY_THRESHOLD, value)
            }
        }

    var SharedPreferences.mapType
        get() = getInt(MAP_TYPE, 1)!!
        set(value) {
            edit {
                it.putInt(MAP_TYPE, value)
            }
        }

    var SharedPreferences.trafficMode
        get() = getBoolean(TRAFFIC_MODE, true)!!
        set(value) {
            edit {
                it.putBoolean(TRAFFIC_MODE, value)
            }
        }

    var SharedPreferences.baseUrl
        get() = getString(BASE_URL, "https://aegyptus-api.onrender.com/")!!
        set(value) {
            edit {
                it.putString(BASE_URL, value)
            }
        }

    var SharedPreferences.firstLaunch
        get() = getBoolean(FIRST_LAUNCH, true)!!
        set(value) {
            edit {
                it.putBoolean(FIRST_LAUNCH, value)
            }
        }

}