package kz.batana.intranet_v3.ui.admin.student_profile

class StudentProfilePresenter(private val view: StudentProfileMVP.View) : StudentProfileMVP.Presenter {

    private val interactor = StudentProfileInteractor(this)


    override fun check(password1: String, password2: String, type: String, id: String) {
        if(password1.isEmpty() || password2.isEmpty()){
            view.message("Please fill both input lines!")
        }else if(!password1.equals(password2)){
            view.message("Fields do not match")
        }else{
            when(type){
                "student"->{
                    interactor.newPasswordStudent(password1.hashCode(), id)
                    view.message("New Password saved successfully!")
                }
                "teacher"->{

                }
                "admin"->{

                }
            }
        }

    }

}