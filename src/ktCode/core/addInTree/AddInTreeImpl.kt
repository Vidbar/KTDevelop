package ktCode.core.addInTree

import dotNet.Version
import ktCode.core.addInTree.addIn.AddIn
import ktCode.core.addInTree.addIn.LoadAddIn
import ktCode.core.services.ApplicationStateInfoService
import ktCode.core.services.messageService.showError

class AddInTreeImpl(applicationStateInfoService: ApplicationStateInfoService) : IAddInTree {
    /*
    TODO lot of code ...
    doozers.TryAdd("Class", new ClassDoozer());
    doozers.TryAdd("Static", new StaticDoozer());
    doozers.TryAdd("FileFilter", new FileFilterDoozer());
    */

    private val rootNode: AddInTreeNode = AddInTreeNode()

    fun load(addInFiles: MutableList<String>, disableAddIns: MutableList<String>) {
        val list = mutableListOf<AddIn>()
        val dict = mutableMapOf<String, Version>()
        val addInDict = mutableMapOf<String, AddIn>()
        //NameTable

        for (fileName in addInFiles){
            var addIn =
                try {
                    LoadAddIn(this, fileName, "nameTable")
                }catch (exception: Exception){
                    AddIn(this).apply {
                        addInFileName = fileName
                    }
                }

            addIn.enabled = true
            if (disableAddIns.any()){
                for (name in addIn.manifest.identities.keys){
                    if (disableAddIns.contains(name)){
                        addIn.enabled = false
                        break
                    }
                }
            }
            if(addIn.enabled){
                for (pair in addIn.manifest.identities){
                    if (dict.containsKey(pair.key)){
                        showError("""Name '${pair.key}' is used by '${addInDict[pair.key]?.addInFileName}' and '$fileName'""")
                        addIn.enabled = false
                        break
                    }else{
                        dict[pair.key] = pair.value
                        addInDict[pair.key] = addIn
                    }
                }
            }
            list.add(addIn)
            //TODO checkdependencies:
            //a lot of code ...
        }
    }

    fun <T> buildItems(path: String, parameter: Any?, throwOnNotFound: Boolean = true): List<T> {
        val node = getTreeNode(path, throwOnNotFound)
        return node?.buildchildItems<T>(parameter) ?: listOf()
    }

    private fun getTreeNode(path: String, throwOnNotFound: Boolean): AddInTreeNode? {

        if(path.isNullOrEmpty()){
            return rootNode
        }
        val splittedPath = path.split('/')
        var curPath = rootNode
        for (i in 0..splittedPath.size){
            if(!curPath.childNodes.containsKey(splittedPath[i])){
                if(throwOnNotFound)
                    throw TreePathNotFoundException(path)
                else
                    return null
            }else{
                curPath = curPath.childNodes.getValue(splittedPath[i])
            }
        }
        return curPath
    }
}
