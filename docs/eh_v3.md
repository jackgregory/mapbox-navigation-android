# EH_v3 integration document

To start listening EHorizon updates `EHorizonObserver` should be registered with

```kotlin
mapboxNavigation.registerEHorizonObserver(eHorizonObserver)
```

It provides the next callbacks:

```kotlin
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
```

Anytime you need edge's shape or metadata you can get it with `EHorizonGraphAccessor`.
It's available with
```kotlin
mapboxNavigation.getEHorizonGraphAccessor()
```

```kotlin
interface EHorizonGraphAccessor {
    /**
     * Gets the shape of the EHorizon Edge
     * @param edgeId
     *
     * @return list of Points representing edge shape
     */
    fun getEdgeShape(edgeId: Long): List<Point>?

    /**
     * Gets the metadata of the EHorizon Edge
     * @param edgeId
     *
     * @return EHorizonEdgeMetadata
     */
    fun getEdgeMetadata(edgeId: Long): EHorizonEdgeMetadata?
}
```

Anytime you need road object's metadata or location you can get it with `EHorizonObjectsStore`
It's available with
```kotlin
mapboxNavigation.getEHorizonObjectsStore()
```

```kotlin

```