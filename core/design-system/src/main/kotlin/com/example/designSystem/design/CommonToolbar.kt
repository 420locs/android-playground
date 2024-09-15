package com.example.designSystem.design

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.designSystem.DrawableResource

sealed class Button {
    data class TextButton(
        val text: String, val color: Color, val backgroundColor: Color, val onClick: () -> Unit
    ) : Button()

    data class BackButton(val onClick: () -> Unit) : Button()
    data class FloatBorderedButton(
        @DrawableRes val icon: Int, val backgroundColor: Color, val onClick: () -> Unit
    ) : Button()

    data class CircledIcon(
        @DrawableRes val icon: Int, val backgroundColor: Color, val onClick: () -> Unit
    ) : Button()

    data object None : Button()
}


@Composable
fun TitledToolbar(
    title: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    leftButtons: List<Button> = emptyList(),
    rightButtons: List<Button> = emptyList(),
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = backgroundColor)
            .padding(horizontal = 24.dp)
            .padding(top = 32.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            leftButtons.forEach { button ->
                CommonToolbarButton(
                    modifier = Modifier.padding(end = 8.dp),
                    button = button
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            rightButtons.forEach { button ->
                CommonToolbarButton(
                    modifier = Modifier.padding(start = 8.dp),
                    button = button
                )
            }
        }

        Text(
            modifier = Modifier.padding(top = 16.dp, bottom = 12.dp),
            text = title,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp
        )
    }
}


@Composable
fun MainToolbar(
    title: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    notification: Boolean,
    newNotificationsCount: Int,
    navigateToNotification: () -> Unit,
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = backgroundColor)
            .padding(horizontal = 32.dp)
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(0.67f),
            text = title,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp
        )

        FloatingActionButton(modifier = Modifier.padding(start = 16.dp), onClick = navigateToNotification) {
            BadgedBox(
                badge = {
                    if (notification) {
                        Badge(
                            containerColor = Color.Red,
                            contentColor = Color.White
                        ) {
                            Text(text = newNotificationsCount.toString())
                        }
                    }
                }
            ) {
                Icon(
                    painter = painterResource(id = DrawableResource.icon_notifications),
                    contentDescription = "Notification",
                )
            }
        }
    }

}


@Composable
private fun FloatBorderedIcon(
    button: Button.FloatBorderedButton,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(modifier = modifier, onClick = button.onClick) {
        Icon(
            painter = painterResource(id = button.icon),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
        )
    }
}

@Composable
private fun CircledIcon(
    button: Button.CircledIcon, modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(36.dp)
            .background(color = button.backgroundColor, shape = CircleShape)
            .clickable(
                onClick = button.onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple()
            ),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(id = button.icon),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
        )
    }
}

@Composable
private fun TextButton(
    button: Button.TextButton,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
            .clickable(
                onClick = button.onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple()
            )
            .background(
                color = button.backgroundColor,
                shape = CircleShape
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),
        color = MaterialTheme.colorScheme.primary,
        text = button.text,
        style = MaterialTheme.typography.labelMedium
    )

}

@Composable
private fun BackIcon(
    button: Button.BackButton, modifier: Modifier = Modifier
) {
    FloatingActionButton(modifier = modifier, onClick = button.onClick) {
        Icon(
            painter = painterResource(id = DrawableResource.icon_back),
            contentDescription = "back",
            tint = MaterialTheme.colorScheme.primary,
        )
    }
}

@Composable
private fun CommonToolbarButton(modifier: Modifier = Modifier, button: Button) {
    return when (button) {
        is Button.BackButton -> BackIcon(modifier = modifier, button = button)
        is Button.CircledIcon -> CircledIcon(modifier = modifier, button = button)
        is Button.FloatBorderedButton -> FloatBorderedIcon(modifier = modifier, button = button)
        is Button.TextButton -> TextButton(modifier = modifier, button = button)
        Button.None -> Unit
    }
}

// Preview

@Preview
@Composable
internal fun FloatBorderedIconBackPreview() {
    Box(modifier = Modifier
        .size(100.dp)
        .background(color = Color.White),
        contentAlignment = Alignment.Center) {
        FloatBorderedIcon(
            button = Button.FloatBorderedButton(icon = DrawableResource.icon_back,
                backgroundColor = MaterialTheme.colorScheme.onPrimary,
                onClick = {})
        )
    }
}

@Preview
@Composable
internal fun CircledIconBackPreview() {
    Box(modifier = Modifier
        .size(100.dp)
        .background(color = Color.White),
        contentAlignment = Alignment.Center) {
        CircledIcon(
            button = Button.CircledIcon(icon = DrawableResource.icon_back,
                backgroundColor = MaterialTheme.colorScheme.inversePrimary,
                onClick = {})
        )
    }
}

@Preview
@Composable
internal fun TextButtonPreview() {
    TextButton(
        Button.TextButton(
            text = "Popular",
            color = MaterialTheme.colorScheme.primary,
            backgroundColor = MaterialTheme.colorScheme.inversePrimary,
            onClick = {})
    )
}

@Preview
@Composable
internal fun BaseToolbarPreview() {
    TitledToolbar(
        title = "Title ntn",
        leftButtons = listOf(Button.BackButton(onClick = {})),
        rightButtons = listOf(
            Button.TextButton(
                text = "hehe",
                color = MaterialTheme.colorScheme.primary,
                backgroundColor = MaterialTheme.colorScheme.inversePrimary,
                onClick = {}),
            Button.CircledIcon(
                icon = DrawableResource.icon_back,
                backgroundColor = MaterialTheme.colorScheme.inversePrimary,
                onClick = {})
        )
    )
}

@Preview
@Composable
internal fun MainToolbarPreview() {
    MainToolbar(
        title = "Title ntn asd asd as das dsd  asdasd",
        notification = true,
        navigateToNotification = {},
        newNotificationsCount = 1
    )
}