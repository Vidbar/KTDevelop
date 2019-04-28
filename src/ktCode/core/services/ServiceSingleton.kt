package ktCode.core.services

import dotNet.IServiceProvider
import dotNet.typeOf

var serviceProvider: IServiceProvider = FallbackServiceProvider()

inline fun <reified T> getRequiredService(): T = serviceProvider.getService(typeOf(T::class))

