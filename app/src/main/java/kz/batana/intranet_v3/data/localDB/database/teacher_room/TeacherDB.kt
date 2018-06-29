package kz.batana.intranet_v3.data.localDB.database.teacher_room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase


@Database(entities = arrayOf(TeacherEntity::class), version = 1)
abstract class TeacherDB : RoomDatabase(){
    abstract fun teacherDao() : TeacherDao
}