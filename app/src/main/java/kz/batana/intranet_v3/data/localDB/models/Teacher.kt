package kz.batana.intranet_v3.data.localDB.models


class Teacher(degree_: String, firstname_: String, lastname_: String, dateOfBirth_: String, telNumber_: String, email_: String,
              gender_: String, password_: Int, dateOfRegistration_: String) : User(
        firstname_, lastname_, dateOfBirth_, telNumber_, email_, gender_, password_, dateOfRegistration_) {

    val degree: String

    init{
        degree = degree_
    }
//    private val courses: Vector<Course>? = null
//    private val salaryPerCourse: HashMap<Course, Int>? = null
//    private val files: HashMap<Course, Vector<File>>? = null
//    private val students: HashMap<Course, TreeSet<Student>>? = null


}