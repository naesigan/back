package good.day.naesigan.servertimer.service

import good.day.naesigan.common.CustomException
import good.day.naesigan.servertimer.mapper.DomainMapper
import good.day.naesigan.servertimer.vo.DomainVo
import org.springframework.stereotype.Service

@Service
class ServertimerService(private val domainMapper: DomainMapper) {
    fun getDomains(): List<DomainVo> {
        val result = domainMapper.getDomains()
        if (result.isEmpty()) {
            throw CustomException("1004")
        }

        return result
    }
    fun setDomain(domain: DomainVo): Int {
        setDomainLog(domain)
        val domains = domainMapper.getDomains()

        for(data in domains) {
            if(data.name.equals(domain.name)) {
                updateDomain(domain.name)
                throw CustomException("0000")
            }
        }

        val result = domainMapper.setDomain(domain)
        return result
    }

    fun setDomainLog(domain: DomainVo): Int {
        return domainMapper.setDomainLog(domain)
    }

    fun updateDomain(name: String): Int {
        return domainMapper.updateDomain(name)
    }
}