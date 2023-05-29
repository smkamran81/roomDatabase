package com.germantv.composetvnote.screens

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.germantv.composetvnote.data.NoteDataSource
import com.germantv.composetvnote.model.Note
import com.germantv.composetvnote.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) :ViewModel() {


    //var noteList = mutableStateListOf<Note>()
    private val tag : String = "NoteViewModel"
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()
    // last position here
    init {
        //val source = NoteDataSource()
        //noteList.addAll(source.loadNotes())
        viewModelScope.launch(Dispatchers.IO){
              repository.getAllNotes().distinctUntilChanged().collect{
                    if(it.isNullOrEmpty()){
                         Log.d(tag,"EmptyList")
                    }else{
                        _noteList.value = it
                    }
              }
        }
    }
    /*
     The viewModelScope is typically provided by a ViewModel from the Android Architecture Components
     and ensures that the coroutine is canceled when the ViewModel is destroyed,
     preventing potential memory leaks.
    */
    fun addNote(note:Note){
        viewModelScope.launch{ repository.addNote(note) }
    }
    fun removeNote(note:Note){
        //noteList.remove(note)
        viewModelScope.launch{ repository.deleteNote(note) }
    }
    fun updateNote(note:Note){
        //noteList.remove(note)
        viewModelScope.launch{ repository.updateNote(note) }
    }

}