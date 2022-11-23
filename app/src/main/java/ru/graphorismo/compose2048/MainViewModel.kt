package ru.graphorismo.compose2048

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.graphorismo.compose2048.domain.UiEvent
import ru.graphorismo.compose2048.domain.move_performers.MovePerformersFacade
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private var movePerformersFacade: MovePerformersFacade) : ViewModel() {

    var gameField : MutableState<GameFieldState?> =
        mutableStateOf( GameFieldState(
            mutableListOf(
                mutableListOf(2,0,0,0),
                mutableListOf(0,2,0,0),
                mutableListOf(2,0,0,0),
                mutableListOf(0,0,2,0)
            )
        ))

    fun onEvent(uiEvent: UiEvent){
        when(uiEvent){
            is UiEvent.SwipeUp -> {
                val handledGameState = gameField.value?.copy()
                if (handledGameState != null) {
                    movePerformersFacade.performMoveUp(handledGameState.state)
                }
                gameField.value = null
                gameField.value = handledGameState
            }
            is UiEvent.SwipeDown -> {
                val handledGameState = gameField.value?.copy()
                if (handledGameState != null) {
                    movePerformersFacade.performMoveDown(handledGameState.state)
                }
                gameField.value = null
                gameField.value = handledGameState
            }
            is UiEvent.SwipeLeft -> {
                val handledGameState = gameField.value?.copy()
                if (handledGameState != null) {
                    movePerformersFacade.performMoveLeft(handledGameState.state)
                }
                gameField.value = null
                gameField.value = handledGameState
            }
            is UiEvent.SwipeRight -> {
                val handledGameState = gameField.value?.copy()
                if (handledGameState != null) {
                    movePerformersFacade.performMoveRight(handledGameState.state)
                }
                gameField.value = null
                gameField.value = handledGameState
            }
        }
    }
}