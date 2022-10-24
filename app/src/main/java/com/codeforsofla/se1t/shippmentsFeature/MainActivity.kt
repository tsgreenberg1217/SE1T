package com.codeforsofla.se1t.shippmentsFeature

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codeforsofla.se1t.BottomNavState
import com.codeforsofla.se1t.ShipmentFilterSwitch
import com.codeforsofla.se1t.ShipmentViewModel
import com.codeforsofla.se1t.ShippingListScreen
import com.codeforsofla.se1t.shipmentFormFeature.ShipmentFormActivity
import com.codeforsofla.se1t.ui.theme.Se1tTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import java.util.logging.Filter

class MainActivity : ComponentActivity() {


    private fun goToAddShipment() {
        val intent = Intent(this, ShipmentFormActivity::class.java)
        startActivity(intent)
    }

    private fun openFilterBottomSheet() {

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
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    val viewModel: ShipmentViewModel = viewModel()

    val scaffoldState = rememberScaffoldState()

    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = { FilterModalScreen() },) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = { Text("SETRK") },
                    backgroundColor = MaterialTheme.colors.background
                )

            },
            bottomBar = {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Cyan)
                        .height(50.dp), Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            if (sheetState.isVisible) sheetState.hide() else sheetState.show()
                        }
                    }) {
                        Icon(Icons.Default.Search, "", Modifier.size(40.dp))
                    }

                    IconButton(onClick = addShipment) {
                        Icon(Icons.Rounded.Add, "", Modifier.size(40.dp))
                    }
                }
            }
        ) { _ ->
            val pagerState = rememberPagerState()
            val iconList = remember {
                listOf(Icons.Rounded.Person, Icons.Rounded.List)

            }

            Column(Modifier.fillMaxSize()) {
                TabRow(
                    // Our selected tab is our current page
                    selectedTabIndex = pagerState.currentPage,
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
                        // what do we do if page == 1,
                        // and page == 2 personalShipments and filteredShipments needs < page == 3(conditional)?
                    })

                }
            }


        }

    }


}

