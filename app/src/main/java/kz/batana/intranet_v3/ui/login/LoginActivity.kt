package kz.batana.intranet_v3.ui.login

import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import kz.batana.intranet_v3.R
import kz.batana.intranet_v3.SplashActivity.Companion.asd
import kz.batana.intranet_v3.data.api.database.admin_room.AdminDB
import kz.batana.intranet_v3.data.api.database.admin_room.AdminEntity
import kz.batana.intranet_v3.data.api.database.student_room.StudentDB
import kz.batana.intranet_v3.data.api.database.student_room.StudentEntity
import kz.batana.intranet_v3.data.api.database.teacher_room.TeacherDB
import kz.batana.intranet_v3.data.api.database.teacher_room.TeacherEntity
import kz.batana.intranet_v3.data.api.models.Admin
import kz.batana.intranet_v3.ui.admin.AdminActivity
import kz.batana.intranet_v3.ui.admin.AdminNewActivity
import java.util.*

class LoginActivity : AppCompatActivity() {

    companion object {
        lateinit var adminDB: AdminDB
        lateinit var studentDB : StudentDB
        lateinit var teacherDB : TeacherDB
        lateinit var adminList: List<kz.batana.intranet_v3.data.api.database.admin_room.AdminEntity>
        lateinit var studentList: List<StudentEntity>
        lateinit var teacherList: List<TeacherEntity>
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Create DB
        adminDB = Room.databaseBuilder(this, AdminDB::class.java, "admin").build()
        studentDB = Room.databaseBuilder(this, StudentDB::class.java, "student").build()
        teacherDB = Room.databaseBuilder(this, TeacherDB::class.java, "teacher").build()

        //createAdmin()

        Log.d(asd, "LoginActivity is opened")
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
            loginFun()
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
            adminDB?.adminDao()?.insertAdmin(admin2)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()



        Log.d(asd, "Admin created!")
    }

    private fun loginFun(){
        var username = editTextUsernameLogin.text.toString()
        var password = editTextPasswordLogin.text.toString()
        Log.d(asd, "$username and $password")


        //getALLAdmins from DB and check
        adminDB?.adminDao()?.getAllAdmins()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { listOfAdmins ->
                    Log.d(asd, "AdminSize###: ${listOfAdmins.size}")
                    adminList = listOfAdmins

                    for(a in listOfAdmins){
                        Log.d(asd, "${password.hashCode()} == ${a.password}")
                        if(a.username.equals(username) and a.password.equals(password.hashCode())){
                            Log.d(asd, "LOGINED Admin!")
                            var adminIntent = Intent(this, AdminNewActivity::class.java)
                            adminIntent.putExtra("user", username)
                            startActivity(adminIntent)
                            finish()
                        }
                    }
                }



        studentDB?.studentDao()?.getAllStudents()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { listOfStudents ->
                    Log.d(asd, "listOfStudents###: ${listOfStudents.size}")
                    studentList = listOfStudents

                    for(a in listOfStudents){
                        Log.d(asd, "${password.hashCode()} == ${a.password}")
                        if(a.username.equals(username) and a.password.equals(password.hashCode())){
                            Log.d(asd, "LOGINED Student!")
                            var adminIntent = Intent(this, AdminActivity::class.java)
                            adminIntent.putExtra("user", username)
                            startActivity(adminIntent)
                            finish()
                        }
                    }
                }

        teacherDB?.teacherDao()?.getAllTeacher()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { listOfTeachers ->
                    Log.d(asd, "listOfStudents###: ${listOfTeachers.size}")
                    teacherList = listOfTeachers

                    for(a in listOfTeachers){
                        Log.d(asd, "${password.hashCode()} == ${a.password}")
                        if(a.username.equals(username) and a.password.equals(password.hashCode())){
                            Log.d(asd, "LOGINED Teacher!")
                            var adminIntent = Intent(this, AdminActivity::class.java)
                            adminIntent.putExtra("user", username)
                            startActivity(adminIntent)
                            finish()
                        }
                    }
                }
    }



}
