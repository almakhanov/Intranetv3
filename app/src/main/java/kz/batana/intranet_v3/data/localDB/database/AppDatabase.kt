package kz.batana.intranet_v3.data.localDB.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import kz.batana.intranet_v3.data.localDB.database.admin_room.AdminDao
import kz.batana.intranet_v3.data.localDB.database.admin_room.AdminEntity
import kz.batana.intranet_v3.data.localDB.database.student_room.StudentDao
import kz.batana.intranet_v3.data.localDB.database.student_room.StudentEntity
import kz.batana.intranet_v3.data.localDB.database.suggestions_room.SuggestionsDao
import kz.batana.intranet_v3.data.localDB.database.suggestions_room.SuggestionsEntity
import kz.batana.intranet_v3.data.localDB.database.teacher_room.TeacherDao
import kz.batana.intranet_v3.data.localDB.database.teacher_room.TeacherEntity


@Database(entities = arrayOf(AdminEntity::class, StudentEntity::class, TeacherEntity::class, SuggestionsEntity::class), version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun adminDao() : AdminDao
    abstract fun suggestionsDao() : SuggestionsDao
    abstract fun teacherDao() : TeacherDao
    abstract fun studentDao() : StudentDao

}
