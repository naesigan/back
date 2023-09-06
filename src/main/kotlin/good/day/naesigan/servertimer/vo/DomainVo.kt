package good.day.naesigan.servertimer.vo

import java.sql.Timestamp

data class DomainVo(
    val id: Int,
    val name: String,
    val date: Timestamp?,
    val deleted: Timestamp?,
)