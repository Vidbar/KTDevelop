package ktCode.core.services.messageService

import ktCode.core.services.getRequiredService

private val service
    get() = getRequiredService<IMessageService>()

fun showError(message: String) = service.showError(message)