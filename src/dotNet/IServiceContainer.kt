package dotNet

interface IServiceContainer : IServiceProvider {
    fun addService(serviceType: String, serviceInstance: Any)
}
