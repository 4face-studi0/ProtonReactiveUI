package me.proton.reactiveui

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toComposeRect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.window.layout.WindowMetricsCalculator
import me.proton.reactiveui.ui.HomeScreen
import me.proton.reactiveui.ui.theme.ProtonReactiveUiTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProtonReactiveUiTheme {
                App(getWidthDensity(activity = this))
            }
        }
    }
}

@Composable
private fun getWidthDensity(activity: Activity): Density {
    // Observe view configuration changes and recalculate the size class on each change. We can't
    // use Activity#onConfigurationChanged as this will sometimes fail to be called on different
    // API levels, hence why this function needs to be @Composable so we can observe the
    // ComposeView's configuration changes.
    LocalConfiguration.current
    val density = LocalDensity.current
    val metrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(activity)
    val size = with(density) { metrics.bounds.toComposeRect().size.toDpSize() }
    return Density.values().reversed().first { it.dp <= size.width.value }
}

@Composable
private fun App(widthDensity: Density) {
    if (widthDensity == Density.ExtraLarge) {
        HomeScreen.TwoPane()
    } else {
        HomeScreen.OnePane()
    }
}

private enum class Density(val dp: Int) {
    Compact(0),
    Medium(600),
    ExtraLarge(900)
}

@Composable
@Preview(showBackground = true)
private fun CompactPreview() {
    ProtonReactiveUiTheme {
        App(Density.Compact)
    }
}

@Composable
@Preview(showBackground = true)
private fun MediumPreview() {
    ProtonReactiveUiTheme {
        App(Density.Medium)
    }
}

@Composable
@Preview(showBackground = true, device = Devices.TABLET)
private fun ExpandedPreview() {
    ProtonReactiveUiTheme {
        App(Density.ExtraLarge)
    }
}
