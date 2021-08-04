package moe.nikolay.nwcode.helper.Exceptions

import moe.nikolay.nwcode.repository.api.pixaby.APIExceptions
import java.io.IOException

open class ServerException(val code: Int, message: String? = null) : IOException(message) {
    override fun getLocalizedMessage(): String? {
        return "status code is $code. "+getErrorType()?.message
    }

    companion object {
        private val codeToErrorTypeMap = mapOf(
            400 to APIExceptions.BAD_REQUEST,
            401 to APIExceptions.UNAUTHORIZED,
            403 to APIExceptions.FORBIDDEN,
            404 to APIExceptions.NOT_FOUND,
            405 to APIExceptions.METHOD_NOT_ALLOWED,
            409 to APIExceptions.VALIDATION_ERROR,
            422 to APIExceptions.VALIDATION_ERROR,
            500 to APIExceptions.SERVER_ERROR,
            502 to APIExceptions.BAD_GATEWAY
        )

        fun getErrorTypeByCode(code: Int) = codeToErrorTypeMap[code]
    }

    fun getErrorType(): APIExceptions? = codeToErrorTypeMap[code]

}