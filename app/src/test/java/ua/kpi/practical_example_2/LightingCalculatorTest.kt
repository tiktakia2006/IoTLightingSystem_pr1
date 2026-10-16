package ua.kpi.practical_example_2

import org.junit.Assert.assertEquals
import org.junit.Test
import ua.kpi.practical_example_2.data.LightingCalculator
import ua.kpi.practical_example_2.data.toDoubleOrZero

/** Перевірка бізнес-логіки системи освітлення. */
class LightingCalculatorTest {
    private val calc = LightingCalculator()

    @Test
    fun currentPower_halfBrightness() {
        assertEquals(30.0, calc.currentPower(60.0, 50.0), 0.001)
    }

    @Test
    fun currentPower_brightnessIsClampedTo100() {
        assertEquals(60.0, calc.currentPower(60.0, 250.0), 0.001)
    }

    @Test
    fun dailyEnergy_tenWattsForTenHours() {
        // 10 Вт × 10 год = 100 Вт·год = 0.1 кВт·год
        assertEquals(0.1, calc.dailyEnergyKwh(10.0, 10.0), 0.0001)
    }

    @Test
    fun savings_whenPeopleArePresentHalfTheTime() {
        assertEquals(0.5, calc.savingsWithMotionSensor(1.0, 50.0), 0.0001)
    }

    @Test
    fun cost_isEnergyTimesTariff() {
        assertEquals(8.0, calc.cost(2.0, 4.0), 0.0001)
    }

    @Test
    fun toDoubleOrZero_acceptsCommaAndGarbage() {
        assertEquals(4.5, "4,5".toDoubleOrZero(), 0.0001)
        assertEquals(0.0, "abc".toDoubleOrZero(), 0.0001)
        assertEquals(0.0, "".toDoubleOrZero(), 0.0001)
    }
}
