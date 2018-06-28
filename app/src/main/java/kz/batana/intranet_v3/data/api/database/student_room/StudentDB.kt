package kz.batana.intranet_v3.data.api.database.student_room

import android.arch.persistence.room.*


@Database(entities = arrayOf(StudentEntity::class), version = 1)
abstract class StudentDB : RoomDatabase(){
    abstract fun studentDao() : StudentDao
}