var userLimit = 80_000_00
fun main() {
    val commission = moneyTransferCommission(25_000_00, "Mastercard", userLimit)
    when (commission) {
        -10 -> println("Превышен месячный лимит")
        -20 -> println("Превышен дневной лимит")
        else -> println("Комиссия за перевод составит ${commission / 100} рублей")
    }
}

fun moneyTransferCommission(transferAmount: Int, account: String = "Vk pay", limit: Int = 0): Int {
    when {
        limit > 600_000_00 -> return -10
        limit > 150_000_00 -> return -20
    }
    if (account == "Vk pay") {
        when {
            limit > 40_000_00 -> return -10
            limit > 15_000_00 -> return -20
        }
    }
    val comission = accountType(account, limit)
    var result = (transferAmount.toDouble() * comission).toInt()
    if (account == "Mastercard" || account == "Maestro")
        if (comission > 0)
            result += 2000

    userLimit += transferAmount
    return result
}

fun accountType(account: String, limit: Int) = when (account) {
    "Mastercard", "Maestro" -> if (limit < 75_000_00) 0.0 else 0.06
    "Visa", "Мир" -> 0.075
    else -> 0.0
}