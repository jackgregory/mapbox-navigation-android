package com.mapbox.navigation.core.trip.session

import com.mapbox.navigation.core.trip.model.eh.EHorizonObjectDistanceInfo
import com.mapbox.navigation.core.trip.model.eh.EHorizonObjectEnterExitInfo
import com.mapbox.navigation.core.trip.model.eh.EHorizonPosition

interface EHorizonObserver {

    /**
     * This callback is fired whenever the location on the EHorizon changes.
     * @param position contains graph position, type and eHorizon object
     * @param distances is a map of eHorizon objects ids and their distances
     *
     */
    fun onPositionUpdated(
        position: EHorizonPosition,
        distances: Map<String, EHorizonObjectDistanceInfo>
    )

    /**
     * This callback is fired whenever road object is entered
     * @param objectEnterExitInfo contains info related to the object
     */
    fun onRoadObjectEnter(objectEnterExitInfo: EHorizonObjectEnterExitInfo)

    /**
     * This callback is fired whenever road object is exited
     * @param objectEnterExitInfo contains info related to the object
     */
    fun onRoadObjectExit(objectEnterExitInfo: EHorizonObjectEnterExitInfo)

    /**
     * This callback is fired whenever road object is added
     * @param roadObjectId id of the object
     */
    fun onRoadObjectAdded(roadObjectId: String)

    /**
     * This callback is fired whenever road object is updated
     * @param roadObjectId id of the object
     */
    fun onRoadObjectUpdated(roadObjectId: String)

    /**
     * This callback is fired whenever road object is removed
     * @param roadObjectId id of the object
     */
    fun onRoadObjectRemoved(roadObjectId: String)
}
