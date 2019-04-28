package dotNet

import kotlin.reflect.KClass

fun typeOf(clazz: KClass<*>): String = clazz::class.qualifiedName