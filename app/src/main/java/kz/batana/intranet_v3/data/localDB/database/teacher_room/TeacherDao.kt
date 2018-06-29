package kz.batana.intranet_v3.data.localDB.database.teacher_room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface TeacherDao{
    @Query("SELECT * FROM teacher")
    fun getAllTeacher() : Flowable<List<TeacherEntity>>

    @Query("SELECT * FROM teacher WHERE username = :username_")
    fun getTeacher(username_: String) : Flowable<TeacherEntity>

    @Insert
    fun insertTeacher(teacherEntity: TeacherEntity)

    @Query("DELETE FROM teacher")
    fun deleteAllTeacher()

}