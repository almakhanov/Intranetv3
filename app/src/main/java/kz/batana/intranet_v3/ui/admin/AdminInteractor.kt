package kz.batana.intranet_v3.ui.admin

import android.arch.persistence.room.Room
import android.content.Context
import android.util.Log
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.batana.intranet_v3.SplashActivity
import kz.batana.intranet_v3.SplashActivity.Companion.log
import kz.batana.intranet_v3.data.localDB.database.admin_room.AdminDB
import kz.batana.intranet_v3.data.localDB.database.student_room.StudentDB
import kz.batana.intranet_v3.data.localDB.database.suggestions_room.SuggestionsDB
import kz.batana.intranet_v3.data.localDB.database.suggestions_room.SuggestionsEntity
import kz.batana.intranet_v3.data.localDB.database.teacher_room.TeacherDB

class AdminInteractor(private val presenter: AdminPresenter,
                      ctx : Context) : AdminMVP.Interactor {


    private var adminDB: AdminDB = Room.databaseBuilder(ctx, AdminDB::class.java, "admin").build()
    private var studentDB : StudentDB = Room.databaseBuilder(ctx, StudentDB::class.java, "student").build()
    private var teacherDB : TeacherDB = Room.databaseBuilder(ctx, TeacherDB::class.java, "teacher").build()
    private var suggestionsDB : SuggestionsDB = Room.databaseBuilder(ctx, SuggestionsDB::class.java, "suggestions").build()


    override fun getStudentsList(){
        studentDB.studentDao().getAllStudents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { userList ->
                    Log.d(SplashActivity.TAG, "AdminInteractor getStudentsList : ${userList.size}")
                    presenter.studentsFound(userList)
                }
    }

    override fun getTeachersList(){
        teacherDB.teacherDao().getAllTeacher()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { userList ->
                    Log.d(SplashActivity.TAG, "AdminInteractor getTeachersList : ${userList.size}")
                    presenter.teachersFound(userList)
                }
    }

    override fun getAdminsList(){
        adminDB.adminDao().getAllAdmins()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { userList ->
                    Log.d(SplashActivity.TAG, "AdminInteractor getAdminsList : ${userList.size}")
                    presenter.adminsFound(userList)
                }
    }

    override fun saveSuggestion(query: SuggestionsEntity) {
        Single.fromCallable {
            suggestionsDB.suggestionsDao().insertSuggestion(query)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    override fun getSuggestions() {
        suggestionsDB.suggestionsDao().getAllSuggestions()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { sgs ->
                    log("AdminInteractor getAdminsList : ${sgs.size}")
                    presenter.suggestionsFound(sgs)
                }
    }




}