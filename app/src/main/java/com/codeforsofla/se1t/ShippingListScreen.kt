package com.codeforsofla.se1t

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat

@Composable
fun ShippingListScreen(shippingList: List<ShippingData>) {
    LazyColumn(
    ) {
        items(shippingList) { data ->
            Spacer(Modifier.height(20.dp))
            Row(
                Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(3.dp))
                    .padding(10.dp),
                Arrangement.SpaceBetween,
                Alignment.CenterVertically
            ) {
                Column() {
                    Text(text = data.recipient, color = Color.DarkGray)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = data.business, color = Color.DarkGray)
                }
                Spacer(modifier = Modifier.weight(1f))
                Column() {
                    Text(text = data.timeRange, color = Color.DarkGray)
                }
            }
        }
    }
}