package ktCode.core.services.propertyService

import ktCode.core.services.fileUtility.DirectoryName

open class PropertyServiceImpl(val properties: Properties) : IPropertyService {
    override val dataDirectory: DirectoryName
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

}
