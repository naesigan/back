package good.day.naesigan.common

import good.day.naesigan.common.vo.ResponseVo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CustomControllerAdvice {
    @ExceptionHandler(value = [CustomException::class])
    fun handleException(ex:CustomException): ResponseEntity<ResponseVo> {
        return ResponseEntity(ResponseVo(ex.getResultCode(), null), HttpStatus.FORBIDDEN);
    }
}