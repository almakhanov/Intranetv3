package kz.batana.intranet_v3.ui.admin

import android.widget.Button
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.batana.intranet_v3.data.localDB.database.suggestions_room.SuggestionsEntity

class AdminPresenter(private val view : AdminMVP.View) : AdminMVP.Presenter {

    private val interactor = AdminInteractor(this)

    companion object {
        const val STUDENT = "student"
        const val TEACHER = "teacher"
        const val ADMIN = "admin"
    }


    override fun getObjects() {
        interactor.getStudentsList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe{ listOfAllStudents ->
                    var list : ArrayList<Any> = ArrayList()
                    list.add("Students")

                    list.addAll(listOfAllStudents)

                    var btn = Button(view as AdminNewActivity)
                    btn.hint = STUDENT
                    list.add(btn)

                    view.updateList(list)
                }

        interactor.getTeachersList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe { listOfAllTeachers ->
                    var list : ArrayList<Any> = ArrayList()
                    list.add("Teachers")

                    list.addAll(listOfAllTeachers)

                    var btn = Button(view as AdminNewActivity)
                    btn.hint = TEACHER
                    list.add(btn)

                    view.updateList(list)
                }

        interactor.getAdminsList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe{ listOfAllAdmins ->
                    var list : ArrayList<Any> = ArrayList()
                    list.add("Admins")

                    list.addAll(listOfAllAdmins)

                    var btn = Button(view as AdminNewActivity)
                    btn.hint = ADMIN
                    list.add(btn)

                    view.updateList(list)
                }


    }

    override fun saveSuggestion(query: String) {
        var sg = SuggestionsEntity(query)
        interactor.saveSuggestion(sg)
    }

    override fun getSuggestions() {
        interactor.getSuggestions()
    }

    override fun suggestionsFound(sgs: List<SuggestionsEntity>) {
        var arrList = ArrayList<String>()
        sgs.forEach{
            arrList.add(it.text)
        }
        view.reloadSuggestions(arrList)
    }


}