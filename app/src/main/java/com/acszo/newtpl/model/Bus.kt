package com.acszo.newtpl.model

data class Bus(
    val ArrivalTime: String,
    val Departure: String,
    val DepartureTime: String,
    val Destination: String,
    val Direction: String,
    val IsDestination: Boolean,
    val IsStarted: Boolean,
    val Latitude: Double,
    val Line: String,
    val LineCode: String,
    val LineType: String,
    val Longitude: Double,
    val NextPasses: String,
    val Note: String,
    val Race: String,
    val Spare2: Any,
    val StopCode: Any,
    val Time: String
)