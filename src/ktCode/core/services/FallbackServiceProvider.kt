package ktCode.core.services

import dotNet.IServiceProvider

class FallbackServiceProvider : IServiceProvider {

    private val fallbackServiceDictionary = mutableMapOf<String, Any>()

    override fun <T> getService(serviceType: String): T = (fallbackServiceDictionary[serviceType] as T?)!!
}
