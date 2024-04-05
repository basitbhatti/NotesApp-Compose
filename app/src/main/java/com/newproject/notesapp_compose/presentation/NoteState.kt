package com.newproject.notesapp_compose.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.newproject.notesapp_compose.db.Note

class NoteState {
    val notes : List<Note> = emptyList()
    val title : MutableState<String> = mutableStateOf("")
    val description : MutableState<String> = mutableStateOf("")
}