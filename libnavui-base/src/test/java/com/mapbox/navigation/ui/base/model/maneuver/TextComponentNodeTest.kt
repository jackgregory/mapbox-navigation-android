package com.mapbox.navigation.ui.base.model.maneuver

import com.mapbox.navigation.testing.BuilderTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.reflect.KClass

@RunWith(RobolectricTestRunner::class)
class TextComponentNodeTest : BuilderTest<TextComponentNode,
    TextComponentNode.Builder>() {

    override fun getImplementationClass(): KClass<TextComponentNode> =
        TextComponentNode::class

    override fun getFilledUpBuilder(): TextComponentNode.Builder {
        return TextComponentNode.Builder()
            .text("exit-number")
            .abbr("mapbox")
            .abbrPriority(4)
    }

    @Test
    override fun trigger() {
        // see comments
    }
}
