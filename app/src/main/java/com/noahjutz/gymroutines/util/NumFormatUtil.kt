
package com.noahjutz.gymroutines.util

import kotlin.math.floor

fun Double?.toStringOrBlank(): String = this?.toString() ?: ""
fun Int?.toStringOrBlank(): String = this?.toString() ?: ""

fun Double?.formatSimple(): String = when {
    this == null -> ""
    this == floor(this) -> this.toInt().toString()
    else -> this.toString()
}
