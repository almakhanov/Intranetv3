package kz.batana.intranet_v3.ui.admin.student_create

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_student.*
import kz.batana.intranet_v3.R
import kz.batana.intranet_v3.SplashActivity
import java.text.SimpleDateFormat
import java.util.*

class CreateStudentActivity : AppCompatActivity(), CreateStudentMVP.View {

    private val presenter : CreateStudentPresenter by lazy{ CreateStudentPresenter(this) }

    private var calendar = Calendar.getInstance()
    private var dateSetListener : DatePickerDialog.OnDateSetListener? = null
    private var spinnerFac : Spinner? = null
    private var spinnerSpec : Spinner? = null
    private var spinnerYearOfStudy : Spinner? = null

    private lateinit var specList: ArrayList<String>
    private lateinit var facList: ArrayList<String>
    private lateinit var yearList: ArrayList<String>

    //Student
    private var yearOfStudyS : Int = 0
    private var dateOfBirthS : String = ""
    private var genderS : String = ""
    private var dateOfRegisS : String = ""
    private var firstnameS : String = ""
    private var lastnameS : String = ""
    private var facultyS : String = ""
    private var specS : String = ""
    private var telS : String = ""
    private var emailS : String = ""
    private var pasS : String = "12345"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_student)

        specList = presenter.getSpecList()
        facList = presenter.getFacList()
        yearList = presenter.getYearList()
    }

    override fun onResume() {
        super.onResume()

        birthDayBtn()

        studentBirthdayInput.setOnClickListener{
            DatePickerDialog(this, dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        spinnerFun()


        saveStudentBtn.setOnClickListener{
            if(presenter.validated(studentLastNameInput.text.toString(), studentNameInput.text.toString(),
                            studentTelInput.text.toString(), studentEmailInput.text.toString(), studentBirthdayInput.text.toString())){
                getTextViews()
                presenter.saveStudent(yearOfStudyS, facultyS, specS, firstnameS, lastnameS, dateOfBirthS,telS,emailS,
                        genderS,pasS.hashCode(),dateOfRegisS)
                clearStudentForm()
            }
        }
    }


    private fun spinnerFun(){
        //Faculty spinner
        spinnerFac = this.studentFacultyInput
        spinnerFac?.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, facList)
        spinnerFac?.prompt = "Select your Faculty"
        spinnerFac?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(p2 == 0){
                    //Toast.makeText(activity, "Select other option please!", Toast.LENGTH_SHORT).show()
                }
                Log.d(SplashActivity.TAG, "FacOption: ${facList[p2]}")
                facultyS = facList[p2]
            }
        }

        //Specialization spinner
        spinnerSpec = this.studentSpecInput
        spinnerSpec?.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, specList)
        spinnerSpec?.prompt = "Select your Specialization"
        spinnerSpec?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(p2 == 0){
                    //Toast.makeText(activity, "Select other option please!", Toast.LENGTH_SHORT).show()
                }
                Log.d(SplashActivity.TAG, "SpecOption: ${specList[p2]}")
                specS = specList[p2]
            }
        }

        //YearOfStudy spinner
        spinnerYearOfStudy = this.studentYearOfStudyInput
        spinnerYearOfStudy?.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, yearList)
        spinnerYearOfStudy?.prompt = "Select your Year Of Study"
        spinnerYearOfStudy?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(p2 == 0){
                    //Toast.makeText(activity, "Select other option please!", Toast.LENGTH_SHORT).show()
                }
                Log.d(SplashActivity.TAG, "YearOfStudyOption: ${yearList[p2]}")
                yearOfStudyS = p2
            }
        }

    }

    private fun birthDayBtn(){
        dateSetListener = DatePickerDialog.OnDateSetListener{ view, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val birthFormat = "yyyy-MM-dd"
            val simpleDateFormat = SimpleDateFormat(birthFormat, Locale.US)
            studentBirthdayInput.text = simpleDateFormat.format(calendar.time)

            Log.d(SplashActivity.TAG, calendar.time.toString())
            dateOfBirthS = simpleDateFormat.format(calendar.time)

            //DATE OF REGISTRATION BY DATE FORMAT
            var dd = Date()
            var sdf = SimpleDateFormat("yyyy-MM-dd")
            dateOfRegisS = sdf.format(dd)
        }
    }

    private fun getTextViews(){
        firstnameS = studentNameInput.text.toString()
        lastnameS = studentLastNameInput.text.toString()
        telS = studentTelInput.text.toString()
        emailS = studentEmailInput.text.toString()
        genderS = if(studentGenderMale.isChecked) "Male" else "Female"
    }


    private fun clearStudentForm(){
        studentLastNameInput.text = null
        studentNameInput.text = null
        studentBirthdayInput.text = "Date of Birth"
        studentEmailInput.text = null
        studentTelInput.text = null
    }

    override fun message(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
