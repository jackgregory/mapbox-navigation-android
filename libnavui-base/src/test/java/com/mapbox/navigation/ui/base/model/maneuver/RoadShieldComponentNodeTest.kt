package com.mapbox.navigation.ui.base.model.maneuver

import com.mapbox.navigation.testing.BuilderTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.reflect.KClass

@RunWith(RobolectricTestRunner::class)
class RoadShieldComponentNodeTest : BuilderTest<RoadShieldComponentNode,
    RoadShieldComponentNode.Builder>() {

    override fun getImplementationClass(): KClass<RoadShieldComponentNode> =
        RoadShieldComponentNode::class

    override fun getFilledUpBuilder(): RoadShieldComponentNode.Builder {
        return RoadShieldComponentNode.Builder()
            .text("exit-number")
            .shieldIcon(byteArrayOf())
    }

    @Test
    override fun trigger() {
        // see comments
    }
}
