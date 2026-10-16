package ua.kpi.practical_example_1

import org.junit.Assert.assertEquals
import org.junit.Test

class DisplayForTest {
    @Test
    fun threeDifficultyLevelsExist() {
        assertEquals(3, DisplayFor.values().size)
    }
}
