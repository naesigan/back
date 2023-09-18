package good.day.naesigan.servertimer.service

import good.day.naesigan.common.CustomException
import good.day.naesigan.servertimer.mapper.DomainMapper
import good.day.naesigan.servertimer.vo.DomainVo
import org.springframework.stereotype.Service
import org.springframework.jdbc.core.JdbcTemplate
import kotlin.jvm.Throws

@Service
class ServertimerService(private val domainMapper: DomainMapper, val db: JdbcTemplate) {
    fun getDomains(): List<DomainVo> {
        val result = domainMapper.getDomains()
        if (result.isEmpty()) {
            throw CustomException("1004");
        }

        return result
    }
    fun setDomain(domain: DomainVo): Int {
        val domains = domainMapper.getDomains()

        for(data in domains) {
            if(data.name.equals(domain.name)) {
                println(data)
                throw CustomException("1000")
            }
        }

        val result = domainMapper.setDomain(domain)
        return result
    }
}