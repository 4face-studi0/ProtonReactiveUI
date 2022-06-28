package me.proton.reactiveui.model

data class Time(
    val short: ShortTime,
    val long: LongTime
)

@JvmInline
value class ShortTime(val value: String)

@JvmInline
value class LongTime(val value: String)
