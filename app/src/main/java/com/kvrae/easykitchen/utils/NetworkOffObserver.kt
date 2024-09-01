package com.kvrae.easykitchen.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

/**
 * A Composable function that provides network connectivity status.
 *
 * This function uses the `ConnectivityManager` to monitor changes in network connectivity.
 * It returns a `Boolean` that indicates whether the network is currently available.
 * The `Boolean` value will be updated automatically as the network connectivity status changes.
 *
 * This function is designed to be used within a Composable context and utilizes
 * `remember` and `DisposableEffect` to manage the state and lifecycle of the network callback.
 *
 * Don't forget to add the necessary permissions to your `AndroidManifest.xml` file:
 * ``` xml
 * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
 * ```
 *
 *
 * @param context The [Context] used to obtain the [ConnectivityManager].
 *                By default, it uses the current `LocalContext`.
 *
 * @return `true` if the network is available, `false` otherwise.
 */
@Composable
fun rememberNetworkConnectivity(
    context: Context = LocalContext.current,
): Boolean {
    val isNetworkAvailable = remember {
        mutableStateOf(true)
    }
    val connectivityManager = context
        .getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager

    val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            isNetworkAvailable.value = true
        }

        override fun onLost(network: Network) {
            isNetworkAvailable.value = false
        }
    }

    DisposableEffect(Unit) {
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
        onDispose {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }

    return isNetworkAvailable.value
}
