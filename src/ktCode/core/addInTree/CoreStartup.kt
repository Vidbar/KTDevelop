package ktCode.core.addInTree

import dotNet.ICommand
import dotNet.IServiceContainer
import dotNet.typeOf
import ktCode.core.services.ApplicationStateInfoService
import ktCode.core.services.getRequiredService
import ktCode.core.services.propertyService.IPropertyService
import ktCode.core.services.resourceService.IResourceService
import ktCode.core.services.resourceService.ResourceServiceImpl
import ktCode.core.services.serviceProvider
import ktDevelop.startup.pathCombine
import java.io.File

class CoreStartup(val applicationName: String) {

    private val addInFiles = mutableListOf<String>()
    private val disableAddIns = mutableListOf<String>()
    private lateinit var addInTree: AddInTreeImpl

    fun startCoreServices(propertyService: IPropertyService) {
        val container = getRequiredService<IServiceContainer>()
        val applicationStateInfoService = ApplicationStateInfoService()
        addInTree = AddInTreeImpl(applicationStateInfoService)

        container.addService(typeOf(IPropertyService::class), propertyService)
        container.addService(
            typeOf(IResourceService::class), ResourceServiceImpl(
                pathCombine(propertyService.dataDirectory.normalizedPath, "resources"),
                propertyService
            )
        )
        container.addService(typeOf(IAddInTree::class), addInTree)
        container.addService(typeOf(ApplicationStateInfoService::class), applicationStateInfoService)
        //TODO StringParser.RegisterStringTagProvider(new AppNameProvider { appName = applicationName });
    }

    fun addAddInFile(addInFile: String) {
        addInFiles.add(addInFile)
    }

    fun addAddInsFromDirectory(addInDir: String) {
        addInFiles.addAll(File(addInDir).walkTopDown().filter { it.extension == ".addin"}.map { it.absolutePath })
    }

    fun runInitialization() {
        addInTree.load(addInFiles, disableAddIns)

        val container = serviceProvider.getService<IServiceContainer>(typeOf(IServiceContainer::class))

        //TODO LoggingService.Info("Running autostart commands...");
        for (command in addInTree.buildItems<ICommand>("/KTDevelop/Autostart", null, false)){
            try {
                command.execute(null)
            }catch (ex: Exception){
                //ServiceSingleton.GetRequiredService<IMessageService>().ShowException(ex);
            }
        }
    }

}
