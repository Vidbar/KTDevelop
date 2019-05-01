package ktCode.core.services.propertyService

import ktCode.core.services.fileUtility.FileName

fun loadProperties(fileName: FileName): Properties {
    return Properties()//loadcontents
}

class Properties {
    private val dict = mutableMapOf<String, String>()

    operator fun get(key: String): String = dict[key] ?: ""

}
