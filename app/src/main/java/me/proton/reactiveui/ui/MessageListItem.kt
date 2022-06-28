package me.proton.reactiveui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.proton.reactiveui.model.MessageInfo
import me.proton.reactiveui.sampledata.Data
import me.proton.reactiveui.ui.theme.ProtonReactiveUiTheme

object MessageListItem {

    @Composable
    operator fun invoke(messageInfo: MessageInfo, onClick: (MessageInfo) -> Unit) {
        Selectable(messageInfo = messageInfo, isSelected = false, onClick = onClick)
    }

    @Composable
    fun Selectable(messageInfo: MessageInfo, isSelected: Boolean, onClick: (MessageInfo) -> Unit) {
        Box(modifier = Modifier.padding(4.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick(messageInfo) }
                    .selectedBackground(shouldShow = isSelected)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = messageInfo.sender.value)
                    Text(text = messageInfo.subject)
                }
                Text(text = messageInfo.time.value, style = MaterialTheme.typography.labelSmall)
            }
        }
    }

    private fun Modifier.selectedBackground(shouldShow: Boolean) = composed {
        if (shouldShow) {
            val color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
            background(color = color, shape = MaterialTheme.shapes.large)
        } else {
            this
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun MessageListItemPreview() {
    val messageInfo = Data.Messages.Birthday.info
    ProtonReactiveUiTheme {
        MessageListItem(messageInfo = messageInfo, onClick = {})
    }
}

@Composable
@Preview(showBackground = true)
private fun SelectedMessageListItemPreview() {
    val messageInfo = Data.Messages.Birthday.info
    ProtonReactiveUiTheme {
        MessageListItem.Selectable(messageInfo = messageInfo, isSelected = true, onClick = {})
    }
}
