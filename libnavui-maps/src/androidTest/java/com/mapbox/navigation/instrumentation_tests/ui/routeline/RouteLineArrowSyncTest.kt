package com.mapbox.navigation.instrumentation_tests.ui.routeline

import androidx.test.espresso.Espresso
import com.mapbox.api.directions.v5.models.RouteOptions
import com.mapbox.navigation.base.internal.extensions.applyDefaultParams
import com.mapbox.navigation.core.MapboxNavigation
import com.mapbox.navigation.core.MapboxNavigationProvider
import com.mapbox.navigation.instrumentation_tests.activity.EmptyTestActivity
import com.mapbox.navigation.instrumentation_tests.routesRequestCallback
import com.mapbox.navigation.instrumentation_tests.utils.MapboxNavigationRule
import com.mapbox.navigation.instrumentation_tests.utils.location.MockLocationReplayerRule
import com.mapbox.navigation.instrumentation_tests.utils.routes.MockRoutesProvider
import com.mapbox.navigation.instrumentation_tests.utils.runOnMainSync
import com.mapbox.navigation.testing.ui.BaseTest
import com.mapbox.navigation.testing.ui.utils.getMapboxAccessTokenFromResources
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RouteLineArrowSyncTest : BaseTest<EmptyTestActivity>(EmptyTestActivity::class.java) {

    @get:Rule
    val mapboxNavigationRule = MapboxNavigationRule()

    @get:Rule
    val mockLocationReplayerRule = MockLocationReplayerRule(mockLocationUpdatesRule)

    private lateinit var mapboxNavigation: MapboxNavigation

    @Before
    fun setup() {
        Espresso.onIdle()

        val options = MapboxNavigation.defaultNavigationOptionsBuilder(
            activity,
            getMapboxAccessTokenFromResources(activity)
        ).build()
        mapboxNavigation = MapboxNavigationProvider.create(options)
    }

    @Test
    fun routeLineAndArrowHaveSamePoints() {
        val mockRoute = MockRoutesProvider.simpleRoute(activity)
        mockWebServerRule.requestHandlers.addAll(mockRoute.mockRequestHandlers)

        runOnMainSync {
            mockLocationUpdatesRule.pushLocationUpdate {
                latitude = mockRoute.routeWaypoints.first().latitude()
                longitude = mockRoute.routeWaypoints.first().longitude()
            }
        }
        runOnMainSync {
            mapboxNavigation.startTripSession()
            mapboxNavigation.requestRoutes(
                RouteOptions.builder().applyDefaultParams()
                    .baseUrl(mockWebServerRule.baseUrl)
                    .accessToken(getMapboxAccessTokenFromResources(activity))
                    .coordinates(mockRoute.routeWaypoints).build(),
                routesRequestCallback(
                    onRoutesReady = { mockLocationReplayerRule.playRoute(it[0]) }
                )
            )
        }


        mockRoute.routeResponseJson
    }
}
