package good.day.naesigan.servertimer.mapper

import good.day.naesigan.servertimer.vo.DomainVO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface DomainMapper {
    fun getDomains(): List<DomainVO>
    fun setDomain(domainVO: DomainVO): Int
}