package kz.batana.intranet_v3.ui.admin

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_admin_new.*
import kz.batana.intranet_v3.R
import kz.batana.intranet_v3.SplashActivity.Companion.TAG
import kz.batana.intranet_v3.SplashActivity.Companion.log
import kz.batana.intranet_v3.SplashActivity.Companion.password
import kz.batana.intranet_v3.SplashActivity.Companion.username
import kz.batana.intranet_v3.data.localDB.SharedPreference.LoginPreference.Companion.putLoginPref
import kz.batana.intranet_v3.data.localDB.database.admin_room.AdminEntity
import kz.batana.intranet_v3.data.localDB.database.student_room.StudentEntity
import kz.batana.intranet_v3.data.localDB.database.teacher_room.TeacherEntity
import kz.batana.intranet_v3.ui.admin.AdminPresenter.Companion.ADMIN
import kz.batana.intranet_v3.ui.admin.AdminPresenter.Companion.STUDENT
import kz.batana.intranet_v3.ui.admin.AdminPresenter.Companion.TEACHER
import kz.batana.intranet_v3.ui.admin.student_create.CreateStudentActivity
import kz.batana.intranet_v3.ui.admin.student_profile.StudentProfileActivity
import kz.batana.intranet_v3.ui.admin.teacher_create.CreateTeacherActivity
import kz.batana.intranet_v3.ui.login.LoginActivity

class AdminNewActivity : AppCompatActivity(), AllUsersAdapter.OnItemClickListener, AdminMVP.View {


    private val presenter : AdminPresenter by lazy{ AdminPresenter(this) }
    private lateinit var arrayList : ArrayList<Any>


    override fun onHeaderClicked(h: String) {
        Log.d(TAG, h)
    }

    override fun onStudentClicked(s: StudentEntity) {
        log(s.toString())
        startActivity(Intent(this, StudentProfileActivity::class.java)
                .putExtra("user", s))
    }

    override fun onTeacherClicked(t: TeacherEntity) {
        Log.d(TAG, t.toString())
    }

    override fun onAdminClicked(a: AdminEntity) {
        Log.d(TAG, a.toString())
    }

    override fun onAddBtnClicked(b: String) {
        when(b){
            STUDENT -> {
                startActivity(Intent(this, CreateStudentActivity::class.java))
            }
            TEACHER -> {
                startActivity(Intent(this, CreateTeacherActivity::class.java))
            }
            ADMIN -> {
                msg("No admin create activity yet!")
            }
        }
    }

    override fun msg(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_new)

        var userObj = intent.getSerializableExtra("user") as AdminEntity
        log("getSerializableExtra : $userObj")

        navigationViewAdmin.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.logout_admin->{
                    startActivity(Intent(this, LoginActivity::class.java))
                    putLoginPref("no_one", "no_password")
                    username = "no_username"
                    password = "no_password"
                    finish()
                }
            }
            drawerLayoutAdmin.closeDrawers()
            true
        }

        setSupportActionBar(toolbarAdmin)
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            this.setDisplayHomeAsUpEnabled(true)
            this.setHomeAsUpIndicator(R.mipmap.nav32)
            this.title = "Admin page"
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawerLayoutAdmin.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



    override fun onResume() {
        super.onResume()
        arrayList = ArrayList()
        presenter.getObjects()
    }


    override fun updateList(objects: ArrayList<Any>) {

        for (it in objects) arrayList.add(it)

        var layout = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        RecView?.layoutManager = layout
        var adapter = AllUsersAdapter(arrayList, this)
        RecView?.adapter = adapter
        adapter.notifyDataSetChanged()

        Log.d(TAG, "arr list size = ${arrayList.size}")
    }

}
