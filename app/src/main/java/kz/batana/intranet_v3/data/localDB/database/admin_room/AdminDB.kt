package kz.batana.intranet_v3.data.localDB.database.admin_room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase


@Database(entities = arrayOf(AdminEntity::class), version = 1)
abstract class AdminDB : RoomDatabase(){
    abstract fun adminDao() : AdminDao
}