package kz.batana.intranet_v3.ui.login

import android.arch.persistence.room.Room
import android.content.Context
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.batana.intranet_v3.SplashActivity.Companion.asd
import kz.batana.intranet_v3.data.localDB.database.admin_room.AdminDB
import kz.batana.intranet_v3.data.localDB.database.student_room.StudentDB
import kz.batana.intranet_v3.data.localDB.database.teacher_room.TeacherDB

class LoginInteractor(private val presenter: LoginMVP.Presenter, ctx: Context) : LoginMVP.Interactor {

    private var adminDB: AdminDB
    private var studentDB : StudentDB
    private var teacherDB : TeacherDB

    init{
        //Create DB
        adminDB = Room.databaseBuilder(ctx, AdminDB::class.java, "admin").build()
        studentDB = Room.databaseBuilder(ctx, StudentDB::class.java, "student").build()
        teacherDB = Room.databaseBuilder(ctx, TeacherDB::class.java, "teacher").build()
    }


    override fun getUser(username: String){
        adminDB?.adminDao()?.getAdmin(username)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { user ->
                    Log.d(asd, "LoginInteractor admin obj : $user")
                    presenter.userFound(user)
                }

        studentDB?.studentDao()?.getStudent(username)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { user ->
                    Log.d(asd, "LoginInteractor student obj : $user")
                    presenter.userFound(user)
                }

        teacherDB?.teacherDao()?.getTeacher(username)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { user ->
                    Log.d(asd, "LoginInteractor teacher obj : $user")
                    presenter.userFound(user)
                }
    }

    //    private fun createAdmin(){
//        //CREATE DATE
//        var date = Calendar.getInstance()
//        date.set(Calendar.YEAR, 1998)
//        date.set(Calendar.MONTH, 12)
//        date.set(Calendar.DAY_OF_MONTH, 5)
//        var d : Date = date.time
//        //problem with sqlDate
//
//
//        var birth = "1998_12_5"
//        var rigis = "2018_6_25"
//
//
//        //password
//        var ps = "12345"
//
//        var admin1 = Admin("Nursultan", "Almakhanov",birth, "87786340203",
//                "nur.almakhanov@gmail.com", "Male", ps.hashCode(),rigis )
//
//
//
//        var admin2 = AdminEntity(admin1.id, admin1.username, admin1.firstname, admin1.lastname, admin1.password,
//                admin1.dateOfRegistration, admin1.dateOfBirth, admin1.telNumber, admin1.email, admin1.gender)
//
//
//        Single.fromCallable {
//            adminDB?.adminDao()?.insertAdmin(admin2)
//        }.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe()
//
//
//
//        Log.d(asd, "Admin created!")
//    }
}