package com.codeforsofla.se1t.ShipmentFormFeature

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codeforsofla.se1t.ui.theme.Se1tTheme

class ShipmentFormActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
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

    @RequiresApi(Build.VERSION_CODES.N)
    @Composable
    fun MainFormScreen() {

        val viewmodel: ShipmentFormViewmodel = viewModel()

        val dialog = getPicker(context = this)

        Column(Modifier.fillMaxSize(), Arrangement.SpaceEvenly, Alignment.CenterHorizontally) {
            OutlinedTextField(
                modifier = Modifier,
                value = viewmodel.state.value.business,
                label = {
                    Text(text = "Business")
                },
                onValueChange = {
                    viewmodel.onChangeBusiness(it)
                })

            OutlinedTextField(
                modifier = Modifier.clickable {
                    dialog.show()
                },
                value = viewmodel.state.value.start.toString(),
                label = {
                    Text(text = "Start")
                },
                onValueChange = {
                }, enabled = false
            )



            OutlinedTextField(
                modifier = Modifier.clickable { dialog.show() },
                value = viewmodel.state.value.end.toString(),
                label = {
                    Text(text = "End")
                },
                onValueChange = {

                }, enabled = false
            )

            Button(onClick = { finish() }, Modifier.fillMaxWidth().padding(horizontal = 80.dp)) {
                Text(text = "Submit", color = Color.White)
            }
        }


    }

    @RequiresApi(Build.VERSION_CODES.N)
    @Composable
    fun getPicker(context: Context): DatePickerDialog {
        return DatePickerDialog(context)
    }
}