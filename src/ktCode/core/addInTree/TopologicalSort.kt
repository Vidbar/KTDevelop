package ktCode.core.addInTree

fun sort(codonInput: List<List<Codon>>): List<Codon> {
    val nameToNodeDict = mutableMapOf<String, Node>()
    val allNodes = mutableListOf<Node>()
    for (codonList in codonInput) {
        var previous: Node? = null
        for (codon in codonList) {
            val node = Node()
            node.codon = codon
            if (!codon.id.isNullOrEmpty()) nameToNodeDict[codon.id] = node

            if (previous != null) node.previous.add(previous)

            allNodes.add(node)
            previous = node
        }
    }

    for (node in allNodes) {
        if (!node.codon.insertBefore.isNullOrEmpty()) {
            for (beforeReference in node.codon.insertBefore.split(',')) {
                if (nameToNodeDict.containsKey(beforeReference)) {
                    nameToNodeDict[beforeReference]!!.previous.add(node)
                } else {
                    //TODO LoggingService.WarnFormatted("Codon ({0}) specified in the insertbefore of the {1} codon does not exist!", beforeReference, node.codon);
                }
            }
        }

        if (!node.codon.insertAfter.isNullOrEmpty()) {
            for (afterReference in node.codon.insertAfter.split(',')) {
                if (nameToNodeDict.containsKey(afterReference)) {
                    node.previous.add(nameToNodeDict[afterReference]!!)
                } else {
                    //TODO LoggingService.WarnFormatted("Codon ({0}) specified in the insertafter of the {1} codon does not exist!", afterReference, node.codon);
                }
            }
        }
    }

    val output = mutableListOf<Codon>()
    for (node in allNodes) node.visit(output)

    return output
}

class Node {

    private var visited: Boolean = false
    val previous = mutableListOf<Node>()
    lateinit var codon: Codon

    fun visit(output: MutableList<Codon>) {
        if (visited)
            return
        visited = true
        for (n in previous) n.visit(output)
        output.add(codon)
    }
}