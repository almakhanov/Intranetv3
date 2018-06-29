package kz.batana.intranet_v3.data.localDB.models

class Admin(firstname_: String, lastname_: String, dateOfBirth_: String, telNumber_: String, email_: String,
            gender_: String, password_: Int, dateOfRegistration_: String) : User(
        firstname_, lastname_, dateOfBirth_, telNumber_, email_, gender_, password_, dateOfRegistration_ ) {
}