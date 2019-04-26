package KTDevelop.Startup

import KTDevelop.Sda.StartupSettings

fun main(args: Array<String>) = Run(args)

private fun Run(args: Array<String>) {
    var noLogo = false
    setCommandLineArgs(args)
    if (parameterList.contains("nologo")) {
        noLogo = true
    }

    if (!checkEnvironment()){
        return
    }

    if(!noLogo){
        showSplashScreen()
    }

    try {
        runApplication()
    }finally {

    }
}

fun checkEnvironment(): Boolean {
    //Check installed requirements, existing folders, ...
    return true
}

fun runApplication(){
    // TODO LoggingService.Info("Starting KTDevelop ...")
    try {
        val startup = StartupSettings()
        startup.applicationRootPath = ""
    }finally {
        // TODO LoggingService.Info("Leaving runApplication()")
    }
}
