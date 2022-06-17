var userLimit = 80_000_00
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