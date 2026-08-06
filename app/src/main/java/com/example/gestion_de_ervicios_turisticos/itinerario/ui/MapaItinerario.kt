package com.example.gestion_de_ervicios_turisticos.itinerario.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.gestion_de_ervicios_turisticos.itinerario.model.ItemItinerario
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.extension.compose.annotation.generated.PolylineAnnotation

@Composable
fun MapaItinerario(
    items: List<ItemItinerario>,
    modifier: Modifier = Modifier
) {
    val puntos = remember(items) {
        items.map { Point.fromLngLat(it.longitud, it.latitud) }
    }

    val viewportState = rememberMapViewportState {
        if (puntos.isNotEmpty()) {
            setCameraOptions {
                center(puntos.first())
                zoom(12.0)
            }
        }
    }

    MapboxMap(
        modifier = modifier.fillMaxSize(),
        mapViewportState = viewportState
    ) {
        // Marcador por cada parada del itinerario
        items.forEachIndexed { index, item ->
            PointAnnotation(point = Point.fromLngLat(item.longitud, item.latitud)) {
                textField = "${index + 1}. ${item.nombreServicio}"
            }
        }

        // Línea que conecta las paradas en orden (ruta simple)
        if (puntos.size >= 2) {
            PolylineAnnotation(points = puntos) {
                lineColor = Color(0xFF0D5B54)   // VerdeOscuro de ColoresSaranta
                lineWidth = 4.0
            }
        }
    }
}
