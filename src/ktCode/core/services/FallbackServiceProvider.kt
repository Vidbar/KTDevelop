package ktCode.core.services

import dotNet.IServiceProvider

class FallbackServiceProvider : IServiceProvider {

    private val fallbackServiceDictionary = mutableMapOf<String, Any>()

    override fun getService(serviceType: String): Any = fallbackServiceDictionary[serviceType]!!
}
