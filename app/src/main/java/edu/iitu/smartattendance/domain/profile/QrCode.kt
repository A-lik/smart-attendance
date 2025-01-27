package edu.iitu.smartattendance.domain.profile

import kotlinx.serialization.Serializable

private const val QR_PREFIX = "fid"

@Serializable
@JvmInline
value class QrCode private constructor(val value: String) {

    companion object {
        fun from(value: String): QrCode? {
            return if (!value.startsWith(QR_PREFIX)) null
            else QrCode(value)
        }
    }
}