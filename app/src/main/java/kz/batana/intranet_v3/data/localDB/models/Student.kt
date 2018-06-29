package kz.batana.intranet_v3.data.localDB.models

import java.util.*

class Student(yearOfStudy_: Int, faculty_: String, specialization_: String,
              firstname_: String, lastname_: String, dateOfBirth_: String, telNumber_: String, email_: String,
              gender_: String, password_: Int, dateOfRegistration_: String) : User(
        firstname_, lastname_, dateOfBirth_, telNumber_, email_, gender_, password_, dateOfRegistration_) {

    val yearOfStudy: Int
    val faculty: String
    val specialization: String
    val cources: Vector<Course>? = null

    init{
        yearOfStudy = yearOfStudy_
        faculty = faculty_
        specialization = specialization_
    }

}