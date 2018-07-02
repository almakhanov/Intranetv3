package kz.batana.intranet_v3.ui.admin

import kz.batana.intranet_v3.data.localDB.database.admin_room.AdminEntity
import kz.batana.intranet_v3.data.localDB.database.student_room.StudentEntity
import kz.batana.intranet_v3.data.localDB.database.teacher_room.TeacherEntity

class UsersQueryFilter(var items: ArrayList<Any>) {

    fun findByFilter(query: String) : ArrayList<Any>{
        var arrList = ArrayList<Any>()
        items.forEach{
            when(it){
                is StudentEntity->{
                    if(it.firstname.toLowerCase().contains(query.toLowerCase())
                    || it.lastname.toLowerCase().contains(query.toLowerCase())){
                        arrList.add(it)
                    }
                }
                is AdminEntity->{
                    if(it.firstname.toLowerCase().contains(query.toLowerCase())
                            || it.lastname.toLowerCase().contains(query.toLowerCase())){
                        arrList.add(it)
                    }
                }
                is TeacherEntity->{
                    if(it.firstname.toLowerCase().contains(query.toLowerCase())
                            || it.lastname.toLowerCase().contains(query.toLowerCase())){
                        arrList.add(it)
                    }
                }
                else -> {
                    arrList.add(it)
                }
            }
        }
        return arrList
    }
}