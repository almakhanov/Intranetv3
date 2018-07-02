package kz.batana.intranet_v3.ui.admin.student_create

import android.arch.persistence.room.Room
import android.content.Context
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.batana.intranet_v3.data.localDB.database.student_room.StudentDB
import kz.batana.intranet_v3.data.localDB.database.student_room.StudentEntity

class CreateStudentInteractor(private val presenter: CreateStudentPresenter,
                              ctx: Context) : CreateStudentMVP.Interactor {

    private var studentDB : StudentDB = Room.databaseBuilder(ctx, StudentDB::class.java, "student").build()

    override fun saveStudentEntity(ss: StudentEntity) {
        Single.fromCallable {
            studentDB.studentDao().insertStudent(ss)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }
}