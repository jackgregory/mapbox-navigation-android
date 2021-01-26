package com.mapbox.navigation.ui.base.model.speedlimit

import com.mapbox.navigation.base.speed.model.SpeedLimitSign
import com.mapbox.navigation.base.speed.model.SpeedLimitUnit
import com.mapbox.navigation.ui.base.MapboxState
import com.mapbox.navigation.ui.base.formatter.ValueFormatter

sealed class SpeedLimitState: MapboxState {
    class UpdateSpeedLimit(
        private val speedKPH: Int,
        private val speedUnit: SpeedLimitUnit,
        private val signFormat: SpeedLimitSign,
        private val speedLimitFormatter: ValueFormatter<UpdateSpeedLimit, String>
    ) : SpeedLimitState() {
        fun getSpeed() = speedKPH
        fun getSpeedUnit() = speedUnit
        fun getSignFormat() = signFormat
        fun getSpeedLimitFormatter() = speedLimitFormatter
    }

    sealed class Visibility: SpeedLimitState() {
        class Visible: Visibility()
        class Hidden: Visibility()
    }
}
