package com.codeforsofla.se1t.shipmentFormFeature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.codeforsofla.se1t.ui.theme.Se1tTheme

class ShipmentFormActivity : ComponentActivity() {

    private val viewModel: ShipmentFormViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Se1tTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainFormScreen()
                }
            }
        }
    }

    @Composable
    fun MainFormScreen() {

        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 40.dp), Arrangement.SpaceEvenly, Alignment.CenterHorizontally) {

            viewModel.run {

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.value.business,
                    label = {
                        Text(text = "Business")
                    },
                    onValueChange = ::onChangeBusiness)


                DateTimeInput(
                    ctx = this@ShipmentFormActivity,
                    title = "Arrival Time",
                    dayValue = state.value.startDay,
                    timeValue = state.value.startTime,
                    onDateChange = ::onChangeStartDate,
                    onTimeChange = ::onChangeStartTime
                )
                DateTimeInput(
                    ctx = this@ShipmentFormActivity,
                    title = "Departure Time",
                    dayValue = state.value.endDay,
                    timeValue = state.value.endTime,
                    onDateChange = ::onChangeEndDate,
                    onTimeChange = ::onChangeEndTime
                )

                Button(
                    onClick = { finish() },
                    Modifier.fillMaxWidth()
                ) {
                    Text(text = "Submit", color = Color.White)
                }
            }
        }


    }
}