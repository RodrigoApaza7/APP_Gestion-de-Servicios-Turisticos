package com.example.gestion_de_ervicios_turisticos.splash.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.gestion_de_ervicios_turisticos.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.ui.layout.ContentScale



/**
 * Configuración editable del Splash Screen.
 * Todo lo ajustable vive aquí (cantidad de círculos, colores, tamaños, tiempos),
 * para no tener que tocar la lógica de animación si algo cambia de diseño.
 */
data class SplashConfig(
    val circleCount: Int = 4,
    // Colores de cada círculo, del más pequeño (índice 0) al más grande.
    // Si agregas más círculos que colores, se repite el último.
    val circleColors: List<Color> = listOf(
        Color(0xFFFFFFFF), // círculo 1 (el más chico, pegado al logo)
        Color(0xFF0F4C46), // círculo 2
        Color(0xFFF2F2F0), // círculo 3
        Color(0xFF0F4C46)  // círculo 4 (el más grande / externo)
    ),
    val baseCircleSize: Dp = 90.dp,   // tamaño del primer círculo (el más chico)
    val circleSizeStep: Dp = 70.dp,   // cuánto crece cada círculo siguiente
    val logoSize: Dp = 72.dp,
    val logoAnimDurationMs: Int = 600,
    val circleAnimDurationMs: Int = 450,
    val holdBeforeCirclesMs: Long = 200,  // pausa entre que aparece el logo y arrancan los círculos
    val holdAfterAnimationMs: Long = 900  // pausa final antes de navegar
)

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit,
    config: SplashConfig = SplashConfig()
) {
    // Controla opacidad y escala del logo por separado
    val logoAlpha = remember { Animatable(0f) }
    val logoScale = remember { Animatable(0.7f) }

    // Un Animatable por círculo: 0f = invisible/sin expandir, 1f = totalmente expandido
    val circleProgress = remember {
        List(config.circleCount) { Animatable(0f) }
    }

    LaunchedEffect(Unit) {
        // 1. Aparece el logo con fade + escala suave
        launch {
            logoScale.animateTo(
                targetValue = 1f,
                animationSpec = tween(config.logoAnimDurationMs, easing = EaseOutCubic)
            )
        }
        logoAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(config.logoAnimDurationMs, easing = EaseOutCubic)
        )

        delay(config.holdBeforeCirclesMs)

        // 2. Círculos, uno por uno: cada animateTo() espera a que termine el anterior
        //    (por eso es secuencial y no simultáneo)
        for (i in 0 until config.circleCount) {
            circleProgress[i].animateTo(
                targetValue = 1f,
                animationSpec = tween(config.circleAnimDurationMs, easing = EaseOutCubic)
            )
        }

        // 3. Pausa final y navegación automática
        delay(config.holdAfterAnimationMs)
        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        // 1. Círculos (círculo más grande al más chico)
        for (i in config.circleCount - 1 downTo 0) {
            val size = config.baseCircleSize + (config.circleSizeStep * i)
            val progress = circleProgress[i].value

            Box(
                modifier = Modifier
                    .size(size)
                    .scale(progress)
                    .alpha(progress)
                    .clip(CircleShape)
                    .background(config.circleColors.getOrElse(i) { Color.Gray })
            )
        } // <- el for CIERRA aquí, el Image queda AFUERA de él

        // 2. Logo real — se dibuja UNA sola vez, al final, encima de todos los círculos
        Image(
            painter = painterResource(id = R.drawable.logo_saranta),
            contentDescription = "Logo de Saranta",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(config.logoSize)
                .scale(logoScale.value)
                .alpha(logoAlpha.value)
                //.clip(CircleShape)
                //.background(Color.White)
        )

        // Borra por completo el Box placeholder blanco que tenías aquí — ya no se necesita
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(
        onSplashFinished = {},
        config = SplashConfig(
            baseCircleSize = 160.dp,  // círculo bien holgado
            logoSize = 100.dp          // ima
        )
    )
}