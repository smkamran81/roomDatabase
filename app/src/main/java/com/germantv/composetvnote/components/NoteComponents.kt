package com.germantv.composetvnote.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalTextInputService

import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NoteInputText(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int,
    onTextChange: (String) -> Unit,
    onImeChange: () -> Unit = {}
) {
     //val keyboardController = LocalSoftwareKeyboardController.current
    val keyboardController = LocalTextInputService.current
     TextField(
         value = text,
         label = { Text(text = label) },
         onValueChange = onTextChange,
         maxLines = maxLine,
         colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
         keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
         keyboardActions = KeyboardActions(onDone = {
             onImeChange()
             keyboardController?.hideSoftwareKeyboard()
         })
     )
}

@Composable
fun NoteButton(
    modifier: Modifier = Modifier,
    text:String,
    onClick: () -> Unit,
    enabled :Boolean = true, ){
     Button(onClick = onClick, shape = CircleShape, enabled = enabled, modifier = modifier) {
         Text(text = text)
     }
}