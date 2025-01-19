package com.kvrae.easykitchen.presentation.miscellaneous.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kvrae.easykitchen.ui.theme.AppTypography
import com.kvrae.easykitchen.utils.MAIN_HOME_ROUTE
import com.kvrae.easykitchen.utils.MAIN_MEALS_ROUTE
import com.kvrae.easykitchen.utils.getTapBarIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title : String = "EasyKitchen",
    onActionClick: () -> Unit,
    actionIcon : Int = getTapBarIcon(title),
    ingredientsSize : Int = 0
) {
    TopAppBar(
        modifier = modifier
            .statusBarsPadding()
        ,
        title = {
            Text(
                text = title,
                style = AppTypography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
        },
        actions = {
            if (title == MAIN_HOME_ROUTE || title == MAIN_MEALS_ROUTE) {
                IconButton(onClick = { onActionClick }) {
                    Icon(painter = painterResource(id = actionIcon), contentDescription = "actionIcon")
                }
            } else {
                BadgedBox(
                    modifier = Modifier.padding(end = 16.dp),
                    badge = {
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                            .size(16.dp)


                    ) {

                    }
                }) {
                    Icon(
                        painter = painterResource(id = actionIcon),
                        contentDescription = "actionIcon"
                    )
                }
            }


        },
        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    )
}



