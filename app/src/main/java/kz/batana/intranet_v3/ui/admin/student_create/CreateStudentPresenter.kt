package kz.batana.intranet_v3.ui.admin.student_create

import android.util.Log
import kz.batana.intranet_v3.SplashActivity.Companion.asd
import kz.batana.intranet_v3.data.localDB.database.student_room.StudentEntity
import kz.batana.intranet_v3.data.localDB.models.Student

class CreateStudentPresenter(private val view: CreateStudentMVP.View) : CreateStudentMVP.Presenter {

    private val interactor = CreateStudentInteractor(this, view as CreateStudentActivity)

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

    override fun saveStudent(yearOfStudyS: Int, facultyS: String, specS: String, firstnameS: String, lastnameS: String,
                             dateOfBirthS: String, telS: String, emailS: String, genderS: String, password: Int,
                             dateOfRegisS: String) {

        var s = Student(yearOfStudyS, facultyS, specS, firstnameS, lastnameS, dateOfBirthS,telS,emailS,
                genderS,password.hashCode(),dateOfRegisS)

        Log.d(asd, "Student : $s")

        var ss = StudentEntity(s.id, s.username, s.firstname, s.lastname, s.password,
                s.dateOfRegistration, s.dateOfBirth, s.telNumber, s.email, s.gender, s.faculty, s.specialization,
                s.yearOfStudy)


        interactor.saveStudentEntity(ss)

        view.msg("Saved Successfully!")
        Log.d(asd, "Student created!")
    }

    override fun getSpecList(): ArrayList<String> {
        return arrayListOf("Select your Specialization","Information Systems", "Computer Systems and Software", "Automation and Control", "Management",
                "Finance","Project Management", "Economics", "Petroleum Engineering", "Geology")
    }

    override fun getFacList(): ArrayList<String> {
        return arrayListOf("Select your Faculty","Faculty of Information Technology", "International School of Management",
                "Business School", "Faculty of Oil and Gas", "Center of Math and Cybernetics", "Kazakhstan Maritime Academy")
    }

    override fun getYearList(): ArrayList<String> {
        return arrayListOf("Select your Specialization","Information Systems", "Computer Systems and Software", "Automation and Control", "Management",
                "Finance","Project Management", "Economics", "Petroleum Engineering", "Geology")
    }

}