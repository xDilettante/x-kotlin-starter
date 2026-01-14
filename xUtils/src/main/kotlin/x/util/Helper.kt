package x.util

import java.net.InetAddress
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

fun ByteArray.toByteString(): String =
    joinToString(prefix = "b'", separator = "") { byte ->
        "\\x" + byte.toUByte().toString(16).padStart(2, '0')
    } + "'"

/** Возвращает двоичное представление байта. */
fun UByte.toBinary(): String = toString(2).padStart(8, '0')

fun ByteArray.toBinary(): List<String> =
    flatMap { byte ->
        byte.toUByte().toString(2).padStart(8, '0').map { it.toString() }
    }

fun ByteArray.toHexString(prefix: String = ""): String =
    joinToString(prefix = prefix, separator = "") { eachByte -> "%02x".format(eachByte) }

fun ByteArray.toHex(): String = joinToString(separator = "") { eachByte -> "%02x".format(eachByte) }

fun String.decodeHex(): ByteArray {
    check(length % 2 == 0) { "Hex string must have even length" }

    return chunked(2)
        .map { chunk ->
            chunk.toIntOrNull(16)?.toByte()
                ?: error("Invalid hex character in: $chunk")
        }
        .toByteArray()
}

@OptIn(ExperimentalEncodingApi::class)
fun String.decodeBase64(): ByteArray = Base64.decode(this)

fun intToIp(ipInt: Int): String = intToIp(ipInt.toUInt())

/** Конвертация IP-адреса из UInt/Int в строку dotted-decimal. */
fun intToIp(ipInt: UInt): String =
    InetAddress.getByAddress(
        byteArrayOf(
            ((ipInt shr 24) and 0xFFu).toByte(),
            ((ipInt shr 16) and 0xFFu).toByte(),
            ((ipInt shr 8) and 0xFFu).toByte(),
            (ipInt and 0xFFu).toByte(),
        ),
    ).hostAddress
