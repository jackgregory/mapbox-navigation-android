package com.mapbox.navigation.ui.base.model.maneuver

import com.mapbox.navigation.testing.BuilderTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.reflect.KClass

@RunWith(RobolectricTestRunner::class)
class ExitComponentNodeTest : BuilderTest<ExitComponentNode,
    ExitComponentNode.Builder>() {

    override fun getImplementationClass(): KClass<ExitComponentNode> =
        ExitComponentNode::class

    override fun getFilledUpBuilder(): ExitComponentNode.Builder {
        return ExitComponentNode.Builder()
            .text("exit")
    }

    @Test
    override fun trigger() {
        // see comments
    }
}
