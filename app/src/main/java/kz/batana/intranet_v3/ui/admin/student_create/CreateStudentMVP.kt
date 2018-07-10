package kz.batana.intranet_v3.ui.admin.student_create

import kz.batana.intranet_v3.data.localDB.database.student_room.StudentEntity

interface CreateStudentMVP {
    interface View{
        fun message(message: String)

    }

    interface Presenter{
        fun validated(firstname: String, lastname: String, tel: String, email: String, dateOfBirth: String): Boolean
        fun saveStudent(yearOfStudyS: Int, facultyS: String, specS: String, firstnameS: String, lastnameS: String,
                        dateOfBirthS: String, telS: String, emailS: String, genderS: String, password: Int, dateOfRegisS: String)

        fun getSpecList(): ArrayList<String>
        fun getFacList(): ArrayList<String>
        fun getYearList(): ArrayList<String>
    }

    interface Interactor {
        fun saveStudentEntity(ss: StudentEntity)
    }
}