package ua.kpi.practical_example_2.data

/**
 * Бізнес-логіка системи освітлення з IoT.
 * Не залежить від Compose, тому її легко тестувати й повторно використовувати.
 */
class LightingCalculator {

    /**
     * Фактична потужність лампи при заданій яскравості.
     * P = P_ном × (яскравість / 100)
     *
     * @param nominalPowerW номінальна потужність лампи, Вт
     * @param brightnessPercent яскравість, % (0–100)
     */
    fun currentPower(nominalPowerW: Double, brightnessPercent: Double): Double {
        val brightness = brightnessPercent.coerceIn(0.0, 100.0)
        return nominalPowerW * brightness / 100.0
    }

    /**
     * Споживання енергії за добу.
     * E = P × t / 1000 (кВт·год)
     *
     * @param powerW потужність, Вт
     * @param hoursPerDay години роботи на добу (0–24)
     */
    fun dailyEnergyKwh(powerW: Double, hoursPerDay: Double): Double {
        val hours = hoursPerDay.coerceIn(0.0, 24.0)
        return powerW * hours / 1000.0
    }

    /**
     * Економія енергії від датчика руху: лампа світить лише тоді,
     * коли у приміщенні є люди.
     *
     * @param energyKwh споживання без датчика, кВт·год
     * @param occupancyPercent частка часу, коли у приміщенні є люди, % (0–100)
     * @return скільки кВт·год економиться
     */
    fun savingsWithMotionSensor(energyKwh: Double, occupancyPercent: Double): Double {
        val occupancy = occupancyPercent.coerceIn(0.0, 100.0)
        return energyKwh * (1.0 - occupancy / 100.0)
    }

    /**
     * Вартість електроенергії.
     *
     * @param energyKwh споживання, кВт·год
     * @param tariffPerKwh тариф, грн за кВт·год
     */
    fun cost(energyKwh: Double, tariffPerKwh: Double): Double = energyKwh * tariffPerKwh
}

/** Перетворює рядок з поля вводу на число; кома теж допускається («4,5»). */
fun String.toDoubleOrZero(): Double = this.replace(',', '.').trim().toDoubleOrNull() ?: 0.0
