package com.comic.shareable_theme.ui.theme.constants

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val DarkColorScheme =
    darkColorScheme(
        primary = Color(0xFF4587e0),
        primaryContainer = Color(0xFF1C4D8A), // Nền cho thành phần liên quan đến màu chính
        onPrimaryContainer = Color(0xFFB3D4FF), // Văn bản trên nền màu chính
        inversePrimary = Color(0xFF5C9DFF), // Màu chính đảo ngược (sử dụng khi cần hiệu ứng đảo ngược)

        secondary = Color(0xFFE91E63), // Màu phụ
        onSecondary = Color(0xFFFFFFFF), // Văn bản trên màu phụ
        secondaryContainer = Color(0xFF700035), // Nền cho thành phần liên quan đến màu phụ
        onSecondaryContainer = Color(0xFFFFD8E4), // Văn bản trên nền màu phụ

        tertiary = Color(0xFF4CAF50), // Màu tam cấp
        onTertiary = Color(0xFFFFFFFF), // Văn bản trên màu tam cấp
        tertiaryContainer = Color(0xFF003E24), // Nền cho thành phần liên quan đến màu tam cấp
        onTertiaryContainer = Color(0xFFB9F6CA), // Văn bản trên nền màu tam cấp


        background = Color(0xFF232531),
        onBackground = Color(0xFFECEDEE),

        surface = Color(0xFF1E1E26), // Sáng hơn `background` một chút, tạo sự khác biệt.
        onSurface = Color(0xFFECEDEE), // Giữ nguyên màu văn bản sáng.
        surfaceContainer = Color(0xFF2A2A36), // Sáng hơn `surface`, phù hợp cho các container nổi bật.
        surfaceDim = Color(0xFF12121A), // Tối hơn `surface`, dùng để tạo chiều sâu.
        surfaceVariant = Color(0xFF2E2E3C), // Gần giống `surfaceContainer`, dùng cho biến thể bề mặt.

        surfaceTint = Color(0xFF4587E0), // Màu nhấn trên bề mặt
        inverseSurface = Color(0xFFECEDEE), // Nền bề mặt đảo ngược
        inverseOnSurface = Color(0xFF121212), // Văn bản trên nền bề mặt đảo ngược

        surfaceBright = Color(0xFF2A2A36), // Bề mặt sáng
        surfaceContainerHigh = Color(0xFF333344), // Nền container độ cao cao
        surfaceContainerHighest = Color(0xFF38384C), // Nền container độ cao cao nhất
        surfaceContainerLow = Color(0xFF272732), // Nền container độ cao thấp
        surfaceContainerLowest = Color(0xFF1A1A26), // Nền container thấp nhất

        error = Color(0xCF6679),
        onError = Color(0x000000),
        errorContainer = Color(0xFF370B0E), // Nền cho thành phần liên quan đến lỗi
        onErrorContainer = Color(0xFFFFB4A9), // Văn bản trên nền màu lỗi

        outline = Color(0xFF737373), // Màu viền
        outlineVariant = Color(0xFF424242), // Biến thể màu viền
        scrim = Color(0xCC000000), // Màu nền mờ (dùng cho overlay)


    )

val LightColorScheme = lightColorScheme(
    primary = Color(0xFF5C9DFF), // Màu chính
    onPrimary = Color(0xFFFFFFFF), // Văn bản trên màu chính
    primaryContainer = Color(0xFFD6E6FF), // Nền cho thành phần liên quan đến màu chính
    onPrimaryContainer = Color(0xFF002F65), // Văn bản trên nền màu chính

    inversePrimary = Color(0xFF4587E0), // Màu chính đảo ngược (sử dụng khi cần hiệu ứng đảo ngược)

    secondary = Color(0xFFE91E63), // Màu phụ
    onSecondary = Color(0xFFFFFFFF), // Văn bản trên màu phụ
    secondaryContainer = Color(0xFFFFD8E4), // Nền cho thành phần liên quan đến màu phụ
    onSecondaryContainer = Color(0xFF700035), // Văn bản trên nền màu phụ

    tertiary = Color(0xFF4CAF50), // Màu tam cấp
    onTertiary = Color(0xFFFFFFFF), // Văn bản trên màu tam cấp
    tertiaryContainer = Color(0xFFB9F6CA), // Nền cho thành phần liên quan đến màu tam cấp
    onTertiaryContainer = Color(0xFF003E24), // Văn bản trên nền màu tam cấp

    background = Color(0xFFECEDEE), // Nền chính của giao diện
    onBackground = Color(0xFF121212), // Văn bản trên nền chính

    surface = Color(0xFFFFFFFF), // Nền cho bề mặt
    onSurface = Color(0xFF121212), // Văn bản trên bề mặt
    surfaceVariant = Color(0xFFE0E0E0), // Biến thể của bề mặt (dùng cho thẻ hoặc hộp)
    onSurfaceVariant = Color(0xFF424242), // Văn bản trên nền bề mặt biến thể

    surfaceTint = Color(0xFF5C9DFF), // Màu nhấn trên bề mặt
    inverseSurface = Color(0xFF121212), // Nền bề mặt đảo ngược
    inverseOnSurface = Color(0xFFECEDEE), // Văn bản trên nền bề mặt đảo ngược

    surfaceBright = Color(0xFFF6F6F6), // Bề mặt sáng
    surfaceContainer = Color(0xFFECECEC), // Nền chính cho container
    surfaceContainerHigh = Color(0xFFE0E0E0), // Nền container độ cao cao
    surfaceContainerHighest = Color(0xFFD6D6D6), // Nền container độ cao cao nhất
    surfaceContainerLow = Color(0xFFF6F6F6), // Nền container độ cao thấp
    surfaceContainerLowest = Color(0xFFFFFFFF), // Nền container thấp nhất
    surfaceDim = Color(0xFFE0E0E0), // Nền bề mặt mờ

    error = Color(0xFFB00020), // Màu lỗi
    onError = Color(0xFFFFFFFF), // Văn bản trên màu lỗi
    errorContainer = Color(0xFFFFDAD4), // Nền cho thành phần liên quan đến lỗi
    onErrorContainer = Color(0xFF410001), // Văn bản trên nền màu lỗi

    outline = Color(0xFF737373), // Màu viền
    outlineVariant = Color(0xFFB0BEC5), // Biến thể màu viền
    scrim = Color(0x33000000), // Màu nền mờ (dùng cho overlay)


)
