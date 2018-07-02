package kz.batana.intranet_v3.ui.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kz.batana.intranet_v3.R
import kz.batana.intranet_v3.SplashActivity.Companion.asd
import kz.batana.intranet_v3.SplashActivity.Companion.password
import kz.batana.intranet_v3.SplashActivity.Companion.username
import kz.batana.intranet_v3.data.localDB.SharedPreference.LoginPreference.Companion.putLoginPref
import kz.batana.intranet_v3.data.localDB.database.admin_room.AdminEntity
import kz.batana.intranet_v3.data.localDB.database.student_room.StudentEntity
import kz.batana.intranet_v3.data.localDB.database.teacher_room.TeacherEntity
import kz.batana.intranet_v3.ui.admin.AdminNewActivity

class LoginActivity : AppCompatActivity(), LoginMVP.View {

    private val presenter : LoginPresenter by lazy{LoginPresenter(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        Log.d(asd, "LoginActivity is opened")

        presenter.login(username, password)

    }


    override fun onResume() {
        super.onResume()

        signUpBtn.setOnClickListener{
            Toast.makeText(this, "Say Admin to create you an Email", Toast.LENGTH_SHORT).show()
        }

        forgotPasswordBtn.setOnClickListener{
            Toast.makeText(this, "This function is in process of realization...", Toast.LENGTH_SHORT).show()
        }

        loginBtn.setOnClickListener{
            var username = editTextUsernameLogin.text.toString()
            var password = editTextPasswordLogin.text.toString()
            putLoginPref(username, password)
            presenter.login(username, password)
        }


    }


    override fun msg(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

    override fun openActivity(user: Any) {
        when(user){
            is StudentEntity -> {
                Log.d(asd, "Starting StudentActivity...")
                msg("No student activity")
            }
            is TeacherEntity -> {
                Log.d(asd, "Starting TeacherActivity...")
                msg("No teacher activity")
            }
            is AdminEntity -> {
                Log.d(asd, "Starting AdminNewActivity...")
                startActivity(Intent(this, AdminNewActivity::class.java)
                        .putExtra("user", user))
                finish()
            }
        }
    }

}
