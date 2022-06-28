package me.proton.reactiveui.sampledata

import me.proton.reactiveui.model.LongTime
import me.proton.reactiveui.model.Message
import me.proton.reactiveui.model.Sender
import me.proton.reactiveui.model.SenderAddress
import me.proton.reactiveui.model.SenderName
import me.proton.reactiveui.model.ShortTime
import me.proton.reactiveui.model.Time

object Data {

    object Senders {

        val Davide = Sender(
            SenderName("Davide"),
            SenderAddress("davide@proton.ch")
        )

        val Maciej = Sender(
            SenderName("Maciej"),
            SenderAddress("maciej@proton.ch")
        )

        val Marino = Sender(
            SenderName("Marino"),
            SenderAddress("marino@proton.ch")
        )

        val Stefanija = Sender(
            SenderName("Stefanija"),
            SenderAddress("stefanija@proton.ch")
        )

        val Zorica = Sender(
            SenderName("Zorica"),
            SenderAddress("zorica@proton.ch")
        )
    }

    object Messages {

        val Birthday = Message(
            id = 0,
            subject = "Happy birthday!",
            sender = Senders.Davide,
            time = Time(ShortTime("10.31"), LongTime("Today at 10.31")),
            body = LoremIpsum.Medium
        )

        val Taxes = Message(
            id = 1,
            subject = "You didn't pay your taxes!",
            sender = Senders.Maciej,
            time = Time(ShortTime("5.12"), LongTime("Today at 5.12")),
            body = LoremIpsum.Medium
        )

        val MergeRequest = Message(
            id = 2,
            subject = "Could you check my MR?",
            sender = Senders.Stefanija,
            time = Time(ShortTime("Yesterday"), LongTime("Yesterday at 16.37")),
            body = LoremIpsum.Medium
        )

        val Droidcon = Message(
            id = 3,
            subject = "Droidcon TICKETS!!",
            sender = Senders.Zorica,
            time = Time(ShortTime("2 days ago"), LongTime("Sunday at 14.10")),
            body = LoremIpsum.Medium
        )

        val Notes = Message(
            id = 4,
            subject = "Meeting notes",
            sender = Senders.Marino,
            time = Time(ShortTime("A month ago"), LongTime("Jun 6th at 14.10")),
            body = LoremIpsum.Medium
        )

        fun all() = listOf(
            Birthday,
            Taxes,
            MergeRequest,
            Droidcon,
            Notes
        )
    }

    private object LoremIpsum {

        const val Medium = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus vel egestas arcu. " +
            "Aliquam et efficitur purus, non placerat mauris. Maecenas dignissim sollicitudin semper. " +
            "Suspendisse efficitur, metus venenatis fringilla sagittis, augue massa consequat sem, eu lacinia " +
            "magna justo at metus. Mauris."
    }
}
