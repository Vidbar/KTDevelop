package ktCode.core.services.fileUtility

fun createFileName(fileName: String) = FileName(fileName)

class FileName(path: String) : PathName(path)
