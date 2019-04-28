package ktCode.core.addInTree

import dotNet.Version
import ktCode.core.addInTree.addIn.AddIn
import ktCode.core.addInTree.addIn.LoadAddIn
import ktCode.core.services.ApplicationStateInfoService

class AddInTreeImpl(applicationStateInfoService: ApplicationStateInfoService) : IAddInTree {
    /*
    TODO lot of code ...
    doozers.TryAdd("Class", new ClassDoozer());
    doozers.TryAdd("Static", new StaticDoozer());
    doozers.TryAdd("FileFilter", new FileFilterDoozer());
    */

    fun load(addInFiles: MutableList<String>, disableAddIns: MutableList<String>) {
        val list = mutableListOf<AddIn>()
        val dict = mutableMapOf<String, Version>()
        val addInDict = mutableMapOf<String, AddIn>()
        //NameTable

        for (fileName in addInFiles){
            var addIn =
                return try {
                    LoadAddIn(this, fileName, "nameTable")
                }catch (exception: Exception){
                    AddIn(this)
                }
        }
    }
}
