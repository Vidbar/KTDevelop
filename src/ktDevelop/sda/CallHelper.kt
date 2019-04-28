package ktDevelop.sda

import ktCode.core.addInTree.CoreStartup
import ktCode.core.services.fallbackServiceProvider
import ktCode.core.services.fileUtility.applicationRootPath
import ktCode.core.services.fileUtility.createDirectoryName
import ktCode.core.services.loggingService.ILoggingService
import ktCode.core.services.messageService.IMessageService
import ktCode.ktDevelop.util.KTDevelopServiceContainer
import ktDevelop.services.PropertyService
import ktDevelop.logging.Log4netLoggingService
import ktDevelop.logging.SDMessageService
import ktDevelop.startup.pathCombine

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
        val dataDirectory: String? = properties.dataDirectory
        val propertiesName = properties.applicationName + "Properties"

        applicationRootPath = properties.applicationRootPath
        val propertyService = PropertyService(
            createDirectoryName(configDirectory),
            createDirectoryName(dataDirectory ?: pathCombine(applicationRootPath, "data")),
            propertiesName
        )
    }


}
