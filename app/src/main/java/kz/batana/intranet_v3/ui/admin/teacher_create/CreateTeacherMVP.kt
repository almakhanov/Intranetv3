package kz.batana.intranet_v3.ui.admin.teacher_create

import kz.batana.intranet_v3.data.localDB.database.teacher_room.TeacherEntity

interface CreateTeacherMVP {

    interface View{
        fun message(message: String)
    }

    interface Presenter{
        fun validated(firstname: String, lastname: String, tel: String, email: String, dateOfBirth: String): Boolean
        fun saveTeacher( degreeS: String, firstnameS: String, lastnameS: String,
                        dateOfBirthS: String, telS: String, emailS: String, genderS: String, password: Int, dateOfRegisS: String)

        fun getDegreeList(): ArrayList<String>
    }

    interface Interactor {
        fun saveTeacherEntity(teacherEntity: TeacherEntity)
    }
}