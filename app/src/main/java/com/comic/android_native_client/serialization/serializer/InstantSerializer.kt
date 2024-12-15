package com.comic.android_native_client.serialization.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.Instant

@Serializer(forClass = Instant::class)
object InstantSerializer : KSerializer<Instant> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Instant", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Instant) {
        encoder.encodeString(value.toString()) // Serialize thành chuỗi ISO-8601
    }

    override fun deserialize(decoder: Decoder): Instant {
        return Instant.parse(decoder.decodeString()) // Deserialize từ chuỗi ISO-8601
    }
}

@Serializer(forClass = Instant::class)
object InstantAsMillisSerializer : KSerializer<Instant> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Instant", PrimitiveKind.LONG)

    override fun serialize(encoder: Encoder, value: Instant) {
        encoder.encodeLong(value.toEpochMilli()) // Serialize thành số milliseconds
    }

    override fun deserialize(decoder: Decoder): Instant {
        return Instant.ofEpochMilli(decoder.decodeLong()) // Deserialize từ milliseconds
    }
}

