package ktCode.core.services

interface IServiceProvider {
    fun getService(serviceType: Any): Any
}
