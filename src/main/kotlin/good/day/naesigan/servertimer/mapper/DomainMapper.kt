package good.day.naesigan.servertimer.mapper

import good.day.naesigan.servertimer.vo.DomainVo
import org.apache.ibatis.annotations.Mapper

@Mapper
interface DomainMapper {
    fun getDomains(): List<DomainVo>
    fun setDomain(domainVO: DomainVo): Int
}