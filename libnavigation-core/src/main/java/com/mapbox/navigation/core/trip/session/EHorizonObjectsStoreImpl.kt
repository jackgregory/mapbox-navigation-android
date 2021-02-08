package com.mapbox.navigation.core.trip.session

import com.mapbox.navigation.core.trip.model.eh.EHorizonObjectEdgeLocation
import com.mapbox.navigation.core.trip.model.eh.EHorizonObjectLocation
import com.mapbox.navigation.core.trip.model.eh.EHorizonObjectMetadata
import com.mapbox.navigation.core.trip.model.eh.mapToEHorizonObjectEdgeLocation
import com.mapbox.navigation.core.trip.model.eh.mapToEHorizonObjectLocation
import com.mapbox.navigation.core.trip.model.eh.mapToEHorizonObjectProvider
import com.mapbox.navigation.core.trip.model.eh.mapToEHorizonObjectType
import com.mapbox.navigation.navigator.internal.MapboxNativeNavigator

class EHorizonObjectsStoreImpl(
    private val navigator: MapboxNativeNavigator,
) : EHorizonObjectsStore {

    override fun getRoadObjectsOnTheEdge(edgeId: Long): Map<String, EHorizonObjectEdgeLocation> {
        val roadObjects = mutableMapOf<String, EHorizonObjectEdgeLocation>()
        navigator.roadObjectsStore?.get(edgeId)?.forEach { (objectId, objectEdgeLocation) ->
            roadObjects[objectId] = objectEdgeLocation.mapToEHorizonObjectEdgeLocation()
        }

        return roadObjects
    }

    override fun getRoadObjectMetadata(roadObjectId: String): EHorizonObjectMetadata? {
        return navigator.roadObjectsStore?.getRoadObjectMetadata(roadObjectId)?.let {
            EHorizonObjectMetadata(
                it.type.mapToEHorizonObjectType(),
                it.provider.mapToEHorizonObjectProvider(),
                navigator.navigatorMapper.toIncidentInfo(it.incident)
            )
        }
    }

    override fun getRoadObjectLocation(roadObjectId: String): EHorizonObjectLocation? {
        return navigator.roadObjectsStore?.getRoadObjectLocation(roadObjectId)
            ?.mapToEHorizonObjectLocation()
    }

    override fun getRoadObjectIdsByEdgeIds(edgeIds: List<Long>): List<String> {
        return navigator.roadObjectsStore?.getRoadObjectIdsByEdgeIds(edgeIds) ?: emptyList()
    }
}
