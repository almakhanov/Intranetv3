package kz.batana.intranet_v3.fragments

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_create_student.*
import kz.batana.intranet_v3.R
import kz.batana.intranet_v3.activities.AdminActivity
import kz.batana.intranet_v3.activities.LoginActivity
import kz.batana.intranet_v3.activities.SplashActivity.Companion.asd
import kz.batana.intranet_v3.classes.Student
import kz.batana.intranet_v3.database.StudentEntity
import java.text.SimpleDateFormat
import java.util.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CreateStudentFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: CreateStudenFragmentListener? = null
    private var calendar = Calendar.getInstance()
    private var dateSetListener : DatePickerDialog.OnDateSetListener? = null
    private var spinnerFac : Spinner? = null
    private var spinnerSpec : Spinner? = null
    private var spinnerYearOfStudy : Spinner? = null

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



    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }



    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        //var
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        birthDayBtn()

    }

    override fun onResume() {
        super.onResume()
        studentBirthdayInput.setOnClickListener{
            DatePickerDialog(activity, dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        spinnerFun()


        saveStudentBtn.setOnClickListener{
            if(validated()){
                getTextViews()
                saveStudent()
                clearStudentForm()
            }
        }
    }

    private fun spinnerFun(){
        //Faculty spinner
        spinnerFac = this.studentFacultyInput
        spinnerFac?.adapter = ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, AdminActivity.facultyList)
        spinnerFac?.prompt = "Select your Faculty"
        spinnerFac?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(p2 == 0){
                    //Toast.makeText(activity, "Select other option please!", Toast.LENGTH_SHORT).show()
                }
                Log.d(asd, "FacOption: ${AdminActivity.facultyList[p2]}")
                facultyS = AdminActivity.facultyList[p2]
            }
        }

        //Specialization spinner
        spinnerSpec = this.studentSpecInput
        spinnerSpec?.adapter = ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, AdminActivity.specList)
        spinnerSpec?.prompt = "Select your Specialization"
        spinnerSpec?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(p2 == 0){
                    //Toast.makeText(activity, "Select other option please!", Toast.LENGTH_SHORT).show()
                }
                Log.d(asd, "SpecOption: ${AdminActivity.specList[p2]}")
                specS = AdminActivity.specList[p2]
            }
        }

        //YearOfStudy spinner
        spinnerYearOfStudy = this.studentYearOfStudyInput
        spinnerYearOfStudy?.adapter = ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, AdminActivity.yearOfStudyList)
        spinnerYearOfStudy?.prompt = "Select your Year Of Study"
        spinnerYearOfStudy?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(p2 == 0){
                    //Toast.makeText(activity, "Select other option please!", Toast.LENGTH_SHORT).show()
                }
                Log.d(asd, "YearOfStudyOption: ${AdminActivity.yearOfStudyList[p2]}")
                yearOfStudyS = p2
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
            studentBirthdayInput.text = simpleDateFormat.format(calendar.time)

            Log.d(asd, calendar.time.toString())
            dateOfBirthS = simpleDateFormat.format(calendar.time)

            //DATE OF REGISTRATION BY DATE FORMAT
            var dd = Date()
            var sdf = SimpleDateFormat("yyyy_MM_dd")
            dateOfRegisS = sdf.format(dd)
        }
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CreateStudenFragmentListener) {
            listener = context as AdminActivity
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface CreateStudenFragmentListener {

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                CreateStudentFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
    private fun getTextViews(){
        firstnameS = studentNameInput.text.toString()
        lastnameS = studentLastNameInput.text.toString()
        telS = studentTelInput.text.toString()
        emailS = studentEmailInput.text.toString()
        genderS = if(studentGenderMale.isChecked) "Male" else "Female"
    }

    private fun saveStudent() {
        var s = Student(yearOfStudyS, facultyS, specS, firstnameS, lastnameS, dateOfBirthS,telS,emailS,
                genderS,pasS.hashCode(),dateOfRegisS)

        Log.d(asd, "Student : $s")

        var ss = StudentEntity(s.id, s.username, s.firstname,s.lastname, s.password,
                s.dateOfRegistration, s.dateOfBirth, s.telNumber, s.email,s.gender, s.faculty, s.specialization,
                s.yearOfStudy)


        Single.fromCallable {
            LoginActivity.studentDB?.studentDao()?.insertStudent(ss)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()

        Toast.makeText(activity, "Saved Successfully!", Toast.LENGTH_SHORT).show()
        Log.d(asd, "Student created!")
    }

    private fun clearStudentForm(){
        studentLastNameInput.text = null
        studentNameInput.text = null
        studentBirthdayInput.text = "Date of Birth"
        studentEmailInput.text = null
        studentTelInput.text = null
    }


    private fun validated(): Boolean{
        return !(studentLastNameInput.equals(null) || studentNameInput.equals(null))
    }
}