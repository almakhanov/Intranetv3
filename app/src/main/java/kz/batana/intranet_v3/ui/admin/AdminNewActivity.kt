package kz.batana.intranet_v3.ui.admin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_admin_new.*
import kz.batana.intranet_v3.R
import kz.batana.intranet_v3.SplashActivity.Companion.asd
import kz.batana.intranet_v3.data.api.database.admin_room.AdminEntity
import kz.batana.intranet_v3.data.api.database.student_room.StudentEntity
import kz.batana.intranet_v3.data.api.database.teacher_room.TeacherEntity
import kz.batana.intranet_v3.ui.login.LoginActivity.Companion.adminList
import kz.batana.intranet_v3.ui.login.LoginActivity.Companion.studentList
import kz.batana.intranet_v3.ui.login.LoginActivity.Companion.teacherList

class AdminNewActivity : AppCompatActivity(), AllUsersAdapter.OnItemClickListener {

    private lateinit var arrayList : ArrayList<Any>

    override fun onHeaderClicked(h: String) {
        Log.d(asd, h)
    }

    override fun onStudentClicked(s: StudentEntity) {
        Log.d(asd, s.toString())
    }

    override fun onTeacherClicked(t: TeacherEntity) {
        Log.d(asd, t.toString())
    }

    override fun onAdminClicked(a: AdminEntity) {
        Log.d(asd, a.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_new)

        recyclerShow()
    }

    private fun recyclerShow(){
        arrayList = ArrayList()
        arrayList.add("Students")
        for(it in studentList){
            arrayList.add(it)
        }

        arrayList.add("Teachers")
        for(it in teacherList){
            arrayList.add(it)
        }
        arrayList.add("Admins")
        for(it in adminList){
            arrayList.add(it)
        }

        var layout = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        RecView?.layoutManager = layout
        var adapter = AllUsersAdapter(arrayList, this)
        RecView?.adapter = adapter
        adapter.notifyDataSetChanged()


        Log.d(asd, "arr list size = ${arrayList.size}")

    }

}
