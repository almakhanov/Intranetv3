package kz.batana.intranet_v3.ui.admin

import android.widget.Button
import kz.batana.intranet_v3.data.localDB.database.admin_room.AdminEntity
import kz.batana.intranet_v3.data.localDB.database.student_room.StudentEntity
import kz.batana.intranet_v3.data.localDB.database.teacher_room.TeacherEntity

class AdminPresenter(private val view : AdminMVP.View) : AdminMVP.Presenter {

    private val interactor = AdminInteractor(this, view as AdminNewActivity)

    companion object {
        const val STUDENT = "student"
        const val TEACHER = "teacher"
        const val ADMIN = "admin"
    }

    override fun studentsFound(userList: List<StudentEntity>) {
        var list : ArrayList<Any> = ArrayList()
        list.add("Students")

        var btn = Button(view as AdminNewActivity)
        btn.hint = STUDENT

        for(it in userList) list.add(it)
        list.add(btn)

        view.updateList(list)
    }

    override fun teachersFound(userList: List<TeacherEntity>) {
        var list : ArrayList<Any> = ArrayList()
        list.add("Teachers")

        var btn = Button(view as AdminNewActivity)
        btn.hint = TEACHER

        for (it in userList) list.add(it)

        list.add(btn)

        view.updateList(list)
    }

    override fun adminsFound(userList: List<AdminEntity>) {
        var list : ArrayList<Any> = ArrayList()
        list.add("Admins")

        var btn = Button(view as AdminNewActivity)
        btn.hint = ADMIN

        for(it in userList) list.add(it)

        list.add(btn)

        view.updateList(list)
    }


    override fun getObjects() {
        interactor.getAdminsList()
        interactor.getStudentsList()
        interactor.getTeachersList()
    }


}