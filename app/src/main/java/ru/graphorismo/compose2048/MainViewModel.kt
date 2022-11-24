package ru.graphorismo.compose2048

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.graphorismo.compose2048.domain.EndGameChecker
import ru.graphorismo.compose2048.domain.GameFieldReseter
import ru.graphorismo.compose2048.domain.RandomSpawnPerformer
import ru.graphorismo.compose2048.domain.UiEvent
import ru.graphorismo.compose2048.domain.move_performers.MovePerformersFacade
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private var movePerformersFacade: MovePerformersFacade,
    private var randomSpawnPerformer: RandomSpawnPerformer,
    private var endGameChecker: EndGameChecker,
    private var gameFieldReseter: GameFieldReseter
) : ViewModel() {

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
            is UiEvent.SwipeUp ->{
                handleEventWithLogic {
                    movePerformersFacade.performMoveUp(it) }
            }
            is UiEvent.SwipeDown ->{
                handleEventWithLogic {
                    movePerformersFacade.performMoveDown(it) }
            }
            is UiEvent.SwipeLeft ->{
                handleEventWithLogic {
                    movePerformersFacade.performMoveLeft(it) }
            }
            is UiEvent.SwipeRight ->{
                handleEventWithLogic {
                    movePerformersFacade.performMoveRight(it) }
            }
        }
    }

    private fun handleEventWithLogic(logic: (MutableList<MutableList<Int>>) -> Unit){
        val handledGameState = gameField.value?.copy()
        if (handledGameState != null) {
            logic(handledGameState.state)
            randomSpawnPerformer.numberOfNumsToSpawn = 2
            randomSpawnPerformer.matrixWorker.matrix = handledGameState.state
            randomSpawnPerformer.execute()
            endGameChecker.matrixWorker.matrix = handledGameState.state
            endGameChecker.execute()
            if (endGameChecker.isEndGame){
                gameFieldReseter.matrixWorker.matrix = handledGameState.state
                gameFieldReseter.execute()
            }
        }
        gameField.value = null
        gameField.value = handledGameState
    }
}