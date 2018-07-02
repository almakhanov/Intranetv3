package kz.batana.intranet_v3.ui.admin.student_profile

import android.graphics.Color
import android.os.Bundle
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_student_profile.*
import kz.batana.intranet_v3.R
import kz.batana.intranet_v3.SplashActivity.Companion.log
import kz.batana.intranet_v3.data.localDB.database.student_room.StudentEntity



class StudentProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_profile)

        var userObj = intent.getSerializableExtra("user") as StudentEntity
        log("getSerializableExtra -> StudentEntity : $userObj")

        setData(userObj)

        setSupportActionBar(toolbarStudentProfile)
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            this.title = "Student Profile"
            this.setDisplayShowHomeEnabled(true)
            this.setDisplayHomeAsUpEnabled(true)
            this.setHomeAsUpIndicator(R.mipmap.back32)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_student_profile, menu)
        var drawable = toolbarStudentProfile.getOverflowIcon();
        if (drawable != null) {
            drawable = DrawableCompat.wrap(drawable)
            DrawableCompat.setTint(drawable.mutate(), Color.WHITE)
            toolbarStudentProfile.overflowIcon = drawable
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.itemStudentProfileEdit->{
                log("edit")
            }
            R.id.itemStudentProfileDelete->{
                log("delete")
            }
            R.id.itemStudentProfileNewPassword->{
                log("new password")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setData(s : StudentEntity){
        textViewStudentProfileNameInput.text = s.firstname + " "  + s.lastname
        textViewStudentProfileUsernameInput.text = s.username
        textViewStudentProfileDateOfBirthInput.text = s.dateOfBirth
        textViewStudentProfileDateOfRegistrationInput.text = s.dateOfRegistration
        textViewStudentProfileEmailInput.text = s.email
        textViewStudentProfileTelInput.text = s.telNumber
        textViewStudentProfileFacultyInput.text = s.faculty
        textViewStudentProfileSpecializationInput.text = s.specialization
        textViewStudentProfileYearOfStudyInput.text = s.yearOfStudy.toString()
        textViewStudentProfileGenderInput.text = s.gender
    }
}
