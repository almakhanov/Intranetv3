package kz.batana.intranet_v3.ui.admin

import kz.batana.intranet_v3.data.localDB.database.admin_room.AdminEntity
import kz.batana.intranet_v3.data.localDB.database.student_room.StudentEntity
import kz.batana.intranet_v3.data.localDB.database.teacher_room.TeacherEntity

class AdminPresenter(private val view : AdminMVP.View) : AdminMVP.Presenter {

    private val interactor = AdminInteractor(this, view as AdminNewActivity)


    override fun studentsFound(userList: List<StudentEntity>) {
        var list : ArrayList<Any> = ArrayList()
        list.add("Students")
        for(it in userList) list.add(it)

        view.updateList(list)
    }

    override fun teachersFound(userList: List<TeacherEntity>) {
        var list : ArrayList<Any> = ArrayList()
        list.add("Teachers")
        for (it in userList) list.add(it)
        view.updateList(list)
    }

    override fun adminsFound(userList: List<AdminEntity>) {
        var list : ArrayList<Any> = ArrayList()
        list.add("Admins")
        for(it in userList) list.add(it)
        view.updateList(list)
    }


    override fun getObjects() {
        interactor.getAdminsList()
        interactor.getStudentsList()
        interactor.getTeachersList()
    }


}