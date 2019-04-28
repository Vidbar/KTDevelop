package ktDevelop.sda

class StartupSettings {
    val addInFiles = mutableListOf<String>()
    val dataDirectory = ""
    val applicationName = "KTDevelop"
    var domPersistencePath: String = ""
    var configDirectory: String = ""
    var allowUserAddIns: Boolean = false
    var applicationRootPath: String = ""

    val addInDirectories = mutableListOf<String>()

    fun addAddInsFromDirectory(addInDir: String) {
        addInDirectories.add(addInDir)
    }
}
