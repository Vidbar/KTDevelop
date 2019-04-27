package ktCode.core.service

interface IServiceProvider {
    fun getService(serviceType: Any): Any
}
