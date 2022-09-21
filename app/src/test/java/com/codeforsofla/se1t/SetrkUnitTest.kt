package com.codeforsofla.se1t

import com.codeforsofla.se1t.shipmentFormFeature.toTimeString
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SetrkUnitTest {
    @Test
    fun doesDateFormatAM() {
        val expected = "3:15 AM"

        val hours = 3
        val minutes = 15

        val minsInLong = (hours * 60 + minutes) * 1000.toLong()

        val result = minsInLong.toTimeString()

        assertEquals(expected, result)
    }

    @Test
    fun doesDateFormatPM() {
        val expected = "3:15 PM"

        val hours = 15
        val minutes = 15

        val minsInLong = (hours * 60 + minutes) * 1000.toLong()

        val result = minsInLong.toTimeString()

        assertEquals(expected, result)
    }
}