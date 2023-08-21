package good.day.naesigan.servertimer.vo

import java.sql.Timestamp

data class DomainVO(
    val id: Int,
    val name: String,
    val date: Timestamp?,
)