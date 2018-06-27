package kz.batana.intranet_v3.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kz.batana.intranet_v3.R

class SplashActivity : AppCompatActivity() {

    companion object {
        var asd = "accepted"
    }

    private var SPLASH_TIME_OUT : Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Log.d(asd, "SplashActivity is opened")
        Handler().postDelayed({
            run {
                val loginIntent = Intent(this, LoginActivity::class.java)
                startActivity(loginIntent)
                finish()
            }
        }, SPLASH_TIME_OUT)

    }
}
