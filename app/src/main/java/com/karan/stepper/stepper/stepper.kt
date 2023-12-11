package com.karan.stepper.stepper

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun StepperComponent(
    icon : ImageVector,
    title : String,
    state : WizardStepState,
    modifier: Modifier = Modifier,
    decoration: WizardStepAction? = null,
    color: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(backgroundColor = color),
    showVerticalDivider: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularIcon(icon, enabled = state != WizardStepState.INACTIVE)

            Spacer(modifier = Modifier.size(16.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.size(16.dp))

            when (decoration) {
                is WizardStepAction.Action -> {
                    ActionButton(action = decoration, state = state)
                }
                is WizardStepAction.ProgressIndicator -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(48.dp)
                            .padding(8.dp)
                    )
                }
                else -> {}
            }
        }

        Row(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .height(IntrinsicSize.Min)
        ) {
            if (showVerticalDivider) {
                VerticalDivider(
                    modifier = Modifier.padding(start = 18.dp, end = 26.dp)
                )
            }

            CompositionLocalProvider(
                LocalContentColor provides if (state == WizardStepState.INACTIVE)
                    contentColor.copy(alpha = 0.38f) else contentColor
            ) {
                Column(
                    modifier = Modifier.padding(start = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    content()
                }
            }
        }
    }
}

@Composable
private fun ActionButton(
    action : WizardStepAction.Action,
    state: WizardStepState,
    modifier: Modifier = Modifier
) {
    if (state == WizardStepState.COMPLETED && action.enabled) {
        OutlinedButton(
            modifier = modifier,
            onClick = action.onClick,
            enabled = action.enabled,
            colors = if (action.dangerous) {
                ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                )
            } else {
                ButtonDefaults.outlinedButtonColors()
            }
        ) {
            Text(text = action.text)
        }
    } else {
        Button(
            modifier = modifier,
            onClick = action.onClick,
            enabled = action.enabled,
            colors = if (action.dangerous) {
                ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError
                )
            } else {
                ButtonDefaults.buttonColors()
            },
        ) {
            Text(text = action.text)
        }
    }
}


@Composable
fun CircularIcon(
    painter: Painter,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.secondary,
    enabled: Boolean = true,
) {
    Image(
        painter = painter,
        contentDescription = null,
        colorFilter = if (enabled) {
            ColorFilter.tint(MaterialTheme.colorScheme.contentColorFor(backgroundColor))
        } else {
            ColorFilter.tint(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f))
        },
        modifier = modifier
            .background(
                color = if (enabled) {
                    backgroundColor
                } else {
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
                },
                shape = CircleShape
            )
            .padding(8.dp)
    )
}

@Composable
fun CircularIcon(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.secondary,
    enabled: Boolean = true,
) {
    CircularIcon(
        painter = rememberVectorPainter(image = imageVector),
        modifier = modifier,
        backgroundColor = backgroundColor,
        enabled = enabled
    )
}

@Composable
fun VerticalDivider(
    modifier: Modifier = Modifier,
    width: Dp = 4.dp,
    color: Color = MaterialTheme.colorScheme.surfaceVariant,
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .width(width)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
    ) { }
}