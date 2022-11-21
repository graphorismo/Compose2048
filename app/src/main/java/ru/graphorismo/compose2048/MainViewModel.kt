package ru.graphorismo.compose2048

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var gameField = mutableStateOf(GameFieldState(mutableListOf(mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf())))
}