package kz.batana.intranet_v3.ui.admin.teacher_create

import android.util.Log
import kz.batana.intranet_v3.SplashActivity
import kz.batana.intranet_v3.data.localDB.database.teacher_room.TeacherEntity
import kz.batana.intranet_v3.data.localDB.models.Teacher

class CreateTeacherPresenter(private val view: CreateTeacherMVP.View) : CreateTeacherMVP.Presenter {

    private val interactor = CreateTeacherInteractor(this, view as CreateTeacherActivity)

    override fun validated(firstname: String, lastname: String, tel: String, email: String, dateOfBirth: String): Boolean {
        when {
            firstname.isEmpty() -> {
                view.msg("First Name is empty!")
                return false
            }
            lastname.isEmpty() -> {
                view.msg("Last Name is empty!")
                return false
            }
            tel.isEmpty() -> {
                view.msg("Telephone Number is empty!")
                return false
            }
            email.isEmpty() -> {
                view.msg("Email is empty!")
                return false
            }
            dateOfBirth.equals("Date of Birth") -> {
                view.msg("Date of Birth is empty!")
                return false
            }
            else -> return true
        }
    }

    override fun saveTeacher(degreeS: String, firstnameS: String, lastnameS: String, dateOfBirthS: String, telS: String,
                             emailS: String, genderS: String, password: Int, dateOfRegisS: String) {
        var s = Teacher(degreeS, firstnameS, lastnameS, dateOfBirthS,telS,emailS,
                genderS,password.hashCode(),dateOfRegisS)

        Log.d(SplashActivity.asd, "Teacher : $s")

        var ss = TeacherEntity(s.id, s.username, s.firstname, s.lastname, s.password,
                s.dateOfRegistration, s.dateOfBirth, s.telNumber, s.email, s.gender, s.degree)

        interactor.saveTeacherEntity(ss)


        view.msg("Saved Successfully!")
        Log.d(SplashActivity.asd, "Teacher created! $ss")
    }

    override fun getDegreeList(): ArrayList<String> {
        return arrayListOf("Select Degree", "Bachelor", "Doctor", "Master", "Professor", "Academic")
    }
}