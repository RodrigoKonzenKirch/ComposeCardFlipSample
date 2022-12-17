package com.example.composecardflipsample.ui.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag

@Composable
fun FlipCard() {

    var rotated by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(500)
    )

    val animateFront by animateFloatAsState(
        targetValue = if (!rotated) 1f else 0f,
        animationSpec = tween(500)
    )

    val animateBack by animateFloatAsState(
        targetValue = if (rotated) 1f else 0f,
        animationSpec = tween(500)
    )

    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            Modifier
                .fillMaxSize(.5f)
                .graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 8 * density
                }
                .clickable {
                    rotated = !rotated
                },
            backgroundColor = Color.LightGray
        )
        {
            Column(
                Modifier
                    .fillMaxSize()
                    // The flip animation occurs here
                    // RotationY in graphicsLayer rotates the layout horizontally
                    .graphicsLayer {
                        alpha = if (rotated) animateBack else animateFront
                        rotationY = rotation
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                if (rotated){
                    Back()

                }else{
                    Front()
                }

            }

        }
    }
}

@Composable
fun Back(){
    Column(modifier = Modifier.testTag("Back")) {
        Text(text = "Back")
        Text(text = "Extra info")

    }
}

@Composable
fun Front() {
    Column(modifier = Modifier.testTag("Front")) {
        Text(text = "Front")
        Text(text = "Name:")
        Text(text = "Date:")
        Text(text = "Other fields")
    }
}