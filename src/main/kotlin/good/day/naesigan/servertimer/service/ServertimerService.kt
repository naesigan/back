package good.day.naesigan.servertimer.service

import good.day.naesigan.servertimer.mapper.DomainMapper
import good.day.naesigan.servertimer.vo.DomainVo
import org.springframework.stereotype.Service
import org.springframework.jdbc.core.JdbcTemplate

@Service
class ServertimerService(private val domainMapper: DomainMapper, val db: JdbcTemplate) {
    fun getDomains(): List<DomainVo> {
        val result = domainMapper.getDomains()
        return result
    }
    fun setDomain(domain: DomainVo): Int {
        val result = domainMapper.setDomain(domain)
        return result
    }
}