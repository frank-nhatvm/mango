package com.fatherofapps.mango

import org.apache.commons.codec.binary.Base64

object Mango {
    fun encode(message: String) : String{
        return String(Base64.encodeBase64(message.toByteArray()))
    }

    fun decode(message: String): String{
        return String(Base64.decodeBase64(message))
    }
}