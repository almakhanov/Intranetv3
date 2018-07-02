package kz.batana.intranet_v3.data.localDB.database.suggestions_room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface SuggestionsDao {
    @Query("SELECT * FROM suggestions")
    fun getAllSuggestions() : Flowable<List<SuggestionsEntity>>

    @Insert
    fun insertSuggestion(suggestionsEntity: SuggestionsEntity)

    @Query("DELETE FROM suggestions")
    fun deleteAllSuggestions()
}