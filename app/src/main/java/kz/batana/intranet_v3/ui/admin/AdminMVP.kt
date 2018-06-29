package kz.batana.intranet_v3.ui.admin

import kz.batana.intranet_v3.data.localDB.database.admin_room.AdminEntity
import kz.batana.intranet_v3.data.localDB.database.student_room.StudentEntity
import kz.batana.intranet_v3.data.localDB.database.teacher_room.TeacherEntity

interface AdminMVP{
    interface View{
        fun updateList(objects: ArrayList<Any>)

    }

    interface Presenter{
        fun getObjects()
        fun studentsFound(userList: List<StudentEntity>)
        fun teachersFound(userList: List<TeacherEntity>)
        fun adminsFound(userList: List<AdminEntity>)
    }

    interface Interactor {
        fun getStudentsList()
        fun getTeachersList()
        fun getAdminsList()
    }
}