package me.proton.reactiveui.model

data class Message(
    val info: MessageInfo,
    val details: MessageDetails
) {

    val id get() = info.id

    constructor(
        id: Int,
        subject: String,
        sender: Sender,
        time: Time,
        body: String
    ) : this(
        info = MessageInfo(id = id, subject = subject, sender = sender.name, time = time.short),
        details = MessageDetails(subject = subject, sender = sender, time = time.long, body = body)
    )
}

data class MessageInfo(
    val id: Int,
    val subject: String,
    val sender: SenderName,
    val time: ShortTime
)

data class MessageDetails(
    val subject: String,
    val sender: Sender,
    val time: LongTime,
    val body: String
)
