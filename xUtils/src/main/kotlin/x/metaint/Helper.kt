package x.metaint

import java.net.InetAddress
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

fun ByteArray.toByteString() = this.joinToString(prefix = "b'", separator = "") { "\\x" + it.toUByte().toString(16).padStart(2, '0') } + "'"

// Преобразование байтов в двоичное представление
//fun UByte.toBinary(): String = String.format("%8s", this.toString(2)).replace(" ".toRegex(), "0")
fun UByte.toBinary(): String = this.toString(2).padStart(8, '0')
fun ByteArray.toBinary(): List<String> {
    return this.flatMap { byte ->
        byte.toUByte().toString(2).padStart(8, '0').map { it.toString() }
    }
}


fun ByteArray.toHexString(prefix: String = ""): String =
    joinToString(prefix = prefix, separator = "") { eachByte -> "%02x".format(eachByte) }
// Конвертация ByteArray в hex-строку
fun ByteArray.toHex(): String = joinToString(separator = "") { eachByte -> "%02x".format(eachByte) }

// Функция для конвертации hex-строки в ByteArray от Меня
fun String.decodeHex(): ByteArray {
    check(length % 2 == 0) { "Must have an even length" }

    return chunked(2)
        .map { it.toInt(16).toByte() }
        .toByteArray()
}

//// Функция для конвертации hex-строки в ByteArray от GPT
//fun String.decodeHex(): ByteArray {
//    check(length % 2 == 0) { "Hex string must have even length" }
//
//    return chunked(2)
//        .map { chunk -> chunk.toIntOrNull(16)?.toByte() ?: error("Invalid hex character in: $chunk") }
//        .toByteArray()
//}

// Функция для декодирования base64-строки в ByteArray
@OptIn(ExperimentalEncodingApi::class)
fun String.decodeBase64(): ByteArray {
    return Base64.decode(this)
}

// Конвертация IP-адреса из Long в строку
fun intToIp(ipInt: Int): String {
    return InetAddress.getByAddress(
        byteArrayOf(
            ((ipInt shr 24) and 0xFF).toByte(),
            ((ipInt shr 16) and 0xFF).toByte(),
            ((ipInt shr 8) and 0xFF).toByte(),
            (ipInt and 0xFF).toByte()
        )
    ).hostAddress
}

