package ktCode.core.addInTree.addIn

import ktCode.core.addInTree.IAddInTree
import java.io.File
import java.io.InputStreamReader

fun LoadAddIn(addInTree: IAddInTree, fileName: String, nameTable: String): AddIn {
    val file = File(fileName)
    if (file.canRead()) {
        val addIn = loadAddIn(addInTree, file.reader(), file.path, nameTable)
        addIn.addInFileName = fileName
        return addIn
    } else {
        throw Exception("Can't load $fileName")
    }
}

fun loadAddIn(addInTree: IAddInTree, textReader: InputStreamReader, path: String?, nameTable: String): AddIn {
    val addIn = AddIn(addInTree)
    //TODO read all the addIn information and parse
    // setupAddIn(reader, addIn, hintPath)
    return addIn
}

class AddIn(addInTree: IAddInTree) {
    lateinit var manifest: AddInManifest
    var enabled = false
    lateinit var addInFileName: String
}
