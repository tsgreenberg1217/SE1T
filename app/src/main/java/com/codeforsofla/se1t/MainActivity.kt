package com.codeforsofla.se1t

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codeforsofla.se1t.ShipmentFormFeature.ShipmentFormActivity
import com.codeforsofla.se1t.ShipmentViewModel
import com.codeforsofla.se1t.ShippingListScreen
import com.codeforsofla.se1t.ui.theme.Se1tTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Se1tTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen {
                        val intent = Intent(this, ShipmentFormActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(goToForm: () -> Unit) {
    val viewModel: ShipmentViewModel = viewModel()

    val materialBlue700 = Color.Blue
    val scaffoldState = rememberScaffoldState(
        rememberDrawerState(DrawerValue.Closed)
    )

    val selectedColor = Color(0xFFF57841)



    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("SE1T") },
                backgroundColor = MaterialTheme.colors.background
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                goToForm()
            },
            backgroundColor = Color(0xFF83EEE8)) {
                Icon(Icons.Rounded.Add, "", tint = Color.White)
            }
        },
        drawerContent = {
            Column(Modifier.fillMaxSize()) {

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        val strokeWidth = 3 * density
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
        },
        content = {
            ShippingListScreen(shippingList = viewModel.state.value.shipmentList)
        },
        bottomBar = {
            BottomAppBar(backgroundColor = Color.White) {
                Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                    Spacer(modifier = Modifier.width(10.dp))
                    IconButton(
                        onClick = {
                            viewModel.filterList("Todd")
                        }
                    ) {
                        Icon(
                            Icons.Rounded.Person, "", tint =
                            if (viewModel.state.value.bottomNavState == BottomNavState.Personal) selectedColor else Color.DarkGray
                        )
                    }

                    IconButton(
                        onClick = {
                            viewModel.getFullList()
                        }
                    ) {
                        Icon(
                            Icons.Default.List,
                            "",
                            tint = if (viewModel.state.value.bottomNavState == BottomNavState.All) selectedColor else Color.DarkGray
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                }
            }
        }
    )
}