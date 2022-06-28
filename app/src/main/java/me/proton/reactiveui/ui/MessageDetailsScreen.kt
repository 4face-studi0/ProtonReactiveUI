package me.proton.reactiveui.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.proton.reactiveui.model.MessageDetails
import me.proton.reactiveui.model.Sender
import me.proton.reactiveui.sampledata.Data
import me.proton.reactiveui.ui.theme.ProtonReactiveUiTheme

object MessageDetailsScreen {

    @Composable
    fun WithSubject(messageDetails: MessageDetails, modifier: Modifier = Modifier) {
        MessageDetailsScreen(messageDetails = messageDetails, withSubject = true, modifier = modifier)
    }

    @Composable
    fun WithoutSubject(messageDetails: MessageDetails, modifier: Modifier = Modifier) {
        MessageDetailsScreen(messageDetails = messageDetails, withSubject = false, modifier = modifier)
    }

    @Composable
    operator fun invoke(messageDetails: MessageDetails, withSubject: Boolean, modifier: Modifier = Modifier) {
        Column(modifier = modifier.padding(16.dp)) {
            if (withSubject) {
                Text(text = messageDetails.subject, style = MaterialTheme.typography.displaySmall)
                VerticalSpacer()
            }
            SenderRow(sender = messageDetails.sender)
            VerticalSpacer()
            Text(text = messageDetails.time.value, style = MaterialTheme.typography.titleMedium)
            VerticalSpacer()
            VerticalSpacer()
            Text(text = messageDetails.body)
        }
    }
}

@Composable
private fun SenderRow(sender: Sender) {
    Column {
        Text(text = "from", style = MaterialTheme.typography.labelSmall)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = sender.name.value, style = MaterialTheme.typography.titleMedium)
            Text(text = " (${sender.address.value})", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun VerticalSpacer() {
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
@Preview(showBackground = true)
private fun WithSubjectMessageDetailsScreenPreview() {
    val messageDetails = Data.Messages.Birthday.details
    ProtonReactiveUiTheme {
        MessageDetailsScreen.WithSubject(messageDetails = messageDetails)
    }
}

@Composable
@Preview(showBackground = true)
private fun WithOutSubjectMessageDetailsScreenPreview() {
    val messageDetails = Data.Messages.Birthday.details
    ProtonReactiveUiTheme {
        MessageDetailsScreen.WithoutSubject(messageDetails = messageDetails)
    }
}
