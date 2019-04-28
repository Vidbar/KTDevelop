package ktCode.core.services.fileUtility

import ktDevelop.startup.pathCombine

fun createDirectoryName(directoryName: String): DirectoryName {
    return DirectoryName(directoryName)
}

class DirectoryName(path: String) : PathName(path) {
    fun combineFile(relativeFileName: String): FileName {
        return createFileName(pathCombine(normalizedPath, relativeFileName))
    }
}