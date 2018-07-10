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
import kotlinx.android.synthetic.main.activity_create_teacher.*
import kz.batana.intranet_v3.R
import kz.batana.intranet_v3.SplashActivity
import java.text.SimpleDateFormat
import java.util.*

class CreateTeacherActivity : AppCompatActivity(), CreateTeacherMVP.View {

    private val presenter : CreateTeacherPresenter by lazy{ CreateTeacherPresenter(this) }

    private var calendar = Calendar.getInstance()
    private var dateSetListener : DatePickerDialog.OnDateSetListener? = null

    private var spinnerSpec : Spinner? = null

    private lateinit var degreeList : ArrayList<String>

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

        degreeList = presenter.getDegreeList()
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
            if(presenter.validated(teacherNameInput.text.toString(), teacherLastNameInput.text.toString(),
                            teacherTelInput.text.toString(), teacherEmailInput.text.toString(), teacherBirthdayInput.text.toString())){
                getTextViews()
                presenter.saveTeacher(degreeS, firstnameS, lastnameS, dateOfBirthS,telS,emailS,
                        genderS,pasS.hashCode(),dateOfRegisS)
                clearTeacherForm()
            }
        }
    }


    private fun spinnerFun(){


        //Specialization spinner
        spinnerSpec = this.teacherDegreeInput
        spinnerSpec?.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, degreeList)
        spinnerSpec?.prompt = "Select Degree"
        spinnerSpec?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(p2 == 0){
                    //Toast.makeText(activity, "Select other option please!", Toast.LENGTH_SHORT).show()
                }
                Log.d(SplashActivity.TAG, "SpecOption: ${degreeList[p2]}")
                degreeS = degreeList[p2]
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

            Log.d(SplashActivity.TAG, calendar.time.toString())
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

    private fun clearTeacherForm(){
        teacherLastNameInput.text = null
        teacherNameInput.text = null
        teacherBirthdayInput.text = "Date of Birth"
        teacherEmailInput.text = null
        teacherTelInput.text = null
    }


    override fun message(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }



}
