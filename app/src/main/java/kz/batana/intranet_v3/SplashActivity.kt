package kz.batana.intranet_v3

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kz.batana.intranet_v3.data.localDB.SharedPreference.LoginPreference
import kz.batana.intranet_v3.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    companion object {
        var TAG = "accepted"
        lateinit var username : String
        lateinit var password : String

        fun log(message: String){
            Log.d(TAG, message)
        }
    }

    private var SPLASH_TIME_OUT : Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //init of preference
        var stf = LoginPreference(this)

        if(!LoginPreference.getUsernamePref().isEmpty() && !LoginPreference.getPasswordPref().isEmpty()){
            username = LoginPreference.getUsernamePref()
            password = LoginPreference.getPasswordPref()
        }else{
            username = "no_username"
            password = "no_password"
        }

        log("SplashActivity is opened")
        Handler().postDelayed({
            run {
                val loginIntent = Intent(this, LoginActivity::class.java)
                startActivity(loginIntent)
                finish()
            }
        }, SPLASH_TIME_OUT)

    }
}
