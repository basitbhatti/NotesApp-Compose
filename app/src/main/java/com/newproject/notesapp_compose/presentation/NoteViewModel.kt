package com.newproject.notesapp_compose.presentation

import androidx.lifecycle.ViewModel
import com.newproject.notesapp_compose.db.NoteDao

class NoteViewModel(
    val dao : NoteDao
) : ViewModel() {


}