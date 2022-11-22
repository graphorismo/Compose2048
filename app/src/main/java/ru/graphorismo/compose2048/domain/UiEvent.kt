package ru.graphorismo.compose2048.domain

sealed interface UiEvent{
    object SwipeUp : UiEvent
    object SwipeDown: UiEvent
    object SwipeLeft: UiEvent
    object SwipeRight: UiEvent
}