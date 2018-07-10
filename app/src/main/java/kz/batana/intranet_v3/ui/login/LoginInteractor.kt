package kz.batana.intranet_v3.ui.login

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.batana.intranet_v3.SplashActivity.Companion.TAG
import kz.batana.intranet_v3.SuperPooperMegaCoolApp.Companion.appDatabase

class LoginInteractor(private val presenter: LoginMVP.Presenter) : LoginMVP.Interactor {

    override fun getUser(username: String){
        appDatabase.adminDao().getAdmin(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { user ->
                    Log.d(TAG, "LoginInteractor admin obj : $user")
                    presenter.userFound(user)
                }

        appDatabase.studentDao().getStudent(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { user ->
                    Log.d(TAG, "LoginInteractor student obj : $user")
                    presenter.userFound(user)
                }

        appDatabase.teacherDao().getTeacher(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { user ->
                    Log.d(TAG, "LoginInteractor teacher obj : $user")
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
//        Log.d(TAG, "Admin created!")
//    }
}