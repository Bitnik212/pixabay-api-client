package moe.nikolay.nwcode.repository.api.pixaby

enum class APIExceptions(val message: String) {
    SERVER_ERROR("Внутрняя ошибка сервер"),
    NOT_FOUND("Не нашел"),
    VALIDATION_ERROR("Ошибка валидации"),
    BAD_REQUEST("Не правильный запрос"),
    UNAUTHORIZED("Ошибка авторизации"),
    FORBIDDEN("Доступ запрещен"),
    METHOD_NOT_ALLOWED("Метод еще не готов"),
    CONFLICT("Конфликт в запросе"),
    BAD_GATEWAY("Что-то с доступом к серверу")
}