package kz.batana.intranet_v3.ui.admin

import android.util.Log
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.batana.intranet_v3.SplashActivity
import kz.batana.intranet_v3.SplashActivity.Companion.log
import kz.batana.intranet_v3.SuperPooperMegaCoolApp.Companion.appDatabase
import kz.batana.intranet_v3.data.localDB.database.suggestions_room.SuggestionsEntity

class AdminInteractor(private val presenter: AdminPresenter) : AdminMVP.Interactor {

    override fun getStudentsList(){
        appDatabase.studentDao().getAllStudents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { userList ->
                    Log.d(SplashActivity.TAG, "AdminInteractor getStudentsList : ${userList.size}")
                    presenter.studentsFound(userList)
                }
    }

    override fun getTeachersList(){
        appDatabase.teacherDao().getAllTeacher()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { userList ->
                    Log.d(SplashActivity.TAG, "AdminInteractor getTeachersList : ${userList.size}")
                    presenter.teachersFound(userList)
                }
    }

    override fun getAdminsList(){
        appDatabase.adminDao().getAllAdmins()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { userList ->
                    Log.d(SplashActivity.TAG, "AdminInteractor getAdminsList : ${userList.size}")
                    presenter.adminsFound(userList)
                }
    }

    override fun saveSuggestion(query: SuggestionsEntity) {
        Single.fromCallable {
            appDatabase.suggestionsDao().insertSuggestion(query)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    override fun getSuggestions() {
        appDatabase.suggestionsDao().getAllSuggestions()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { sgs ->
                    log("AdminInteractor getAdminsList : ${sgs.size}")
                    presenter.suggestionsFound(sgs)
                }
    }




}