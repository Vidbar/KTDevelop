package ktCode.core.addInTree

import ktCode.core.addInTree.addIn.AddIn
import ktCode.core.services.propertyService.Properties

class Codon(val addIn: AddIn, name: String, private val properties: Properties, val conditions: List<ICondition>) {

    val insertAfter: String
        get() = properties["insertafter"]
    val insertBefore: String
        get() = properties["insertbefore"]
    val id: String
        get() = properties["id"]

    fun buildItem(args: BuildItemArgs): Any {
        //val doozer = addIn.addInTree.doozers[name]
        //return doozer.buildItem(args)
        return Any()
    }
}
