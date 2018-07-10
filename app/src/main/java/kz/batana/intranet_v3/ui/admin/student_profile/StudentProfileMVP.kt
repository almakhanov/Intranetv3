package kz.batana.intranet_v3.ui.admin.student_profile

interface StudentProfileMVP {
    interface View{
        fun message(message: String)

    }

    interface Presenter{
        fun check(password1: String, password2: String, type: String, id: String)
    }

    interface Interactor {
        fun newPasswordStudent(hashCode: Int, id: String)
    }
}