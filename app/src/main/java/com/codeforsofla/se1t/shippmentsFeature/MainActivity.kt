package com.codeforsofla.se1t.shippmentsFeature

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codeforsofla.se1t.R
import com.codeforsofla.se1t.ShipmentViewModel
import com.codeforsofla.se1t.ShippingListScreen
import com.codeforsofla.se1t.shipmentFormFeature.ShipmentFormActivity
import com.codeforsofla.se1t.ui.theme.DDA_BLUE
import com.codeforsofla.se1t.ui.theme.DDA_YELLOW
import com.codeforsofla.se1t.ui.theme.Se1tTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {


    private fun goToAddShipment() {
        val intent = Intent(this, ShipmentFormActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Se1tTheme {
                // A surface container using the 'background' color from the theme
                MainScreen(
                    addShipment = ::goToAddShipment
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun MainScreen(
    addShipment: () -> Unit,
) {

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
    )
    val viewModel: ShipmentViewModel = viewModel()

    val scaffoldState = rememberScaffoldState()

    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(8.dp),
        sheetContent = { FilterModalScreen() },
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = {
//                        Image(
//                            painterResource(id = R.drawable.ftldda_logo),
//                            contentDescription = ""
//                        )
                        Text(text = "SETRK")
                    },
                    backgroundColor = Color.White
                )

            },
            bottomBar = {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(DDA_BLUE)
                        .height(50.dp), Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            if (sheetState.isVisible) sheetState.hide() else sheetState.show()
                        }
                    }) {
                        Icon(Icons.Default.Search, "", Modifier.size(40.dp), tint = Color.White)
                    }

                    IconButton(onClick = addShipment) {
                        Icon(Icons.Rounded.Add, "", Modifier.size(40.dp), tint = Color.White)
                    }
                }
            },
            content = { _ ->
                val pagerState = rememberPagerState()
                val iconList = remember {
                    listOf(Icons.Rounded.Person, Icons.Rounded.List)

                }

                Column(Modifier.fillMaxSize()) {
                    TabRow(
                        // Our selected tab is our current page
                        selectedTabIndex = pagerState.currentPage,
                        backgroundColor = DDA_YELLOW,
                        contentColor = Color.White,
                        // Override the indicator, using the provided pagerTabIndicatorOffset modifier
                        indicator = { tabPositions ->
                            TabRowDefaults.Indicator(
                                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                            )
                        }
                    ) {
                        // Add tabs for all of our pages

                        iconList.forEachIndexed { index, icon ->
                            Tab(
                                text = { Icon(icon, "") },
                                selected = pagerState.currentPage == index,
                                onClick = { /* TODO */ },
                            )
                        }
                    }
                    HorizontalPager(
                        count = 2,
                        state = pagerState
                    ) { page ->
                        ShippingListScreen(viewModel.state.value.run {
                            if (page == 0) personalShipments else filteredShipments
                        })

                    }
                }


            }
        )

    }


}

