package ktDevelop.services

import ktCode.core.services.fileUtility.DirectoryName
import ktCode.core.services.fileUtility.FileName
import ktCode.core.services.propertyService.Properties
import ktCode.core.services.propertyService.PropertyServiceImpl
import ktCode.core.services.propertyService.loadProperties
import java.io.File

class PropertyService(val configDirectory: DirectoryName, val dataDirectory: DirectoryName, propertiesName: String):
    PropertyServiceImpl(loadPropertiesFromStream(configDirectory.combineFile("$propertiesName.xml")))
{
    val propertiesFileName: FileName = configDirectory.combineFile("$propertiesName.xml")
}

fun loadPropertiesFromStream(fileName: FileName): Properties {
    val file = File(fileName.normalizedPath)
    return if (!file.exists()) Properties()
    else loadProperties(fileName)
}
