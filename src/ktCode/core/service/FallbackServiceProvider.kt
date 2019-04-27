package ktCode.core.service

class FallbackServiceProvider : IServiceProvider {

    private val fallbackServiceDictionary = mutableMapOf<Any, Any>()

    override fun getService(serviceType: Any): Any = fallbackServiceDictionary[serviceType]!!
}
