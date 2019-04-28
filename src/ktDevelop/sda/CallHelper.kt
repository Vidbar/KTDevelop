package ktDevelop.sda

import dotNet.typeOf
import ktCode.core.addInTree.CoreStartup
import ktCode.core.services.fileUtility.applicationRootPath
import ktCode.core.services.fileUtility.createDirectoryName
import ktCode.core.services.loggingService.ILoggingService
import ktCode.core.services.messageService.IMessageService
import ktCode.core.services.serviceProvider
import ktCode.ktDevelop.util.KTDevelopServiceContainer
import ktDevelop.logging.Log4netLoggingService
import ktDevelop.logging.SDMessageService
import ktDevelop.services.PropertyService
import ktDevelop.startup.pathCombine

class CallHelper {
    private lateinit var callback: CallbackHelper

    fun initKTDevelopCore(callback: CallbackHelper, properties: StartupSettings) {
        val container = KTDevelopServiceContainer()
        container.addFallbackProvider(serviceProvider)
        container.addService(typeOf(IMessageService::class), SDMessageService())
        container.addService(typeOf(ILoggingService::class), Log4netLoggingService())
        serviceProvider = container

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
        startup.startCoreServices(propertyService)
        //TODO a lot of code ...
        for (file in properties.addInFiles) startup.addAddInFile(file)
        for (dir in properties.addInDirectories) startup.addAddInsFromDirectory(dir)

        //TODO a lot of code ...
        //LoggingService.Info("Loading AddInTree...");
        startup.runInitialization()
    }


}
