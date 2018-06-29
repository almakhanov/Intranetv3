package kz.batana.intranet_v3.ui.admin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_admin_new.*
import kz.batana.intranet_v3.R
import kz.batana.intranet_v3.SplashActivity.Companion.asd
import kz.batana.intranet_v3.data.localDB.database.admin_room.AdminEntity
import kz.batana.intranet_v3.data.localDB.database.student_room.StudentEntity
import kz.batana.intranet_v3.data.localDB.database.teacher_room.TeacherEntity

class AdminNewActivity : AppCompatActivity(), AllUsersAdapter.OnItemClickListener, AdminMVP.View {

    private val presenter : AdminPresenter by lazy{ AdminPresenter(this) }
    private var arrayList = ArrayList<Any>()


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

        presenter.getObjects()
    }


    override fun updateList(objects: ArrayList<Any>) {

        for (it in objects) arrayList.add(it)

        var layout = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        RecView?.layoutManager = layout
        var adapter = AllUsersAdapter(arrayList, this)
        RecView?.adapter = adapter
        adapter.notifyDataSetChanged()

        Log.d(asd, "arr list size = ${arrayList.size}")
    }

}
