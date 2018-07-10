package kz.batana.intranet_v3.ui.admin.student_create

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.batana.intranet_v3.SuperPooperMegaCoolApp.Companion.appDatabase
import kz.batana.intranet_v3.data.localDB.database.student_room.StudentEntity

class CreateStudentInteractor(private val presenter: CreateStudentPresenter) : CreateStudentMVP.Interactor {


    override fun saveStudentEntity(ss: StudentEntity) {
        Single.fromCallable {
            appDatabase.studentDao().insertStudent(ss)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }
}