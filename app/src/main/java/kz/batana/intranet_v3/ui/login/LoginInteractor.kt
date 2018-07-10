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

}