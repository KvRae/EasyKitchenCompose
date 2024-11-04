package com.kvrae.easykitchen.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kvrae.easykitchen.R
import com.kvrae.easykitchen.ui.components.FormButton
import com.kvrae.easykitchen.ui.components.TextInput
import com.kvrae.easykitchen.utils.EMAIL_FPS_ROUTE
import com.kvrae.easykitchen.utils.FORGET_PASS_SCREEN_ROUTE
import com.kvrae.easykitchen.utils.LOGIN_SCREEN_ROUTE
import com.kvrae.easykitchen.utils.OTP_FPS_ROUTE
import com.kvrae.easykitchen.utils.PASSWORD_FPS_ROUTE
import com.kvrae.easykitchen.utils.popThenNavigateTo

@Composable
fun ForgetPasswordScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var navRoute = remember {
        mutableStateOf<String>(EMAIL_FPS_ROUTE)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        when (navRoute.value) {
            PASSWORD_FPS_ROUTE -> PasswordFPScreen(
                onConfirm = {
                    navController.popThenNavigateTo(
                        navigateRoute = LOGIN_SCREEN_ROUTE,
                        popRoute = FORGET_PASS_SCREEN_ROUTE
                    )
                }
            )

            OTP_FPS_ROUTE -> OtpFPScreen(
                onConfirm = {
                    navRoute.value = PASSWORD_FPS_ROUTE
                }

            )

            else -> EmailFPScreen(
                modifier = modifier,
                onConfirm = {
                    navRoute.value = OTP_FPS_ROUTE
                }
            )

        }
    }

    BackHandler {
        when (navRoute.value) {
            PASSWORD_FPS_ROUTE -> navRoute.value = OTP_FPS_ROUTE
            OTP_FPS_ROUTE -> navRoute.value = EMAIL_FPS_ROUTE
            else -> navController.popThenNavigateTo(
                navigateRoute = LOGIN_SCREEN_ROUTE,
                popRoute = FORGET_PASS_SCREEN_ROUTE
            )

        }
    }

}

@Composable
fun EmailFPScreen(
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier
) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Logo"
        )
        Text(
            modifier = Modifier
                .padding(16.dp),
            text = "Please enter your email address to receive a verification code",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )


        TextInput(
            placeholder = "Enter Your Email",
            label = "Email",
            leadingIcon = Icons.Rounded.Email,
        )

        Spacer(modifier = Modifier.padding(32.dp))

        FormButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = "Send",
            onClick = onConfirm
        )
}

@Composable
fun OtpFPScreen(
    modifier: Modifier = Modifier,
    onConfirm: () -> Unit
) {
    Image(
        painter = painterResource(R.drawable.ic_launcher_foreground),
        contentDescription = "Logo"
    )
    Text("Please enter the verification code sent to your email address",
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface
    )

    TextInput(
        placeholder = "Enter Your Verification Code",
        label = "Verification Code",
        leadingIcon = Icons.Rounded.CheckCircle,
    )

    Spacer(modifier = Modifier.padding(32.dp))

    FormButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        text = "Verify",
        onClick = onConfirm
    )

}

@Composable
fun PasswordFPScreen(
    modifier: Modifier = Modifier,
    onConfirm: () -> Unit
) {
    Image(
        painter = painterResource(R.drawable.ic_launcher_foreground),
        contentDescription = "Logo"
    )
    Text(
        text = "Please enter your new password",
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface
    )
    TextInput(
        placeholder = "Enter Your Password",
        label = "Password",
        leadingIcon = Icons.Rounded.Email,
    )
    TextInput(
        placeholder = "Repeat Your Password",
        label = "Repeat Password",
        leadingIcon = Icons.Rounded.Email,
    )

    Spacer(modifier = Modifier.padding(32.dp))

    FormButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        text = "Confirm",
        onClick = onConfirm
    )
}
