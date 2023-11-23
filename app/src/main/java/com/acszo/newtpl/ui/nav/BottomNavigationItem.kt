package com.acszo.newtpl.ui.nav

import com.acszo.newtpl.R
import com.acszo.newtpl.ui.nav.Pages.station
import com.acszo.newtpl.ui.nav.Pages.university

sealed class BottomNavItem(var route: String, var iconSelected: Int, var icon: Int) {

    object University: BottomNavItem(university, R.drawable.ic_school_filled, R.drawable.ic_school_outline)
    object Station: BottomNavItem(station, R.drawable.ic_bus_filled, R.drawable.ic_bus_outline)

}
