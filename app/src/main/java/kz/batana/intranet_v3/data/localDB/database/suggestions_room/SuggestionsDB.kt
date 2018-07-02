package kz.batana.intranet_v3.data.localDB.database.suggestions_room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(SuggestionsEntity::class), version = 1)
abstract class SuggestionsDB : RoomDatabase(){
    abstract fun suggestionsDao() : SuggestionsDao
}