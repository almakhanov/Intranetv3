package kz.batana.intranet_v3.ui.admin.teacher_create

import android.arch.persistence.room.Room
import android.content.Context
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.batana.intranet_v3.data.localDB.database.teacher_room.TeacherDB
import kz.batana.intranet_v3.data.localDB.database.teacher_room.TeacherEntity

class CreateTeacherInteractor(private val presenter: CreateTeacherPresenter,
                              private val ctx: Context) : CreateTeacherMVP.Interactor {


    private var teacherDB : TeacherDB

    init{
        //Create DB
        teacherDB = Room.databaseBuilder(ctx, TeacherDB::class.java, "student").build()
    }

    override fun saveTeacherEntity(ss: TeacherEntity) {
        Single.fromCallable {
            teacherDB?.teacherDao()?.insertTeacher(ss)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }
}