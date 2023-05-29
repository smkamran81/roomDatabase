package com.germantv.composetvnote.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.germantv.composetvnote.R
import com.germantv.composetvnote.components.NoteButton
import com.germantv.composetvnote.components.NoteInputText
import com.germantv.composetvnote.model.Note
import com.germantv.composetvnote.util.formatDate
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(notes: List<Note>, onAddNote: (Note) -> Unit, onRemoveNote: (Note) -> Unit) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var context = LocalContext.current
    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        }, actions = {
            Icon(imageVector = Icons.Default.Notifications, contentDescription = "dddd")
        },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.LightGray)
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(text = title, label = "Enter Title", maxLine = 1, onTextChange = {
                if (it.all { char ->
                        char.isLetter() || char.isWhitespace()
                    }) title = it
            })
            Spacer(modifier = Modifier.height(5.dp))
            NoteInputText(
                text = description,
                label = "Enter Description",
                maxLine = 1,
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) description = it
                })
            Spacer(modifier = Modifier.height(5.dp))
            NoteButton(text = "Save", onClick = { /*TODO*/
                if (title.isNotBlank() && description.isNotEmpty()) {
                    onAddNote(Note(title=title, description = description))
                    title = ""
                    description = ""
                    Toast.makeText(context,"Added",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context,"Enter Title and Description",Toast.LENGTH_SHORT).show()
                }
            })
            Divider(modifier = Modifier.padding(6.dp))
            LazyColumn {
                items(notes) { note ->
                    //Divider(modifier = Modifier.padding(6.dp))
                    NoteRow(note=note,onNoteClicked={ onRemoveNote(it) })
                }
            }
        }

    }

}

@Composable
fun NoteRow(modifier: Modifier = Modifier, note: Note, onNoteClicked: (Note) -> Unit) {
    Surface(
        modifier = Modifier.padding(5.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        shadowElevation = 6.dp
    ) {
       Column(modifier = modifier
           .clickable {
               onNoteClicked(note)
           }
           .padding(horizontal = 14.dp, vertical = 14.dp),
       horizontalAlignment = Alignment.Start) {
            Text(text = note.title, style = MaterialTheme.typography.titleMedium)
            Text(text = note.description, style = MaterialTheme.typography.titleSmall)
            Text(text = formatDate(note.entryDate.time))
       }
    }

}




