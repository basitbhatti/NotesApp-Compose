package com.newproject.notesapp_compose.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NotesDatabase : RoomDatabase(){
    abstract val getDao : NoteDao
}