package com.germantv.composetvnote

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp //it gives hilt access to entire application instance
class NoteApplication:Application() {

}