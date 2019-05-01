package ktCode.core.addInTree

class AddInTreeNode {

    private var codonInput: List<List<Codon>>? = null
    private val codons: List<Codon>
    get() {
        val _codons = sort(codonInput!!)
        codonInput = null
        return _codons
    }

    val childNodes = mutableMapOf<String, AddInTreeNode>()

    fun <T> buildchildItems(parameter: Any?, additionalConditions: List<ICondition> = listOf()): List<T>? {
        val items = mutableListOf<T>()
        for (codon in codons){
            val result = buildchildItem(codon, parameter, additionalConditions)
        }
        return items
    }

    private fun buildchildItem(codon: Codon, parameter: Any?, additionalConditions: List<ICondition>? = null): Any {
        val subItemNode = childNodes[codon.id]

        var conditions: List<ICondition>
        if (additionalConditions == null)
            conditions = codon.conditions
        else if (codon.conditions == null)
            conditions = additionalConditions
        else
            conditions = additionalConditions + codon.conditions

        return codon.buildItem(BuildItemArgs(parameter, codon, conditions, subItemNode))
    }
}
