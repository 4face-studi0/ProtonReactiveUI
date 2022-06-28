package me.proton.reactiveui.model

data class Sender(
    val name: SenderName,
    val address: SenderAddress
)

@JvmInline
value class SenderName(val value: String)

@JvmInline
value class SenderAddress(val value: String)
