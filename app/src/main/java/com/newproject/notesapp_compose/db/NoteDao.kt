package com.newproject.notesapp_compose.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Upsert
    suspend fun upsertNote(note: Note)


    @Delete
    suspend fun deleteNote(note:Note)

    @Query("SELECT * FROM notes ORDER BY title ASC")
    fun GetNotesByTitle() : Flow<List<Note>>
    @Query("SELECT * FROM notes ORDER BY dateAdded ASC")
    fun GetNotesByDate() : Flow<List<Note>>


}