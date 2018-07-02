package kz.batana.intranet_v3.data.localDB.SharedPreference

import android.content.Context
import android.content.SharedPreferences



class LoginPreference(ctx: Context) {

    init {
        context = ctx
    }

    companion object {
        private val USERNAME : String = "USERNAME"
        private val PASSWORD : String = "PASSWORD"
        lateinit var settings : SharedPreferences
        lateinit var editor : SharedPreferences.Editor
        lateinit var context : Context

        fun inital(){
            settings = context.getSharedPreferences(USERNAME, Context.MODE_PRIVATE)
            settings = context.getSharedPreferences(PASSWORD, Context.MODE_PRIVATE)
            editor = settings.edit()
        }

        fun putLoginPref(username: String, password: String){
            inital()
            editor.putString(USERNAME, username)
            editor.putString(PASSWORD, password)
            editor.apply()
        }

        fun getUsernamePref() : String {
            inital()
            return settings.getString(USERNAME, "")
        }

        fun getPasswordPref() : String {
            inital()
            return settings.getString(PASSWORD, "")
        }
    }
}