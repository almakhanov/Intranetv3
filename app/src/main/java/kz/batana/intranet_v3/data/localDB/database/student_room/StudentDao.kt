package kz.batana.intranet_v3.data.localDB.database.student_room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable


@Dao
interface StudentDao{
    @Query("SELECT * FROM student")
    fun getAllStudents() : Flowable<List<StudentEntity>>

    @Query("SELECT * FROM student WHERE username = :username_")
    fun getStudent(username_: String) : Flowable<StudentEntity>

    @Insert
    fun insertStudent(studentEntity: StudentEntity)

    @Query("DELETE FROM student")
    fun deleteAllStudents()

}