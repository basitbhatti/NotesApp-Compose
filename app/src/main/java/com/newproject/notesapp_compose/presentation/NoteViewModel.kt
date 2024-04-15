package com.newproject.notesapp_compose.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newproject.notesapp_compose.db.Note
import com.newproject.notesapp_compose.db.NoteDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteViewModel(
    val dao: NoteDao
) : ViewModel() {

    private var isSortedByDateAdded = MutableStateFlow(true)

    private var notes = isSortedByDateAdded.flatMapLatest {
        if (it) {
            dao.GetNotesByDate()
        } else {
            dao.GetNotesByTitle()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())


    var _state = MutableStateFlow(NoteState())
    var state = combine(_state, isSortedByDateAdded, notes) { state, isSortedByDateAdded, notes ->
        state.copy(
            notes = notes
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteState())

    fun onEvent(event: NoteEvent) {
        when (event) {
            is NoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    dao.deleteNote(event.note)
                }
            }

            is NoteEvent.SaveNote -> {
                val note = Note(
                    title = event.title,
                    description = event.description,
                    dateAdded = System.currentTimeMillis()
                )
                viewModelScope.launch {
                    dao.upsertNote(note)
                }
                _state.update {
                    it.copy(
                        title = mutableStateOf(""), description = mutableStateOf("")
                    )
                }
            }

            NoteEvent.SortNotes -> {

            }
        }
    }

}