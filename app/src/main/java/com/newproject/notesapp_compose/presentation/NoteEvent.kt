package com.newproject.notesapp_compose.presentation

import com.newproject.notesapp_compose.db.Note

sealed interface NoteEvent {

    object SortNotes : NoteEvent

    data class DeleteNote(val note: Note) : NoteEvent

    data class SaveNote(
        val title: String, val description: String
    )
}