package com.germantv.composetvnote.di

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Note
import android.text.method.SingleLineTransformationMethod
import androidx.room.Room
import com.germantv.composetvnote.data.NoteDatabase
import com.germantv.composetvnote.data.NoteDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun providesNoteDao(noteDatabase: NoteDatabase): NoteDatabaseDao = noteDatabase.noteDao()

    @Singleton
    @Provides
    fun providesNoteDatabase(@ApplicationContext context: Context): NoteDatabase =
        Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "notes_db"
        )
            .fallbackToDestructiveMigration()
            .build()
}