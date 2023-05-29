package com.germantv.composetvnote.repository

import com.germantv.composetvnote.data.NoteDatabaseDao
import com.germantv.composetvnote.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {

    //suspend fun addNote(note: Note) = noteDatabaseDao.insert(note)
    suspend fun addNote(note: Note){
        noteDatabaseDao.insert(note)
    }
    suspend fun updateNote(note:Note) = noteDatabaseDao.update(note)
    suspend fun deleteNote(note:Note) = noteDatabaseDao.deleteNote(note)
    suspend fun deleteAllNotes() = noteDatabaseDao.deleteAll()
    suspend fun deleteNoteById(id:String) = noteDatabaseDao.getNoteById(id)
    fun getAllNotes(): Flow<List<Note>> = noteDatabaseDao.getNote().flowOn(Dispatchers.IO).conflate()
}