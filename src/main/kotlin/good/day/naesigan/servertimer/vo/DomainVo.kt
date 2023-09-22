package good.day.naesigan.servertimer.vo

import java.sql.Timestamp

data class DomainVo(
    val name: String,
    val date: Timestamp?,
    val deleted: Timestamp?,
    val count: Int,
)