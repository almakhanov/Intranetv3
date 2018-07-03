package kz.batana.intranet_v3.ui.admin.student_profile

import android.arch.persistence.room.Room
import android.content.Context
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.batana.intranet_v3.data.localDB.database.admin_room.AdminDB
import kz.batana.intranet_v3.data.localDB.database.student_room.StudentDB
import kz.batana.intranet_v3.data.localDB.database.teacher_room.TeacherDB

class StudentProfileInteractor(private val presenter: StudentProfilePresenter,
                               ctx: Context) : StudentProfileMVP.Interactor{

    private var adminDB: AdminDB = Room.databaseBuilder(ctx, AdminDB::class.java, "admin").build()
    private var studentDB : StudentDB = Room.databaseBuilder(ctx, StudentDB::class.java, "student").build()
    private var teacherDB : TeacherDB = Room.databaseBuilder(ctx, TeacherDB::class.java, "teacher").build()


    override fun newPasswordStudent(hashCode: Int, id: String) {
        Single.fromCallable {
            studentDB.studentDao().updatePasswordStudent(hashCode,id)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

}