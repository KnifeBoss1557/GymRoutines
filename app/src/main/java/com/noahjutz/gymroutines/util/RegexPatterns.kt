

package com.noahjutz.gymroutines.util

object RegexPatterns {
    val integer =
        """^$|^0$|^[1-9]\d{0,2}$""".toRegex()
    val float =
        """^(0|[1-9]\d{0,2})?((?<=\d)\.)?((?<=\.)\d{1,3}$)?""".toRegex()
    val duration =
        """^$|[1-9]\d{0,3}""".toRegex()
}
