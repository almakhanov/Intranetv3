package kz.batana.intranet_v3.database

import android.arch.persistence.room.*
import io.reactivex.Flowable

@Entity(tableName = "teacher")
data class TeacherEntity(

        @PrimaryKey
        @ColumnInfo(name = "id")
        var id : String,

        @ColumnInfo(name = "username")
        var username : String,

        @ColumnInfo(name = "firstname")
        var firstname: String,

        @ColumnInfo(name = "lastname")
        var lastname: String,

        @ColumnInfo(name = "password")
        var password: Int,

        @ColumnInfo(name = "dateOfRegistration")
        var dateOfRegistration: String,

        @ColumnInfo(name = "dateOfBirth")
        var dateOfBirth: String,

        @ColumnInfo(name = "telNumber")
        var telNumber: String,

        @ColumnInfo(name = "email")
        var email: String?,

        @ColumnInfo(name = "gender")
        var gender: String,

        @ColumnInfo(name = "degree")
        var degree: String


)

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

@Database(entities = arrayOf(TeacherEntity::class), version = 1)
abstract class TeacherDB : RoomDatabase(){
    abstract fun teacherDao() : TeacherDao
}