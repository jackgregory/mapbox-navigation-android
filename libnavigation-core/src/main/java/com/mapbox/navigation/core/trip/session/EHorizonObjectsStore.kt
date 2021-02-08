package com.mapbox.navigation.core.trip.session

import com.mapbox.navigation.core.trip.model.eh.EHorizonObjectEdgeLocation
import com.mapbox.navigation.core.trip.model.eh.EHorizonObjectLocation
import com.mapbox.navigation.core.trip.model.eh.EHorizonObjectMetadata

interface EHorizonObjectsStore {
    fun getRoadObjectsOnTheEdge(edgeId: Long): Map<String, EHorizonObjectEdgeLocation>

    fun getRoadObjectMetadata(roadObjectId: String): EHorizonObjectMetadata?

    fun getRoadObjectLocation(roadObjectId: String): EHorizonObjectLocation?

    /**
     * Gets all road objects on all edges
     * @param edgeId
     *
     * @return list of Points representing edge shape
     */
    fun getRoadObjectIdsByEdgeIds(edgeIds: List<Long>): List<String>
}
