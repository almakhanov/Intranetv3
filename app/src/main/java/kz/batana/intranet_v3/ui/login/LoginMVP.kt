package kz.batana.intranet_v3.ui.login

interface LoginMVP {

    interface View{
        fun message(message: String)
        fun openActivity(user: Any)
    }

    interface Presenter{
        fun login(username: String, password: String)
        fun userFound(user: Any)
    }

    interface Interactor {
        fun getUser(username: String)
    }
}