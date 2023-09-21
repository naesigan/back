package good.day.naesigan.servertimer.controller

import good.day.naesigan.servertimer.vo.DomainVo
import good.day.naesigan.servertimer.service.ServertimerService
import good.day.naesigan.common.vo.ResponseVo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/servertimer")
class ServertimerController(val servertimerService: ServertimerService) {
    @GetMapping("/domain")
    fun getDomains(): ResponseEntity<ResponseVo> {
        val responseVo = ResponseVo("0000", servertimerService.getDomains())
        return ResponseEntity(responseVo, HttpStatus.OK)
    }

    @PostMapping("/domain")
    fun postDomain(@RequestBody domain: DomainVo): ResponseEntity<Any> {
        val responseVo = ResponseVo("0000", servertimerService.setDomain(domain))
        return ResponseEntity(responseVo, HttpStatus.OK)
    }

    @PostMapping("/domain/log")
    fun postLog(@RequestBody domain: DomainVo): ResponseEntity<Any> {
        val responseVo = ResponseVo("0000", servertimerService.setDomainLog(domain))
        return ResponseEntity(responseVo, HttpStatus.OK)
    }
}