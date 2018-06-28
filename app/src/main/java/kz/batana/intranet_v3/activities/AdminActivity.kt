package kz.batana.intranet_v3.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.PopupMenu
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_admin.*
import kz.batana.intranet_v3.R
import kz.batana.intranet_v3.activities.LoginActivity.Companion.adminList
import kz.batana.intranet_v3.activities.LoginActivity.Companion.studentList
import kz.batana.intranet_v3.activities.LoginActivity.Companion.teacherList
import kz.batana.intranet_v3.activities.SplashActivity.Companion.asd

class AdminActivity : AppCompatActivity() {

    companion object {
        var facultyList = arrayOf("Select your Faculty","Faculty of Information Technology", "International School of Management",
                "Business School", "Faculty of Oil and Gas", "Center of Math and Cybernetics", "Kazakhstan Maritime Academy")
        var yearOfStudyList = arrayOf("Select your year of Study","1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12")
        var specList = arrayOf("Select your Specialization","Information Systems", "Computer Systems and Software", "Automation and Control", "Management",
                "Finance","Project Management", "Economics", "Petroleum Engineering", "Geology" )
        var degreeList = arrayOf("Select Degree", "Bachelor", "Doctor", "Master", "Professor", "Academic" )
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        amountStudent.text = studentList.size.toString()
        amountAdmin.text = adminList.size.toString()
        amountTeacher.text = teacherList.size.toString()

        var msg = intent.extras.getString("user")
        Log.d(asd, "$msg is in AdminActivity")
        Toast.makeText(this, "Hello $msg!", Toast.LENGTH_SHORT).show()


    }

    override fun onResume() {
        super.onResume()


        studentActionMenu.setOnClickListener{
            studentActionMenuFunc()
        }

        teacherActionMenu.setOnClickListener{
            teacherActionMenuFunc()
        }


    }

    private fun teacherActionMenuFunc(){
        var popupMenu = PopupMenu(this, teacherActionMenu)
        popupMenu.menuInflater.inflate(R.menu.admin_card_view_option, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.mainCardViewCreate -> {
                    Log.d(asd, "createStudentFragment")
                    var createIntent = Intent(this, CreateTeacherActivity::class.java)
                    startActivity(createIntent)
                    true
                }
                R.id.mainCardViewShowList -> {
                    Log.d(asd, "studentRecyclerFragment")

                    //callback?.createFragment(studentRecyclerFragment, R.id.firstFragmentContainer)
                    true
                }
                R.id.mainCardViewClearList -> {
                    Log.d(asd, "clearStudentList")
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }


    private fun studentActionMenuFunc(){
        var popupMenu = PopupMenu(this, studentActionMenu)
        popupMenu.menuInflater.inflate(R.menu.admin_card_view_option, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.mainCardViewCreate -> {
                    Log.d(asd, "createStudentFragment")

                    var createIntent = Intent(this, CreateStudentActivity::class.java)
                    startActivity(createIntent)
                    true
                }
                R.id.mainCardViewShowList -> {
                    Log.d(asd, "studentRecyclerFragment")

                    getAllStudentsList()
                    //callback?.createFragment(studentRecyclerFragment, R.id.firstFragmentContainer)
                    true
                }
                R.id.mainCardViewClearList -> {
                    Log.d(asd, "clearStudentList")
                    clearStudentList()
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }


    private fun clearStudentList() {
        LoginActivity.studentDB?.studentDao()?.deleteAllStudents()
        getAllStudentsList()
        Log.d(asd, "Deleted list size : ${studentList.size}")
    }

    private fun getAllStudentsList(){
        LoginActivity.studentDB?.studentDao()?.getAllStudents()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { listOfStudents ->
                    Log.d(asd, "listOfStudents: ${listOfStudents.size}")
                    studentList = listOfStudents
                    Log.d(asd, "listOfStudents: $studentList")
                }
    }
}
