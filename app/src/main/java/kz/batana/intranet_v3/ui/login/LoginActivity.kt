package kz.batana.intranet_v3.ui.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import kz.batana.intranet_v3.R
import kz.batana.intranet_v3.SplashActivity.Companion.log
import kz.batana.intranet_v3.SplashActivity.Companion.password
import kz.batana.intranet_v3.SplashActivity.Companion.username
import kz.batana.intranet_v3.SuperPooperMegaCoolApp.Companion.appDatabase
import kz.batana.intranet_v3.data.localDB.SharedPreference.LoginPreference.Companion.putLoginPref
import kz.batana.intranet_v3.data.localDB.database.admin_room.AdminEntity
import kz.batana.intranet_v3.data.localDB.database.student_room.StudentEntity
import kz.batana.intranet_v3.data.localDB.database.teacher_room.TeacherEntity
import kz.batana.intranet_v3.data.localDB.models.Admin
import kz.batana.intranet_v3.ui.admin.AdminNewActivity
import java.util.*

class LoginActivity : AppCompatActivity(), LoginMVP.View {

    private val presenter : LoginPresenter by lazy{LoginPresenter(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        log("LoginActivity is opened")

        //createAdmin()


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


    override fun message(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun openActivity(user: Any) {
        when(user){
            is StudentEntity -> {
                log("Starting StudentActivity...")
                message("No student activity")
            }
            is TeacherEntity -> {
                log("Starting TeacherActivity...")
                message("No teacher activity")
            }
            is AdminEntity -> {
                log("Starting AdminNewActivity...")
                startActivity(Intent(this, AdminNewActivity::class.java)
                        .putExtra("user", user))
                finish()
            }
        }
    }



        private fun createAdmin(){
        //CREATE DATE
        var date = Calendar.getInstance()
        date.set(Calendar.YEAR, 1998)
        date.set(Calendar.MONTH, 12)
        date.set(Calendar.DAY_OF_MONTH, 5)
        var d : Date = date.time
        //problem with sqlDate


        var birth = "1998_12_5"
        var rigis = "2018_6_25"


        //password
        var ps = "12345"

        var admin1 = Admin("Nursultan", "Almakhanov",birth, "87786340203",
                "nur.almakhanov@gmail.com", "Male", ps.hashCode(),rigis )



        var admin2 = AdminEntity(admin1.id, admin1.username, admin1.firstname, admin1.lastname, admin1.password,
                admin1.dateOfRegistration, admin1.dateOfBirth, admin1.telNumber, admin1.email, admin1.gender)


        Single.fromCallable {
            appDatabase.adminDao().insertAdmin(admin2)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()



        log("Admin created!")
    }



}
