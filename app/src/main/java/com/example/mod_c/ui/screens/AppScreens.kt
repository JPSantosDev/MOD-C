package com.example.mod_c.ui.screens

import android.R
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBusFilled
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.mod_c.ui.preferences.AppPreferences
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun SplashScreen(
    preferences: AppPreferences,
    onFinish: () -> Unit
) {
    val transition = remember { Animatable(900f) }
    val state by preferences.state.collectAsState(null)
    val current = state!!

    LaunchedEffect(current.splashScreenSeen) {

            delay(500)

            transition.animateTo(
                targetValue = -1200f,
                animationSpec = tween(
                    durationMillis = 1200,
                    easing = FastOutSlowInEasing
                )
            )

        if (current.splashScreenSeen) delay(500)
        else delay(10000)
        onFinish()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // Fundo
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        listOf(
                            Color(0xFF8128FF),
                            Color(0xFF2A0147)
                        )
                    )
                )
        )

        // Logo
        Image(
            imageVector = Icons.Default.Menu,
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.Center)
        )

        // Painel preto
        Box(
            modifier = Modifier
                .size(900.dp)
                .align(Alignment.BottomStart)
                .offset {
                    IntOffset(
                        -250,
                        transition.value.toInt()
                    )
                }
                .rotate(-18f)
                .background(Color(0xFF12001C))
        )
    }



}

@Composable
fun LoginScreen(){

}