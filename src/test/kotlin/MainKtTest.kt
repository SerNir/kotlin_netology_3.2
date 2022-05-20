import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun moneyTransferCommissionFromVisa() {
        val transferAmount = 25_000_00
        val account = "Visa"
        val limit = 40_000_00

        val result = moneyTransferCommission(transferAmount, account, limit)
        assertEquals(187500, result)
    }

    @Test
    fun moneyTransferCommissionFromMastercardOverLimit() {
        val transferAmount = 25_000_00
        val account = "Mastercard"
        val limit = 80_000_00

        val result = moneyTransferCommission(transferAmount, account, limit)
        assertEquals(152000, result)
    }

    @Test
    fun moneyTransferCommissionFromMastercardNoLimit() {
        val transferAmount = 25_000_00
        val account = "Mastercard"
        val limit = 20_000_00

        val result = moneyTransferCommission(transferAmount, account, limit)
        assertEquals(0, result)
    }

    @Test
    fun moneyTransferCommissionFromMastercardLimitDay() {
        val transferAmount = 25_000_00
        val account = "Mastercard"
        val limit = 151_000_00

        val result = moneyTransferCommission(transferAmount, account, limit)
        assertEquals(-20, result)
    }

    @Test
    fun moneyTransferCommissionFromMastercardLimitMonth() {
        val transferAmount = 25_000_00
        val account = "Mastercard"
        val limit = 600_001_00

        val result = moneyTransferCommission(transferAmount, account, limit)
        assertEquals(-10, result)
    }

    @Test
    fun moneyTransferCommissionFromVkPay() {
        val transferAmount = 25_000_00
        val result = moneyTransferCommission(transferAmount)
        assertEquals(0, result)
    }

    @Test
    fun moneyTransferCommissionFromVkPayLimitDay() {
        val transferAmount = 25_000_00
        val account = "Vk pay"
        val limit = 20_000_00

        val result = moneyTransferCommission(transferAmount, account, limit)
        assertEquals(-20, result)
    }

    @Test
    fun moneyTransferCommissionFromVkPayLimitMonth() {
        val transferAmount = 25_000_00
        val account = "Vk pay"
        val limit = 60_000_00

        val result = moneyTransferCommission(transferAmount, account, limit)
        assertEquals(-10, result)
    }

    @Test
    fun moneyTransferCommissionFromOverLimitMount() {
        val transferAmount = 25_000_00
        val account = "Neo"
        val limit = 1__000_00

        val result = moneyTransferCommission(transferAmount, account, limit)
        assertEquals(0, result)
    }

    @Test
    fun accountTypeMastercard() {
        val account = "Maestro"
        val limit = 0

        val result = accountType(account, limit)

        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun accountTypeMaestro() {
        val account = "Maestro"
        val limit = 80_000_00

        val result = accountType(account, limit)

        assertEquals(0.06, result, 0.00)
    }
}