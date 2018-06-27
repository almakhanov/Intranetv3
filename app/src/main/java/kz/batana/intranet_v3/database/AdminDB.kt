package kz.batana.intranet_v3.database

import android.arch.persistence.room.*
import io.reactivex.Flowable

@Entity(tableName = "admin")
data class AdminEntity(

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
        var gender: String
)

@Dao
interface AdminDao{
    @Query("SELECT * FROM admin")
    fun getAllAdmins() : Flowable<List<AdminEntity>>

    @Query("SELECT * FROM admin WHERE username = :username_")
    fun getAdmin(username_: String) : Flowable<AdminEntity>

    @Insert
    fun insertAdmin(admin : AdminEntity)

    @Query("DELETE FROM admin")
    fun deleteAllAdmins()

}

@Database(entities = arrayOf(AdminEntity::class), version = 1)
abstract class AdminDB : RoomDatabase(){
    abstract fun adminDao() : AdminDao
}