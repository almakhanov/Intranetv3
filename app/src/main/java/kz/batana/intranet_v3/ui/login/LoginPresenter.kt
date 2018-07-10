package kz.batana.intranet_v3.ui.login

import android.util.Log
import kz.batana.intranet_v3.SplashActivity.Companion.TAG
import kz.batana.intranet_v3.data.localDB.database.admin_room.AdminEntity
import kz.batana.intranet_v3.data.localDB.database.student_room.StudentEntity
import kz.batana.intranet_v3.data.localDB.database.teacher_room.TeacherEntity

class LoginPresenter(private val view: LoginMVP.View) : LoginMVP.Presenter {

    private val interactor = LoginInteractor(this)

    private var pass : String = ""
    private var userN : String = ""

    override fun userFound(user_: Any) {
        var user = user_

        when(user){
            is StudentEntity->{
                if(user.password.equals(pass.hashCode())){
                    view.openActivity(user)
                    Log.d(TAG, "Student Presenter")
                    view.message("Welcome ${user.firstname}!")
                }else{
                    view.message("Invalid Password!")
                }
            }
            is TeacherEntity->{
                if(user.password.equals(pass.hashCode())){
                    view.openActivity(user)
                    Log.d(TAG, "Teacher Presenter")
                    view.message("Welcome ${user.firstname}!")
                }else{
                    view.message("Invalid Password!")
                }
            }
            is AdminEntity->{
                if(user.password.equals(pass.hashCode())){
                    view.openActivity(user)
                    Log.d(TAG, "Admin Presenter")
                    view.message("Welcome ${user.firstname}!")
                }else{
                    view.message("Invalid Password!")
                }
            }
            else->{
                view.message("ERROR in LoginPresenter!")
            }
        }
    }


    override fun login(username: String, password: String) {
        userN = username
        pass = password
        if(username.isEmpty() || password.isEmpty()){
            view.message("Empty username or password!")
        }else{
            interactor.getUser(username)
        }
    }


}