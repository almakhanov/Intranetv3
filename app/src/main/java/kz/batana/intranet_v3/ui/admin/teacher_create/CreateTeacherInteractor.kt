package kz.batana.intranet_v3.ui.admin.teacher_create

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.batana.intranet_v3.SuperPooperMegaCoolApp.Companion.appDatabase
import kz.batana.intranet_v3.data.localDB.database.teacher_room.TeacherEntity

class CreateTeacherInteractor(private val presenter: CreateTeacherPresenter) : CreateTeacherMVP.Interactor {

    override fun saveTeacherEntity(teacherEntity: TeacherEntity) {
        Single.fromCallable {
            appDatabase.teacherDao().insertTeacher(teacherEntity)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }
}