package ktDevelop.startup

import ktDevelop.sda.KTDevelopHost
import ktDevelop.sda.StartupSettings
import java.nio.file.Paths

fun main(args: Array<String>) = Run(args)

private fun Run(args: Array<String>) {
    var noLogo = false
    setCommandLineArgs(args)
    if (parameterList.contains("nologo")) {
        noLogo = true
    }

    if (!checkEnvironment()) {
        return
    }

    if (!noLogo) {
        showSplashScreen()
    }

    try {
        runApplication()
    } finally {

    }
}

fun checkEnvironment(): Boolean {
    //Check installed requirements, existing folders, ...
    return true
}

fun runApplication() {
    // TODO LoggingService.Info("Starting ktDevelop ...")
    try {
        val startup = StartupSettings()
        startup.applicationRootPath = Paths.get("").toAbsolutePath().toString()
        startup.allowUserAddIns = true

        //val configDirectory = ConfigurationManager.appSettings["settingsPath"]
        startup.configDirectory = pathCombine(getEnvironmentApplicationData(), "Roaming", "KTDevelop")
        startup.domPersistencePath = pathCombine(getEnvironmentApplicationData(), "Local", "Temp", "KTDevelop")
        startup.addAddInsFromDirectory(pathCombine(startup.applicationRootPath, "AddIns"))

        for(parameter in parameterList){
            if(parameter.startsWith("addindir:")){
                startup.addAddInsFromDirectory(parameter.substring(9))
            }
        }

        val host = KTDevelopHost("idk current domain", startup)
    } finally {
        // TODO LoggingService.Info("Leaving runApplication()")
    }
}

fun pathCombine(first: String, vararg more: String) = Paths.get(first, *more).toString()
fun getEnvironmentApplicationData() = pathCombine(System.getProperty("user.home"), "AppData")
