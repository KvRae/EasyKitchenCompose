package com.kvrae.easykitchen.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.kvrae.easykitchen.R
import com.kvrae.easykitchen.data.dto.CategoryDto
import com.kvrae.easykitchen.data.dto.IngredientDto
import com.kvrae.easykitchen.data.dto.MealDto
import com.valentinilk.shimmer.shimmer

@Composable
fun MealCard(
    modifier: Modifier = Modifier,
    meal: MealDto = MealDto(),
    onMealClick: (String) -> Unit
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .width(240.dp)
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    onMealClick(meal.id.orEmpty())
                }
                .padding(8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            SubcomposeAsyncImage(
                model = meal.image.orEmpty(),
                loading = {
                    CircularProgressIndicator()
                },
                contentDescription = stringResource(R.string.meal_image),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .height(200.dp)
                    .align(Alignment.CenterHorizontally),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = meal.name.orEmpty(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = meal.area.orEmpty(),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
fun CategoryCard(
    category : CategoryDto
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(50)),
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(60))
                    .background(MaterialTheme.colorScheme.tertiary)
                    .padding(4.dp),

            ) {
                SubcomposeAsyncImage(
                    model = category.image.orEmpty(),
                    loading = {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.onPrimary,
                            strokeWidth = 2.dp

                        )
                    },
                    contentDescription = stringResource(R.string.meal_image),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize(),
                )
            }
            Text(
                text = category.name.orEmpty(),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
fun MealByAreaAnsCategoryCard(
    modifier: Modifier = Modifier,
    meal : MealDto = MealDto(),
    onMealClick: (String) -> Unit
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    onMealClick(meal.id.orEmpty())
                }
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            SubcomposeAsyncImage(
                model = meal.image,
                loading = {
                    CircularProgressIndicator()
                },
                contentDescription = stringResource(R.string.meal_image),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .height(64.dp),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = meal.name.orEmpty(),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = meal.category.orEmpty(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = meal.area.orEmpty(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Composable
fun IngredientCard(
    modifier: Modifier = Modifier,
    onIngredientClick: (String) -> Unit,
    ingredient: IngredientDto
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        var checked by remember { mutableStateOf(false) }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = ingredient.name.orEmpty(),
                style = MaterialTheme.typography.titleSmall,
            )
            IconToggleButton(
                checked = checked,
                onCheckedChange = {
                    checked = it
                    onIngredientClick("Name")
                }
            ) {
                Icon(
                    imageVector = if (!checked) Icons.Rounded.Add else Icons.Rounded.Clear,
                    contentDescription = "Add/Remove Icon"
                )
            }
        }

    }
}


@Composable
fun MealImageCoveredCard(
    modifier: Modifier = Modifier,
    meal: MealDto = MealDto(),
    onMealClick: (String) -> Unit
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onMealClick(meal.id.orEmpty()) } // Assuming meal.id is a String identifier
    ) {

        SubcomposeAsyncImage(
            model = meal.image,
            loading = {
                MealCoveredImageShimmer()
            },
            contentDescription = stringResource(R.string.meal_image),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.4f)) // Semi-transparent background
                .height(150.dp)
        ) {
         Text(
             modifier = Modifier.align(Alignment.CenterStart),
             text = meal.name.orEmpty()
         )
         Text(
             modifier = Modifier.align(Alignment.BottomStart),
             text = meal.area.orEmpty()
         )
         Text(
             modifier = Modifier.align(Alignment.BottomEnd),
             text = meal.name.orEmpty()
         )
        }
    }
}

@Composable
fun MealCoveredImageShimmer(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(16.dp))
            .shimmer()
            .background(MaterialTheme.colorScheme.onBackground)

    )
}