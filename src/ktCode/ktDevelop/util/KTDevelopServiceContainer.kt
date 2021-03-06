package ktCode.ktDevelop.util

import dotNet.IServiceProvider

class KTDevelopServiceContainer : IServiceProvider {
    override fun <T> getService(serviceType: String): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val fallbackProviders = mutableListOf<IServiceProvider>()
    private val services = mutableMapOf<String, Any>()

    fun addFallbackProvider(provider: IServiceProvider) {
        fallbackProviders.add(provider)
    }

    fun addService(serviceType: String, serviceInstance: Any) {
        services[serviceType] = serviceInstance
        onServiceInitialized(serviceType, serviceInstance)
    }

    private fun onServiceInitialized(serviceType: String, serviceInstance: Any) {
        //IDisposable interfaces add to servicesToDispose list
    }

}
