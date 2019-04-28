package ktCode.core.services.fileUtility

open class PathName(path: String){
    var normalizedPath: String
    init {
        normalizedPath = normalizePath(path)
    }
}