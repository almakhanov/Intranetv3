package kz.batana.intranet_v3.data.localDB.database.suggestions_room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "suggestions")
data class SuggestionsEntity (

    @PrimaryKey
    @ColumnInfo(name = "text")
    var text : String
)