package good.day.naesigan.common

class CustomException(str: String) : Exception(str) {
    private var resultMessage = str

    fun getResultCode() : String {
        return resultMessage
    }
}