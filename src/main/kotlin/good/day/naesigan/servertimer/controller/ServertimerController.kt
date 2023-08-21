package good.day.naesigan.servertimer.controller

import good.day.naesigan.servertimer.vo.DomainVO
import good.day.naesigan.servertimer.service.ServertimerService
import good.day.naesigan.vo.ResponseVo
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
    fun index(): ResponseEntity<ResponseVo> {
        return ResponseEntity(ResponseVo("0000", servertimerService.getDomains()), HttpStatus.OK)
    }

    @PostMapping("/domain")
    fun post(@RequestBody domain: DomainVO): ResponseEntity<Any> {
        servertimerService.setDomain(domain)
        val responseVo = ResponseVo("0000", servertimerService.setDomain(domain))
        return ResponseEntity(responseVo, HttpStatus.OK)
    }
}