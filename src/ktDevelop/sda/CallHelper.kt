package ktDevelop.sda

import ktCode.core.addInTree.CoreStartup
import ktCode.core.service.fallbackServiceProvider
import ktCode.core.service.loggingService.ILoggingService
import ktCode.core.service.messageService.IMessageService
import ktCode.ktDevelop.util.KTDevelopServiceContainer
import ktDevelop.logging.Log4netLoggingService
import ktDevelop.logging.SDMessageService

class CallHelper {
    private lateinit var callback: CallbackHelper

    fun initKTDevelopCore(callback: CallbackHelper, properties: StartupSettings) {
        val container = KTDevelopServiceContainer()
        container.addFallbackProvider(fallbackServiceProvider)
        container.addService(IMessageService::class.qualifiedName!!, SDMessageService())
        container.addService(ILoggingService::class.qualifiedName!!, Log4netLoggingService())
        fallbackServiceProvider = container

        //LoggingService.info("InitKTDevelop...")
        this.callback = callback
        val startup = CoreStartup(properties.applicationName)
        val configDirectory = properties.configDirectory
        val dataDirectory = properties.dataDirectory
        val propertiesName = properties.applicationName + "Properties"
    }
}
