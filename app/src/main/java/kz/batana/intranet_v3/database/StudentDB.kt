package kz.batana.intranet_v3.database

import android.arch.persistence.room.*
import io.reactivex.Flowable


@Entity(tableName = "student")
data class StudentEntity(

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

        @ColumnInfo(name = "faculty")
        var faculty: String,

        @ColumnInfo(name = "specialization")
        var specialization: String,

        @ColumnInfo(name = "yearOfStudy")
        var yearOfStudy: Int
)

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

@Database(entities = arrayOf(StudentEntity::class), version = 1)
abstract class StudentDB : RoomDatabase(){
    abstract fun studentDao() : StudentDao
}