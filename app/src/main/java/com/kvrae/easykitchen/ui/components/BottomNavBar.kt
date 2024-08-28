package com.kvrae.easykitchen.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavBar(
    navItems : List<NavItem>,
    navItem: String,
    onNavItemSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .navigationBarsPadding(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(navItems.size) { index ->
            Icon(
                imageVector = navItems[index].icon,
                contentDescription = navItems[index].name,

                tint = if (navItems[index].name == navItem)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.onSurface,

                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        onNavItemSelect(navItems[index].name)
                    }
            )
        }
    }
}

val navItems = listOf(
    NavItem("Home", Icons.Default.Home),
    NavItem("Meals", Icons.Default.List),
    NavItem("Ingredients", Icons.Default.AddCircle),
)
data class NavItem(
    val name: String,
    val icon: ImageVector
)