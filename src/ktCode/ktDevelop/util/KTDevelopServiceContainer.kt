package ktCode.ktDevelop.util

import ktCode.core.service.IServiceProvider

class KTDevelopServiceContainer : IServiceProvider {

    private val fallbackProviders = mutableListOf<IServiceProvider>()
    private val services = mutableMapOf<String, Any>()

    fun addFallbackProvider(provider: IServiceProvider) {
        fallbackProviders.add(provider)
    }

    override fun getService(serviceType: Any): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun addService(serviceType: String, serviceInstance: Any) {
        services[serviceType] = serviceInstance
        onServiceInitialized(serviceType, serviceInstance)
    }

    private fun onServiceInitialized(serviceType: String, serviceInstance: Any) {
        //IDisposable interfaces add to servicesToDispose list
    }

}
