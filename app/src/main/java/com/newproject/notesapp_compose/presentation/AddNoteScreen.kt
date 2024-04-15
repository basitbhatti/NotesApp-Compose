package com.newproject.notesapp_compose.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun AddNoteScreen(
    modifier: Modifier = Modifier,
    state: NoteState,
    navController: NavHostController,
    onEvent: (NoteEvent) -> Unit
) {

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            onEvent(
                NoteEvent.SaveNote(
                    title = state.title.value, description = state.description.value
                )
            )
            navController.popBackStack()
        }) {
            Icon(imageVector = Icons.Rounded.Check, contentDescription = "Check Icon")
        }
    }) {

        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
        ) {

            OutlinedTextField(value = state.title.value,
                onValueChange = { state.title.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = {
                    Text(text = "Title")
                })


            OutlinedTextField(value = state.description.value,
                onValueChange = { state.description.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = {
                    Text(text = "Description")
                })

        }
    }

}
