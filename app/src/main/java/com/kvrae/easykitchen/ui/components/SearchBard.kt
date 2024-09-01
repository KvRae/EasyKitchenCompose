import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize


/**
 * A composable that displays a search bar with a list of items in our case POIs
 * it covers the whole screen and has a text field to search for items and a list of items
 * @param items the list of items to search for
 * @param placeholder the placeholder of the text field
 * @param value the value of the text field
 * @param onTitleChange the callback to be called when the text field value changes
 * @param onPoiChange the callback to be called when an item is selected
 * @param backgroundColor the background color of the screen
 * **Docs:** [SearchBarLayout Docs](https://karam-mannai.kvrae.tech/MachInNav-Engine-Docs/features/ui_components/#search-bar-layout)
 *
 */
@Composable
fun SearchBarLayout(
    modifier: Modifier = Modifier,
    items: List<String>,
    placeholder: String? = "",
    value: String? = "",
    onTitleChange: (String) -> Unit = {},
    backgroundColor: Color = Color.Transparent
) {
    var expanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val focusManager = LocalFocusManager.current
    val screenColor = animateColorAsState(
        if (expanded) backgroundColor else Color.Transparent,
        label = "",
        animationSpec = tween(100)
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .animateContentSize(
                animationSpec = tween(
                    delayMillis = 100,
                    easing = LinearOutSlowInEasing
                )
            )
    ) {
        Column(
            modifier = Modifier
                .background(screenColor.value)
                .statusBarsPadding()
                .navigationBarsPadding()
                .fillMaxSize()
        ) {
            TextField(
                value = value ?: "",
                onValueChange = {
                    expanded = true
                    onTitleChange(it)
                },
                leadingIcon = {
                    if (expanded)
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                expanded = false
                                focusManager.clearFocus()
                            }
                        )
                    else
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = null
                        )
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                placeholder = {
                    Text(
                        text = placeholder ?: "",
                    )
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .onGloballyPositioned {
                        textFieldSize = it.size.toSize()
                    }
                    .onFocusChanged {
                        expanded = it.isFocused
                    },
                shape = CircleShape,
                trailingIcon = {
                    if (value?.isNotEmpty() == true) {
                        Icon(
                            imageVector = Icons.Rounded.Clear,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                onTitleChange("")
                            }
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = VisualTransformation.None,
                keyboardActions = KeyboardActions.Default,
            )
            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically( animationSpec = tween(200)),
                exit = fadeOut( animationSpec = tween(100))
            ) {
                val filteredItems = items.filter { it.contains(value ?: "", ignoreCase = true) }
                LazyColumn {
                    items(
                        count = filteredItems.size,
                        key = { index -> filteredItems[index] }
                    ) { index ->
                        val item = filteredItems[index]
                        DropdownMenuItem(
                            modifier = Modifier
                                .padding(top = 8.dp, bottom = 8.dp)
                                .fillMaxWidth(),
                            text = {
                                SearchItem(
                                    item = item
                                )
                            },
                            onClick = {
                                expanded = false
                                onTitleChange(item)
                                focusManager.clearFocus()
                            },
                            leadingIcon = {
                                Box(
                                    modifier = Modifier.clip(RoundedCornerShape(8.dp))
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Info,
                                        contentDescription = null,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.Info,
                                    contentDescription = null,
                                )
                            }
                        )
                        Divider(modifier = Modifier.padding(start = 32.dp, end = 16.dp))
                    }
                }
                if (filteredItems.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No data",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SearchItem(
    item: String = ""
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        if (item.isNotBlank())
            Text(
                text = item,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        if (!item.isNullOrBlank())
            Text(
                text = item,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
        if (item.isNotBlank())
            Text(
                text = item + " - " + item,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
    }
}