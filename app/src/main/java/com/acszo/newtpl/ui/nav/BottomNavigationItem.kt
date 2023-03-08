package com.acszo.newtpl.ui.nav

import com.acszo.newtpl.R
import com.acszo.newtpl.ui.nav.Pages.station
import com.acszo.newtpl.ui.nav.Pages.university

sealed class BottomNavItem(var route: String, var icon: Int) {

    object University: BottomNavItem(university, R.drawable.baseline_school_24)
    object Station: BottomNavItem(station, R.drawable.baseline_directions_bus_filled_24)

}
