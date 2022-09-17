package com.codeforsofla.se1t

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.codeforsofla.se1t.ui.theme.Disabled
import com.codeforsofla.se1t.ui.theme.Enabled

@Composable
fun ShipmentFilterSwitch(name:String){
    Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
        var isChecked:Boolean by remember { mutableStateOf(false) }
        Text(text = name)
        Switch(
            checked = isChecked,
            onCheckedChange ={isChecked = !isChecked},
            colors = SwitchDefaults.colors(
                checkedThumbColor = Enabled,
                uncheckedThumbColor = Disabled,
                checkedTrackColor = Color.LightGray,
                uncheckedTrackColor = Color.LightGray,
            )
        )
    }
}