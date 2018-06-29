package kz.batana.intranet_v3.ui.admin

import android.arch.persistence.room.Room
import android.content.Context
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.batana.intranet_v3.SplashActivity
import kz.batana.intranet_v3.data.localDB.database.admin_room.AdminDB
import kz.batana.intranet_v3.data.localDB.database.student_room.StudentDB
import kz.batana.intranet_v3.data.localDB.database.teacher_room.TeacherDB

class AdminInteractor(private val presenter: AdminPresenter,
                      private val ctx : Context) : AdminMVP.Interactor {

    private var adminDB: AdminDB
    private var studentDB : StudentDB
    private var teacherDB : TeacherDB

    init{
        //Create DB
        adminDB = Room.databaseBuilder(ctx, AdminDB::class.java, "admin").build()
        studentDB = Room.databaseBuilder(ctx, StudentDB::class.java, "student").build()
        teacherDB = Room.databaseBuilder(ctx, TeacherDB::class.java, "teacher").build()
    }

    override fun getStudentsList(){
        studentDB?.studentDao().getAllStudents()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { userList ->
                    Log.d(SplashActivity.asd, "AdminInteractor admin : $userList")
                    presenter.studentsFound(userList)
                }
    }

    override fun getTeachersList(){
        teacherDB?.teacherDao().getAllTeacher()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { userList ->
                    Log.d(SplashActivity.asd, "AdminInteractor admin : $userList")
                    presenter.teachersFound(userList)
                }
    }

    override fun getAdminsList(){
        adminDB?.adminDao().getAllAdmins()
        ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { userList ->
                    Log.d(SplashActivity.asd, "AdminInteractor admin : $userList")
                    presenter.adminsFound(userList)
                }
    }


}