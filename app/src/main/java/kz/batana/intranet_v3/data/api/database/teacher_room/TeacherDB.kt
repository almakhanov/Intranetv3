package kz.batana.intranet_v3.data.api.database.teacher_room

import android.arch.persistence.room.*


@Database(entities = arrayOf(TeacherEntity::class), version = 1)
abstract class TeacherDB : RoomDatabase(){
    abstract fun teacherDao() : TeacherDao
}