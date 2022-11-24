package ru.graphorismo.compose2048

import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dagger.hilt.android.AndroidEntryPoint
import ru.graphorismo.compose2048.domain.UiEvent
import ru.graphorismo.compose2048.ui.theme.Compose2048Theme
import kotlin.math.absoluteValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel : MainViewModel by viewModels()
    var oldX = 0f
    var oldY = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val screenPixelDensity = this.resources.displayMetrics.density
        val screenHeightInDp = this.resources.displayMetrics.heightPixels / screenPixelDensity
        val screenWidthInDp = this.resources.displayMetrics.widthPixels / screenPixelDensity
        val smallerScreenSide = if(screenHeightInDp < screenWidthInDp) screenHeightInDp else screenWidthInDp
        val gameFieldMargin = smallerScreenSide/40
        val gameFieldPadding = smallerScreenSide/40
        val gameFieldSize = smallerScreenSide - (gameFieldMargin*2)
        val gameCellMargin = gameFieldMargin / 2
        val gameCellSize = (gameFieldSize - (gameCellMargin*5)) / 5

        setContent {
            Compose2048Theme {
                var fieldState = remember {
                    viewModel.gameField.value
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Cyan)
                        .padding(gameFieldMargin.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Card(
                        modifier = Modifier
                            .width(gameFieldSize.dp)
                            .height(gameFieldSize.dp)
                            .padding(gameFieldPadding.dp),
                        elevation = 20.dp,
                        shape = RoundedCornerShape(gameFieldPadding.dp)
                    ) {
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Blue),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            for (i in 0..3){
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceAround,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    for (j in 0..3){
                                        Card(
                                            modifier = Modifier
                                                .height(gameCellSize.dp)
                                                .width(gameCellSize.dp),
                                            elevation =  5.dp,
                                            shape = RoundedCornerShape(5.dp)
                                        ) {
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .background(Color.Green),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.SpaceAround
                                            ) {
                                                viewModel.gameField.value?.state?.get(i)?.get(j)?.toString()
                                                    ?.let { Text(text = it, fontSize = 26.sp) }
                                            }
                                        }
                                    }
                                }
                            }

                        }

                    }

                }
            }
        }
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            val threshold = 150f
            when(event.action){
                MotionEvent.ACTION_DOWN ->{
                    oldX = event.x
                    oldY = event.y
                }
                MotionEvent.ACTION_UP -> {
                    val xShift = event.x - oldX
                    val yShift = event.y - oldY
                    if(xShift >= 0f
                        && xShift.absoluteValue > threshold
                        && xShift.absoluteValue > yShift.absoluteValue
                    ){
                        viewModel.onEvent(UiEvent.SwipeRight)
                    }else if(xShift < 0f
                        && xShift.absoluteValue > threshold
                        && xShift.absoluteValue > yShift.absoluteValue
                    ){
                        viewModel.onEvent(UiEvent.SwipeLeft)
                    }else if (yShift >= 0f
                        && yShift.absoluteValue > threshold
                    ){
                        viewModel.onEvent(UiEvent.SwipeDown)
                    }else if (yShift < 0f
                        && yShift.absoluteValue > threshold
                    ){
                        viewModel.onEvent(UiEvent.SwipeUp)
                    }
                }
            }
        }
        return super.onTouchEvent(event)
    }


}