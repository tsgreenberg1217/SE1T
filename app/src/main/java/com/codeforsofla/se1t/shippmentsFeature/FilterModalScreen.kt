package com.codeforsofla.se1t.shippmentsFeature

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.codeforsofla.se1t.ShipmentFilterSwitch


@Composable
fun FilterModalScreen() {
    Column(Modifier.fillMaxWidth()) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                val strokeWidth = 1 * density
                val y = size.height - strokeWidth / 2

                drawLine(
                    Color.DarkGray,
                    Offset(0f, y),
                    Offset(size.width, y),
                    strokeWidth
                )
            }) {
            Text(text = "Filters", fontSize = 28.sp)
        }
        ShipmentFilterSwitch(name = "Brad's Beds")
        ShipmentFilterSwitch(name = "Matt's Mattresess")

    }
}
