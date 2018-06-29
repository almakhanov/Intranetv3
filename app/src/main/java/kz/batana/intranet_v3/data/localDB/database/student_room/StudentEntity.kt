package kz.batana.intranet_v3.data.localDB.database.student_room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

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
) : Serializable