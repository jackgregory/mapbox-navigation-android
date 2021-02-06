package com.mapbox.navigation.instrumentation_tests.ui.routeline

import android.content.Context
import androidx.test.espresso.Espresso
import com.mapbox.core.constants.Constants
import com.mapbox.geojson.LineString
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.style.sources.generated.GeoJsonSource
import com.mapbox.maps.extension.style.sources.getSourceAs
import com.mapbox.navigation.base.trip.model.RouteProgress
import com.mapbox.navigation.core.trip.session.RouteProgressObserver
import com.mapbox.navigation.instrumentation_tests.ui.SimpleMapViewNavigationTest
import com.mapbox.navigation.instrumentation_tests.utils.routes.MockRoute
import com.mapbox.navigation.instrumentation_tests.utils.routes.MockRoutesProvider
import com.mapbox.navigation.instrumentation_tests.utils.runOnMainSync
import com.mapbox.navigation.ui.base.internal.route.RouteConstants
import com.mapbox.navigation.ui.maps.route.arrow.api.MapboxRouteArrowApi
import com.mapbox.navigation.ui.maps.route.arrow.api.MapboxRouteArrowView
import com.mapbox.navigation.ui.maps.route.arrow.model.RouteArrowOptions
import com.mapbox.turf.TurfMeasurement
import com.mapbox.turf.TurfMisc
import org.junit.Assert.assertEquals

import org.junit.Test

class RouteLineArrowSyncTest : SimpleMapViewNavigationTest() {

    protected lateinit var routeArrowApi: MapboxRouteArrowApi
    protected lateinit var routeArrowView: MapboxRouteArrowView

    override fun getRoute(context: Context): MockRoute {
        return MockRoutesProvider.simpleRoute(context)
    }

    @Test
    fun routeLineAndArrowHaveSamePoints() {
        routeArrowApi = MapboxRouteArrowApi()
        routeArrowView = MapboxRouteArrowView(RouteArrowOptions.Builder(activity).build())

        addLocationPuck()
        addRouteLine()
        addNavigationCamera()

        runOnMainSync {
            mapboxNavigation.registerRouteProgressObserver(object : RouteProgressObserver {
                override fun onRouteProgressChanged(routeProgress: RouteProgress) {
                    // only need one route progress for this test
                    mapboxNavigation.unregisterRouteProgressObserver(this)

                    val state = routeArrowApi.updateUpcomingManeuverArrow(routeProgress)
                    routeArrowView.render(activity.mapboxMap.getStyle()!!, state)


                    val source = activity.mapboxMap.getStyle()!!.getSourceAs<GeoJsonSource>(RouteConstants.ARROW_SHAFT_SOURCE_ID).data
                    //these should come from the arrow shaft source
                    val maneuverPoints = listOf(
                        Point.fromLngLat(-122.533043, 37.963317),
                        Point.fromLngLat(-122.533275, 37.963604),
                        Point.fromLngLat(-122.53337, 37.963667),
                        Point.fromLngLat(-122.53337, 37.963667),
                        Point.fromLngLat(-122.533471, 37.963777),
                        Point.fromLngLat(-122.533511, 37.963866),
                        Point.fromLngLat(-122.53350921756403, 37.96390546828733)
                    )

                    val routeCoords = LineString.fromPolyline(
                        routeProgress.route.geometry()!!,
                        Constants.PRECISION_6
                    ).coordinates()

                    assertEquals(7, maneuverPoints.size)
                    assertEquals(0.0, getDistanceFromNearestPointOnLine(maneuverPoints[0], routeCoords), 0.1)
                    assertEquals(0.0, getDistanceFromNearestPointOnLine(maneuverPoints[1], routeCoords), 0.1)
                    assertEquals(0.0, getDistanceFromNearestPointOnLine(maneuverPoints[2], routeCoords), 0.1)
                    assertEquals(0.0, getDistanceFromNearestPointOnLine(maneuverPoints[3], routeCoords), 0.1)
                    assertEquals(0.0, getDistanceFromNearestPointOnLine(maneuverPoints[4], routeCoords), 0.1)
                    assertEquals(0.0, getDistanceFromNearestPointOnLine(maneuverPoints[5], routeCoords), 0.1)
                    assertEquals(0.0, getDistanceFromNearestPointOnLine(maneuverPoints[6], routeCoords), 0.1)
                }
            })
        }

        Espresso.onIdle()
    }

    private fun getDistanceFromNearestPointOnLine(point: Point, lineCoordinates: List<Point>): Double {
        val nearestPoint = TurfMisc.nearestPointOnLine(point, lineCoordinates).geometry() as Point
        return TurfMeasurement.distance(nearestPoint, point)
    }
}
