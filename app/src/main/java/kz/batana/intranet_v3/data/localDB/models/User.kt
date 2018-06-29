package kz.batana.intranet_v3.data.localDB.models

abstract class User(firstname_: String, lastname_: String, dateOfBirth_: String, telNumber_: String, email_: String,
                    gender_: String, password_: Int, dateOfRegistration_: String) {
    var id: String
    var username: String
    var firstname: String
    var lastname: String
    var password: Int
    var dateOfRegistration: String
    var dateOfBirth: String
    var telNumber: String
    var email: String?
    var gender: String

    init{
        firstname = firstname_
        lastname = lastname_
        username = "${firstname[0].toLowerCase()}_${lastname.toLowerCase()}"
        password = password_
        telNumber = telNumber_
        email = email_
        dateOfBirth = dateOfBirth_
        dateOfRegistration = dateOfRegistration_
        @Suppress("DEPRECATION")
        id = "${dateOfRegistration}_$username"
        gender = gender_
    }

}