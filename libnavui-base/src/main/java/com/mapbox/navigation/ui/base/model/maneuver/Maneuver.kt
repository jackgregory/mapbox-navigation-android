package com.mapbox.navigation.ui.base.model.maneuver

import com.mapbox.api.directions.v5.models.BannerInstructions

/**
 * A simplified data structure representing a single [BannerInstructions]
 * @property primary PrimaryManeuver represents [BannerInstructions.primary]
 * @property totalManeuverDistance TotalManeuverDistance represents [BannerInstructions.distanceAlongGeometry]
 * @property secondary SecondaryManeuver? represents [BannerInstructions.secondary]
 * @property sub SubManeuver? represents [BannerInstructions.sub] with type text
 * @property laneGuidance Lane? represents [BannerInstructions.sub] with type lane
 */
data class Maneuver(
    val primary: PrimaryManeuver,
    val totalManeuverDistance: TotalManeuverDistance,
    val secondary: SecondaryManeuver?,
    val sub: SubManeuver?,
    val laneGuidance: Lane?
) {
    /**
     * Indicates whether some other object is "equal to" this one.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Maneuver

        if (primary != other.primary) return false
        if (secondary != other.secondary) return false
        if (sub != other.sub) return false
        if (laneGuidance != other.laneGuidance) return false

        return true
    }

    /**
     * Returns a hash code value for the object.
     */
    override fun hashCode(): Int {
        var result = primary.hashCode()
        result = 31 * result + totalManeuverDistance.hashCode()
        result = 31 * result + (secondary?.hashCode() ?: 0)
        result = 31 * result + (sub?.hashCode() ?: 0)
        result = 31 * result + (laneGuidance?.hashCode() ?: 0)
        return result
    }
}
