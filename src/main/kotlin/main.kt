fun main() {
    val commission = moneyTransferCommission(250000, "Mastercard", 8_500_000)
    println("Комиссия за перевод составит ${commission / 100} рублей")
}

fun moneyTransferCommission(transferAmount: Int, account: String = "Vk pay", limit: Int = 0): Int {
    val comission = accountType(account, limit)
    var result = (transferAmount.toDouble() * comission).toInt()
    if (account == "Mastercard" || account == "Maestro")
        if (comission > 0)
            result += 2000

    return result
}

fun accountType(account: String, limit: Int) = when (account) {
    "Mastercard", "Maestro" -> if (limit < 7_500_000) 0.0 else 0.06
    "Visa", "Мир" -> 0.075
    else -> 0.0
}