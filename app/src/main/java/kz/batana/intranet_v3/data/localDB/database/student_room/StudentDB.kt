package kz.batana.intranet_v3.data.localDB.database.student_room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase


@Database(entities = arrayOf(StudentEntity::class), version = 1)
abstract class StudentDB : RoomDatabase(){
    abstract fun studentDao() : StudentDao
}