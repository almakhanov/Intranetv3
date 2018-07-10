package kz.batana.intranet_v3.ui.admin.student_profile

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.batana.intranet_v3.SuperPooperMegaCoolApp.Companion.appDatabase

class StudentProfileInteractor(private val presenter: StudentProfilePresenter) : StudentProfileMVP.Interactor{

    override fun newPasswordStudent(hashCode: Int, id: String) {
        Single.fromCallable {
            appDatabase.studentDao().updatePasswordStudent(hashCode,id)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

}