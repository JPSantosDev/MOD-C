package com.example.mod_c.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Note
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mod_c.ui.components.PinField
import com.example.mod_c.ui.preferences.AppPreferences
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    preferences: AppPreferences,
    onFinish: () -> Unit
) {
    val transition = remember { Animatable(900f) }
    val state by preferences.state.collectAsState(null)
    val scope = rememberCoroutineScope()

    if (state == null){
        CircularProgressIndicator()
        return
    }

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

        if (current.splashScreenSeen) delay(3000)
        else{
            delay(10000)
            scope.launch {
                preferences.finishOnboarding()
            }

        }
        onFinish(
        )
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .border(2.dp,Color.Black),
            contentAlignment = Alignment.Center
        ){
            Text("A+", fontStyle = FontStyle.Italic, fontSize = 48.sp)
        }
        Text("Aprender+", fontSize = 24.sp)
        Spacer(Modifier.height(8.dp))
        Text("Digite seu pin de acesso")
        PinField()
    }

}

@Composable
fun PreviewLoginScreen(){
    LoginScreen()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(
    preferences: AppPreferences,
    onVoltar: () -> Unit,
    onHome: () -> Unit,
    onExercise: () -> Unit,
    onExplore: () -> Unit,
    onArticles: () -> Unit,
    onPerfil: () -> Unit
){
    Scaffold(
        topBar ={
            TopAppBar(
                title = {
                    Text("Perfil")
                },
                navigationIcon = {
                    IconButton(
                        onClick = onVoltar
                    ) {
                        Image(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                contentPadding = PaddingValues(8.dp),



                ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(
                        onClick = onHome
                    ) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = null
                        )
                    }
                    IconButton(
                        onClick = onExplore
                    ) {
                        Icon(
                            imageVector = Icons.Default.Explore,
                            contentDescription = null
                        )
                    }
                    IconButton(
                        onClick = onExercise
                    ) {
                        Icon(
                            imageVector = Icons.Default.Note,
                            contentDescription = null
                        )
                    }

                    IconButton(
                        onClick = onArticles
                    ) {
                        Icon(
                            imageVector = Icons.Default.Book,
                            contentDescription = null
                        )
                    }
                    IconButton(
                        onClick = onPerfil
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = null
                        )
                    }

                }
            }
        }
    ) {pad->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(pad),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(
                modifier = Modifier
                    .border(1.dp,Color.Gray),
                contentAlignment = Alignment.Center

            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        imageVector = Icons.Default.AccountCircle, // inserir imagem aqui
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(48.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text("Nome de usuário", style =  MaterialTheme.typography.headlineSmall)
                    Text("email",style =  MaterialTheme.typography.bodyLarge) //inserir email aqui
                    Text("Nivel",style =  MaterialTheme.typography.bodyMedium) // inserir data de nascimento aqui
                }

                Box(){

                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun PreviewPerfilScreen(){
    PerfilScreen(
        onVoltar = {},
        onPerfil = {},
        onHome = {},
        onExplore = {},
        onArticles = {},
        onExercise = {},
        preferences = AppPreferences(LocalContext.current)
    )
}