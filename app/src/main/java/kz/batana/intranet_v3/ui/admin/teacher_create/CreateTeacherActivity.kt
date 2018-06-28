package kz.batana.intranet_v3.ui.admin.teacher_create

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_create_teacher.*
import kz.batana.intranet_v3.R
import kz.batana.intranet_v3.SplashActivity
import kz.batana.intranet_v3.data.api.database.teacher_room.TeacherEntity
import kz.batana.intranet_v3.data.api.models.Teacher
import kz.batana.intranet_v3.ui.admin.AdminActivity
import kz.batana.intranet_v3.ui.login.LoginActivity
import java.text.SimpleDateFormat
import java.util.*

class CreateTeacherActivity : AppCompatActivity() {

    private var calendar = Calendar.getInstance()
    private var dateSetListener : DatePickerDialog.OnDateSetListener? = null
    private var spinnerFac : Spinner? = null
    private var spinnerSpec : Spinner? = null
    private var spinnerYearOfStudy : Spinner? = null

    //Teacher
    private var dateOfBirthS : String = ""
    private var genderS : String = ""
    private var dateOfRegisS : String = ""
    private var firstnameS : String = ""
    private var lastnameS : String = ""
    private var degreeS : String = ""
    private var telS : String = ""
    private var emailS : String = ""
    private var pasS : String = "12345"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_teacher)
    }

    override fun onResume() {
        super.onResume()
        birthDayBtn()
        teacherBirthdayInput.setOnClickListener{
            DatePickerDialog(this, dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        spinnerFun()


        saveStudentBtn.setOnClickListener{
            if(validated()){
                getTextViews()
                teacherStudent()
                clearTeacherForm()
            }
        }
    }


    private fun spinnerFun(){


        //Specialization spinner
        spinnerSpec = this.teacherDegreeInput
        spinnerSpec?.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, AdminActivity.degreeList)
        spinnerSpec?.prompt = "Select Degree"
        spinnerSpec?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(p2 == 0){
                    //Toast.makeText(activity, "Select other option please!", Toast.LENGTH_SHORT).show()
                }
                Log.d(SplashActivity.asd, "SpecOption: ${AdminActivity.degreeList[p2]}")
                degreeS = AdminActivity.degreeList[p2]
            }
        }



    }

    private fun birthDayBtn(){
        dateSetListener = DatePickerDialog.OnDateSetListener{ view, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val birthFormat = "yyyy_MM_dd"
            val simpleDateFormat = SimpleDateFormat(birthFormat, Locale.US)
            teacherBirthdayInput.text = simpleDateFormat.format(calendar.time)

            Log.d(SplashActivity.asd, calendar.time.toString())
            dateOfBirthS = simpleDateFormat.format(calendar.time)

            //DATE OF REGISTRATION BY DATE FORMAT
            var dd = Date()
            var sdf = SimpleDateFormat("yyyy_MM_dd")
            dateOfRegisS = sdf.format(dd)
        }
    }

    private fun getTextViews(){
        firstnameS = teacherNameInput.text.toString()
        lastnameS = teacherLastNameInput.text.toString()
        telS = teacherTelInput.text.toString()
        emailS = teacherEmailInput.text.toString()
        genderS = if(teacherGenderMale.isChecked) "Male" else "Female"
    }

    private fun teacherStudent() {
        var s = Teacher(degreeS, firstnameS, lastnameS, dateOfBirthS,telS,emailS,
                genderS,pasS.hashCode(),dateOfRegisS)

        Log.d(SplashActivity.asd, "Student : $s")

        var ss = TeacherEntity(s.id, s.username, s.firstname, s.lastname, s.password,
                s.dateOfRegistration, s.dateOfBirth, s.telNumber, s.email, s.gender, s.degree)


        Single.fromCallable {
            LoginActivity.teacherDB?.teacherDao()?.insertTeacher(ss)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()

        Toast.makeText(this, "Saved Successfully!", Toast.LENGTH_SHORT).show()
        Log.d(SplashActivity.asd, "Teacher created! $ss")
    }

    private fun clearTeacherForm(){
        teacherLastNameInput.text = null
        teacherNameInput.text = null
        teacherBirthdayInput.text = "Date of Birth"
        teacherEmailInput.text = null
        teacherTelInput.text = null
    }


    private fun validated(): Boolean{
        return !(teacherLastNameInput.equals(null) || teacherNameInput.equals(null))
    }



}
