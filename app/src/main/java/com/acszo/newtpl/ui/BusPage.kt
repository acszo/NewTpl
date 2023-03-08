package com.acszo.newtpl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.acszo.newtpl.viewmodel.BusViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusPage(stopCode: String) {
    val viewModel = viewModel<BusViewModel>()
    viewModel.getBuses(stopCode)
    val buses = viewModel.buses.observeAsState()

    LazyColumn(modifier = Modifier
        .fillMaxHeight()
        .background(MaterialTheme.colorScheme.background)
        .padding(0.dp, 64.dp, 0.dp, 80.dp)
    ) {
        buses.value?.size?.let {
            items(it) {index ->
                val bus = buses.value!![index]
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(10.dp, 5.dp, 10.dp, 10.dp),
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.cardColors(containerColor =  MaterialTheme.colorScheme.surfaceVariant),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .padding(10.dp, 10.dp, 10.dp, 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        LineText(bus.LineCode, 60.dp)
                        Column {
                            BusInfoText(bus.Destination)
                            BusInfoText(bus.ArrivalTime)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LineText(lineCode: String, width: Dp) {
    Box(
        modifier = Modifier.width(width),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = lineCode,
            fontSize = 30.sp,
            fontWeight = FontWeight(500)
        )
    }
}

@Composable
fun BusInfoText(text: String) {
    Text(
        text = text,
        fontSize = 20.sp,
        fontWeight = FontWeight(500)
    )
}
