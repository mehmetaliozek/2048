package com.mehmetaliozek.ttfe.utils.enums

import androidx.compose.ui.graphics.Color

enum class TileColor(val value: Int, val color: Color) {
    C0(0, Color(0xFFCDC1B4),),
    C2(2, Color(0xFFEEE4DA)),
    C4(4, Color(0xFFEEE1C9)),
    C8(8, Color(0xFFF3B27A)),
    C16(16, Color(0xFFF69664)),
    C32(32, Color(0xFFF77C5F)),
    C64(64, Color(0xFFF75F3B)),
    C128(128, Color(0xFFEDD073)),
    C256(256, Color(0xFFEDCC62)),
    C512(512, Color(0xFFEDC950)),
    C1024(1024, Color(0xFFEDC53F)),
    C2048(2048, Color(0xFFEDC22E)),
    C4096(4096, Color(0xFFF17C72)),
    C8192(8192, Color(0xFFED4860)),
    C16384(16384, Color(0xFFED1C2A)),
    C32768(32768, Color(0xFF719AFF)),
    C65536(65536, Color(0xFF59B6FF)),
    C131072(131072, Color(0xFF327BFF));

    companion object {
        fun fromValue(value: Int): TileColor {
            return entries.find { it.value == value } ?: C131072
        }
    }
}
