package kz.batana.intranet_v3.data.localDB.database.admin_room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable


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

