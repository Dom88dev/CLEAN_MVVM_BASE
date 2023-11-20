package com.dom.presentation.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.dom.presentation.util.Extensions.remove
import com.dom.presentation.util.Extensions.set
import java.nio.charset.StandardCharsets
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import kotlin.reflect.KClass

class SharedPreferenceManager(val context: Context) {
    private val legacyPreferenceName = "legacy_pref"
    private val encryptedPreferenceName = "encrypted_app_pref"

    private var prefs: SharedPreferences

    init {
        prefs = initializedEncryptedSharedPreference()
        val legacyPrefs = context.getSharedPreferences(legacyPreferenceName, Context.MODE_PRIVATE)
        if(legacyPrefs.contains(Constants.PREF_ACCESS_TOKEN)) {
            val accessToken = legacyPrefs.getString(Constants.PREF_ACCESS_TOKEN, "") ?: ""
            if(accessToken.isNotBlank()) {
                prefs.set(Constants.PREF_ACCESS_TOKEN, AES256Util.decrypt(accessToken))
            }
            legacyPrefs.edit { clear() }
        }
    }

    private fun initializedEncryptedSharedPreference(): SharedPreferences {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        return EncryptedSharedPreferences.create(
            context,
            encryptedPreferenceName,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
//        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
//        return EncryptedSharedPreferences.create(
//            encryptedPreferenceName,
//            masterKeyAlias,
//            context,
//            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
//            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
//        )
    }

    fun <T: Any> set(key: String, value: T) {
        prefs.set(key, value)
    }

    fun getString(key: String, defaultValue: String): String {
        return getValue(key, defaultValue, String::class)
    }

    fun <T: Any>getValue(key: String, defaultValue: T, typeClass: KClass<*>): T {
        val value = prefs.all[key]
        return if (typeClass.isInstance(value)) value as T
        else defaultValue
    }

    fun remove(key: String) {
        prefs.remove(key)
    }

    class AES256Util {

        companion object {
            private const val alg = "AES/CBC/PKCS5Padding"
            private const val key = "H@McQfThWmZq4t7w!z%C*F-JaNdRgUkX"
            private val iv = key.substring(0, 16) // 16byte

            fun decrypt(cipherText: String): String {
                val cipher = Cipher.getInstance(alg)
                val keySpec = SecretKeySpec(key.toByteArray(), "AES")
                val ivParamSpec = IvParameterSpec(iv.toByteArray())
                cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec)
                val decodedBytes = Base64.decode(cipherText, Base64.DEFAULT)
                val decrypted = cipher.doFinal(decodedBytes)

                return String(decrypted, StandardCharsets.UTF_8)
            }
        }

    }

}