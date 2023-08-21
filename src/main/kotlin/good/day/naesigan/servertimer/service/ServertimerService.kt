package good.day.naesigan.servertimer.service

import good.day.naesigan.servertimer.mapper.DomainMapper
import good.day.naesigan.servertimer.vo.DomainVO
import org.springframework.stereotype.Service
import org.springframework.jdbc.core.JdbcTemplate

@Service
class ServertimerService(private val domainMapper: DomainMapper, val db: JdbcTemplate) {
    fun getDomains(): List<DomainVO> {
        domainMapper.getDomains()
        return domainMapper.getDomains()
    }
    fun setDomain(domain: DomainVO): Int {
        val result = domainMapper.setDomain(domain)
        return result
    }
}