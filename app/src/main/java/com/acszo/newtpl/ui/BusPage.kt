package com.acszo.newtpl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.acszo.newtpl.R
import com.acszo.newtpl.model.Bus
import com.acszo.newtpl.viewmodel.BusViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun BusPage(stopCode: String) {
    val viewModel = viewModel<BusViewModel>()
    LaunchedEffect(Unit) { viewModel.getBuses(stopCode) }
    val buses = viewModel.buses.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val isRetry = remember { mutableStateOf(false) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

    if (isRetry.value) {
        LaunchedEffect(Unit) { viewModel.getBuses(stopCode) }
        isRetry.value = false
    }

    if (isLoading) {
        ProgressIndicator()
    }
    else {
        if (buses.isEmpty()) {
            EmptyListMessage(isRetry)
        } else {
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = { viewModel.getBuses(stopCode) },
                indicator = { state, trigger ->
                    SwipeRefreshIndicator(
                        state = state,
                        refreshTriggerDistance = trigger,
                        contentColor = MaterialTheme.colorScheme.surface,
                        backgroundColor = MaterialTheme.colorScheme.primary,
                    )
                }
            ) {
               ListBuses(buses)
            }
        }
    }
}

@Composable
fun ProgressIndicator() {
    LinearProgressIndicator(
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun EmptyListMessage(value: MutableState<Boolean>) {
    val emoji = listOf(
        stringResource(R.string.ascii_emoji_sad),
        stringResource(R.string.ascii_emoji_idk),
        stringResource(R.string.ascii_emoji_lost),
        stringResource(R.string.ascii_emoji_mad),
        stringResource(R.string.ascii_emoji_table)
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = emoji.random(),
                fontSize = 40.sp,
            )
            Text(
                text = stringResource(R.string.no_available_buses),
                fontSize = 20.sp,
            )
            RetryButton(value)
        }
    }
}

@Composable
fun RetryButton(value: MutableState<Boolean>) {
    Button(onClick = { value.value = true }) {
        Text(text = stringResource(R.string.retry))
    }
}

@Composable
fun ListBuses(buses: List<Bus>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(0.dp, 10.dp, 0.dp, 0.dp)
    ) {
        items(items = buses) { bus ->
            BusItemList(bus)
        }
    }
}

@Composable
fun BusItemList(bus: Bus) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(10.dp, 5.dp, 10.dp, 10.dp),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(10.dp, 10.dp, 10.dp, 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LineText(bus.LineCode)
            Column(
                modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)
            ) {
                BusInfoText(
                    bus.Destination.lowercase()
                        .replaceFirstChar { it.uppercase() })
                BusInfoText(bus.ArrivalTime)
            }
        }
    }
}

@Composable
fun LineText(lineCode: String) {
    Box(
        modifier = Modifier.width(60.dp),
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
