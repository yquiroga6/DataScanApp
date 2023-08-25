package gio.quiroga.datascantest1.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Composable
fun rememberReceipt(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "receipt",
            defaultWidth = 40.0.dp,
            defaultHeight = 40.0.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(5.25f, 36.042f)
                verticalLineTo(3.792f)
                lineToRelative(0.125f, 0.021f)
                quadToRelative(0.125f, 0.02f, 0.333f, 0.229f)
                lineToRelative(1.5f, 1.541f)
                quadToRelative(0.25f, 0.209f, 0.5f, 0.209f)
                reflectiveQuadToRelative(0.459f, -0.209f)
                lineToRelative(1.541f, -1.541f)
                quadToRelative(0.209f, -0.209f, 0.459f, -0.209f)
                reflectiveQuadToRelative(0.458f, 0.209f)
                lineToRelative(1.5f, 1.541f)
                quadToRelative(0.25f, 0.209f, 0.5f, 0.209f)
                reflectiveQuadToRelative(0.458f, -0.209f)
                lineToRelative(1.542f, -1.541f)
                quadToRelative(0.208f, -0.209f, 0.458f, -0.209f)
                reflectiveQuadToRelative(0.459f, 0.209f)
                lineToRelative(1.5f, 1.541f)
                quadToRelative(0.25f, 0.209f, 0.5f, 0.209f)
                reflectiveQuadTo(18f, 5.583f)
                lineToRelative(1.542f, -1.541f)
                quadToRelative(0.208f, -0.209f, 0.458f, -0.209f)
                reflectiveQuadToRelative(0.458f, 0.209f)
                lineTo(22f, 5.583f)
                quadToRelative(0.208f, 0.209f, 0.458f, 0.209f)
                reflectiveQuadToRelative(0.5f, -0.209f)
                lineToRelative(1.5f, -1.541f)
                quadToRelative(0.209f, -0.209f, 0.459f, -0.209f)
                reflectiveQuadToRelative(0.458f, 0.209f)
                lineToRelative(1.542f, 1.541f)
                quadToRelative(0.208f, 0.209f, 0.458f, 0.209f)
                reflectiveQuadToRelative(0.5f, -0.209f)
                lineToRelative(1.5f, -1.541f)
                quadToRelative(0.208f, -0.209f, 0.458f, -0.209f)
                reflectiveQuadToRelative(0.459f, 0.209f)
                lineToRelative(1.541f, 1.541f)
                quadToRelative(0.209f, 0.209f, 0.459f, 0.209f)
                reflectiveQuadToRelative(0.5f, -0.209f)
                lineToRelative(1.5f, -1.541f)
                quadToRelative(0.208f, -0.209f, 0.333f, -0.229f)
                lineToRelative(0.125f, -0.021f)
                verticalLineToRelative(32.25f)
                lineToRelative(-0.125f, 0.041f)
                quadToRelative(-0.125f, 0.042f, -0.333f, -0.208f)
                lineToRelative(-1.5f, -1.5f)
                quadToRelative(-0.25f, -0.25f, -0.5f, -0.25f)
                reflectiveQuadToRelative(-0.459f, 0.25f)
                lineToRelative(-1.541f, 1.5f)
                quadToRelative(-0.209f, 0.25f, -0.459f, 0.25f)
                reflectiveQuadToRelative(-0.458f, -0.25f)
                lineToRelative(-1.5f, -1.5f)
                quadToRelative(-0.25f, -0.25f, -0.5f, -0.25f)
                reflectiveQuadToRelative(-0.458f, 0.25f)
                lineToRelative(-1.542f, 1.5f)
                quadToRelative(-0.208f, 0.25f, -0.458f, 0.25f)
                reflectiveQuadToRelative(-0.459f, -0.25f)
                lineToRelative(-1.5f, -1.5f)
                quadToRelative(-0.25f, -0.25f, -0.5f, -0.25f)
                reflectiveQuadToRelative(-0.458f, 0.25f)
                lineToRelative(-1.542f, 1.5f)
                quadToRelative(-0.208f, 0.25f, -0.458f, 0.25f)
                reflectiveQuadToRelative(-0.458f, -0.25f)
                lineTo(18f, 34.375f)
                quadToRelative(-0.208f, -0.25f, -0.458f, -0.25f)
                reflectiveQuadToRelative(-0.5f, 0.25f)
                lineToRelative(-1.5f, 1.5f)
                quadToRelative(-0.209f, 0.25f, -0.459f, 0.25f)
                reflectiveQuadToRelative(-0.458f, -0.25f)
                lineToRelative(-1.542f, -1.5f)
                quadToRelative(-0.208f, -0.25f, -0.458f, -0.25f)
                reflectiveQuadToRelative(-0.5f, 0.25f)
                lineToRelative(-1.5f, 1.5f)
                quadToRelative(-0.208f, 0.25f, -0.458f, 0.25f)
                reflectiveQuadToRelative(-0.459f, -0.25f)
                lineToRelative(-1.541f, -1.5f)
                quadToRelative(-0.209f, -0.25f, -0.459f, -0.25f)
                reflectiveQuadToRelative(-0.5f, 0.25f)
                lineToRelative(-1.5f, 1.5f)
                quadToRelative(-0.208f, 0.25f, -0.333f, 0.208f)
                lineToRelative(-0.125f, -0.041f)
                close()
                moveToRelative(6.125f, -8.375f)
                horizontalLineTo(28.75f)
                quadToRelative(0.542f, 0f, 0.917f, -0.375f)
                reflectiveQuadToRelative(0.375f, -0.959f)
                quadToRelative(0f, -0.541f, -0.375f, -0.916f)
                reflectiveQuadToRelative(-0.917f, -0.375f)
                horizontalLineTo(11.375f)
                quadToRelative(-0.542f, 0f, -0.937f, 0.375f)
                quadToRelative(-0.396f, 0.375f, -0.396f, 0.916f)
                quadToRelative(0f, 0.584f, 0.396f, 0.959f)
                quadToRelative(0.395f, 0.375f, 0.937f, 0.375f)
                close()
                moveToRelative(0f, -6.375f)
                horizontalLineTo(28.75f)
                quadToRelative(0.542f, 0f, 0.917f, -0.375f)
                reflectiveQuadToRelative(0.375f, -0.959f)
                quadToRelative(0f, -0.541f, -0.375f, -0.916f)
                reflectiveQuadToRelative(-0.917f, -0.375f)
                horizontalLineTo(11.375f)
                quadToRelative(-0.542f, 0f, -0.937f, 0.375f)
                quadToRelative(-0.396f, 0.375f, -0.396f, 0.916f)
                quadToRelative(0f, 0.584f, 0.396f, 0.959f)
                quadToRelative(0.395f, 0.375f, 0.937f, 0.375f)
                close()
                moveToRelative(0f, -6.417f)
                horizontalLineTo(28.75f)
                quadToRelative(0.542f, 0f, 0.917f, -0.375f)
                reflectiveQuadToRelative(0.375f, -0.917f)
                quadToRelative(0f, -0.583f, -0.375f, -0.958f)
                reflectiveQuadToRelative(-0.917f, -0.375f)
                horizontalLineTo(11.375f)
                quadToRelative(-0.542f, 0f, -0.937f, 0.375f)
                quadToRelative(-0.396f, 0.375f, -0.396f, 0.958f)
                quadToRelative(0f, 0.542f, 0.396f, 0.917f)
                quadToRelative(0.395f, 0.375f, 0.937f, 0.375f)
                close()
                moveToRelative(-3.5f, 17.208f)
                horizontalLineToRelative(24.25f)
                verticalLineTo(7.875f)
                horizontalLineTo(7.875f)
                close()
                moveToRelative(0f, -24.208f)
                verticalLineToRelative(24.208f)
                close()
            }
        }.build()
    }
}