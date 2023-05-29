package com.germantv.composetvnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.germantv.composetvnote.data.NoteDataSource
import com.germantv.composetvnote.model.Note
import com.germantv.composetvnote.screens.NoteScreen
import com.germantv.composetvnote.screens.NoteViewModel
import com.germantv.composetvnote.ui.theme.ComposeTVNoteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // this is the dependency container
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTVNoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val noteViewModelObject = viewModel<NoteViewModel>() // same as below
                    //val noteViewModelObject: NoteViewModel by viewModels()
                    MyApp(noteViewModelObject)
                    //

                }
            }
        }
    }
}

@Composable
fun MyApp(noteViewModel:NoteViewModel){
    var myNoteList = noteViewModel.noteList.collectAsState().value
    NoteScreen(notes =myNoteList,
        onRemoveNote = { note->
            noteViewModel.removeNote(note)
        },
        onAddNote = { note->
            noteViewModel.addNote(note)
        })
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTVNoteTheme {
        //Greeting("Android")
    }
}