package com.kvrae.easykitchen.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kvrae.easykitchen.R
import com.kvrae.easykitchen.presentation.ui.components.FormButton
import com.kvrae.easykitchen.presentation.ui.components.TextFormButton
import com.kvrae.easykitchen.presentation.ui.components.TextInput

import com.kvrae.easykitchen.utils.FORGET_PASS_SCREEN_ROUTE
import com.kvrae.easykitchen.utils.LOGIN_SCREEN_ROUTE
import com.kvrae.easykitchen.utils.MAIN_SCREEN_ROUTE
import com.kvrae.easykitchen.utils.REGISTER_SCREEN_ROUTE
import com.kvrae.easykitchen.utils.popThenNavigateTo


@Composable
fun LoginScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Logo"
        )
        TextInput(
            placeholder = "Enter Your Email",
            label = "Email",
            leadingIcon = Icons.Rounded.Email,
        )
        TextInput(
            placeholder = "Enter Your Password",
            label = "Password",
            leadingIcon = Icons.Rounded.Lock,
        )

        TextFormButton(
            text = "Forget Password?",
            onClick = {
                navController.navigate(FORGET_PASS_SCREEN_ROUTE)
            }
        )

        FormButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = "Login",
            onClick = {
                navController.popThenNavigateTo(
                    navigateRoute = MAIN_SCREEN_ROUTE,
                    popRoute = LOGIN_SCREEN_ROUTE
                )
            }
        )

        Divider(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Don't have an account?",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            TextFormButton(
                text = "Sign Up",
                onClick = {
                    navController.navigate(REGISTER_SCREEN_ROUTE)
                }
            )
        }

    }

}