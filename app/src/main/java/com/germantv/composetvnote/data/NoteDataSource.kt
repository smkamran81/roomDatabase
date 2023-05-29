package com.germantv.composetvnote.data

import com.germantv.composetvnote.model.Note

class NoteDataSource {

    fun loadNotes(): List<Note> {
        return listOf(

            Note(title = "title1", description = "description1"),
            Note(title = "title2", description = "description3"),
            Note(title = "title3", description = "description3"),
            Note(title = "title4", description = "description4"),
            Note(title = "title5", description = "description5"),
            Note(title = "title6", description = "description6"),
            Note(title = "title7", description = "description7"),
            Note(title = "title8", description = "description8"),
            Note(title = "title9", description = "description9"),
            Note(title = "title10", description = "description10")
        )
    }
}