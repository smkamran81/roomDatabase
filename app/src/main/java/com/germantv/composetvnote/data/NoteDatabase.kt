package com.germantv.composetvnote.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.germantv.composetvnote.model.Note
import com.germantv.composetvnote.util.DateConverter
import com.germantv.composetvnote.util.UUIDConverter

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class,UUIDConverter::class)
abstract class NoteDatabase:RoomDatabase() {
    abstract fun noteDao():NoteDatabaseDao
}