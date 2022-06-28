package me.proton.reactiveui.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.proton.reactiveui.model.MessageInfo
import me.proton.reactiveui.sampledata.Data
import me.proton.reactiveui.ui.theme.ProtonReactiveUiTheme

object MessagesListScreen {

    @get:Composable
    private val contentPadding get() = PaddingValues(vertical = 8.dp)

    @Composable
    operator fun invoke(
        messages: List<MessageInfo>,
        onMessageClick: (MessageInfo) -> Unit,
        modifier: Modifier = Modifier
    ) {
        LazyColumn(modifier = modifier, contentPadding = contentPadding) {
            items(messages) {
                MessageListItem(messageInfo = it, onClick = onMessageClick)
            }
        }
    }

    @Composable
    fun Selectable(
        messages: List<MessageInfo>,
        initialSelected: MessageInfo?,
        onMessageClick: (MessageInfo) -> Unit,
        modifier: Modifier = Modifier
    ) {
        var selected by remember { mutableStateOf(initialSelected) }
        LazyColumn(modifier = modifier, contentPadding = contentPadding) {
            items(messages) { messageInfo ->
                MessageListItem.Selectable(
                    messageInfo = messageInfo,
                    isSelected = selected == messageInfo,
                    onClick = { selectedMessageInfo ->
                        selected = selectedMessageInfo
                        onMessageClick(selectedMessageInfo)
                    }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun MessagesListScreenPreview() {
    val messages = Data.Messages.all().map { it.info }
    ProtonReactiveUiTheme {
        MessagesListScreen(messages, onMessageClick = {})
    }
}

@Composable
@Preview(showBackground = true)
private fun SelectedMessagesListScreenPreview() {
    val messages = Data.Messages.all().map { it.info }
    val initialSelected = Data.Messages.Birthday.info
    ProtonReactiveUiTheme {
        MessagesListScreen.Selectable(messages, initialSelected = initialSelected, onMessageClick = {})
    }
}
