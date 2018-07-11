package kz.batana.intranet_v3.ui.admin

import io.reactivex.Flowable
import kz.batana.intranet_v3.data.localDB.database.admin_room.AdminEntity
import kz.batana.intranet_v3.data.localDB.database.student_room.StudentEntity
import kz.batana.intranet_v3.data.localDB.database.suggestions_room.SuggestionsEntity
import kz.batana.intranet_v3.data.localDB.database.teacher_room.TeacherEntity

interface AdminMVP{
    interface View{
        fun updateList(objects: ArrayList<Any>)
        fun message(message: String)
        fun reloadSuggestions(data:ArrayList<String>)
    }

    interface Presenter{
        fun getObjects()
        fun saveSuggestion(query: String)
        fun getSuggestions()
        fun suggestionsFound(sgs: List<SuggestionsEntity>)
    }

    interface Interactor {
        fun getStudentsList(): Flowable<List<StudentEntity>>
        fun getTeachersList(): Flowable<List<TeacherEntity>>
        fun getAdminsList(): Flowable<List<AdminEntity>>
        fun saveSuggestion(query: SuggestionsEntity)
        fun getSuggestions()
    }
}