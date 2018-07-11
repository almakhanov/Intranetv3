package kz.batana.intranet_v3.ui.admin

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.batana.intranet_v3.SplashActivity.Companion.log
import kz.batana.intranet_v3.SuperPooperMegaCoolApp.Companion.appDatabase
import kz.batana.intranet_v3.data.localDB.database.admin_room.AdminEntity
import kz.batana.intranet_v3.data.localDB.database.student_room.StudentEntity
import kz.batana.intranet_v3.data.localDB.database.suggestions_room.SuggestionsEntity
import kz.batana.intranet_v3.data.localDB.database.teacher_room.TeacherEntity


class AdminInteractor(private val presenter: AdminPresenter) : AdminMVP.Interactor{

    override fun getStudentsList(): Flowable<List<StudentEntity>> {
        return appDatabase.studentDao().getAllStudents()
    }

    override fun getTeachersList(): Flowable<List<TeacherEntity>> {
        return appDatabase.teacherDao().getAllTeacher()
    }

    override fun getAdminsList(): Flowable<List<AdminEntity>> {
        return appDatabase.adminDao().getAllAdmins()
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