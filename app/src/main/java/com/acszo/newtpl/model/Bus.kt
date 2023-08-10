package com.acszo.newtpl.model

data class Bus(
    val arrivalTime: String,
    val departure: String,
    val departureTime: String,
    val destination: String,
    val direction: String,
    val isDestination: Boolean,
    val isStarted: Boolean,
    val latitude: Double,
    val line: String,
    val lineCode: String,
    val lineType: String,
    val longitude: Double,
    val nextPasses: String,
    val note: String,
    val race: String,
    val spare2: Any,
    val stopCode: Any,
    val time: String
)