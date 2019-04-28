package dotNet

interface IServiceProvider {
    fun <T>getService(serviceType: String): T
}
