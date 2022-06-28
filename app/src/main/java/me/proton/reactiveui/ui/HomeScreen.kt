package me.proton.reactiveui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.proton.reactiveui.model.MessageDetails
import me.proton.reactiveui.sampledata.Data
import me.proton.reactiveui.ui.theme.ProtonReactiveUiTheme

@OptIn(ExperimentalMaterial3Api::class)
object HomeScreen {

    private val messages = Data.Messages.all().map { it.info }

    @Composable
    fun OnePane() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Destination.List) {
            composable(Destination.List) {
                TopBarScaffold(title = "Inbox") { paddingValues ->
                    MessagesListScreen(
                        modifier = Modifier.padding(paddingValues = paddingValues),
                        messages = messages,
                        onMessageClick = { navController.navigate(Destination.Details(it.id)) }
                    )
                }
            }
            addDetails { messageDetails ->
                TopBarScaffold(title = messageDetails.subject) { paddingValues ->
                    MessageDetailsScreen.WithoutSubject(
                        modifier = Modifier.padding(paddingValues),
                        messageDetails = messageDetails
                    )
                }
            }
        }
    }

    @Composable
    fun TwoPane() {
        val navController = rememberNavController()
        TopBarScaffold(title = "Inbox") { paddingValues ->
            Row(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Column(modifier = Modifier.fillMaxWidth(0.37f)) {
                    MessagesListScreen.Selectable(
                        messages = messages,
                        initialSelected = null,
                        onMessageClick = { navController.navigate(Destination.Details(it.id)) }
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = MaterialTheme.colorScheme.background,
                            shape = MaterialTheme.shapes.extraLarge.copy(
                                topEnd = CornerSize(0.dp),
                                bottomEnd = CornerSize(0.dp)
                            )
                        )
                        .padding(start = 24.dp)
                ) {
                    NavHost(navController = navController, startDestination = Destination.NoMessage) {
                        composable(Destination.NoMessage) { NoMessageSelectedScreen() }
                        addDetails { MessageDetailsScreen.WithSubject(messageDetails = it) }
                    }
                }
            }
        }
    }

    private fun NavGraphBuilder.addDetails(content: @Composable (MessageDetails) -> Unit) {
        composable(Destination.Details) { backStackEntry ->
            val message = run {
                val id = requireNotNull(backStackEntry.arguments?.getString(Destination.key)?.toInt())
                Data.Messages.all().first { it.id == id }
            }
            content(message.details)
        }
    }
}

private object Destination {

    const val key = "messageId"

    const val Details = "details/{$key}"
    const val List = "list"
    const val NoMessage = "NoMessage"

    fun Details(messageId: Int) = "details/$messageId"
}

@Composable
@Preview(showSystemUi = true)
fun OnePaneHomeScreen() {
    ProtonReactiveUiTheme {
        HomeScreen.OnePane()
    }
}

@Composable
@Preview(showSystemUi = true, device = Devices.TABLET)
fun TwoPaneHomeScreen() {
    ProtonReactiveUiTheme {
        HomeScreen.TwoPane()
    }
}
