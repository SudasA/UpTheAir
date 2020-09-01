package com.example.upintheair.SharedPreferences

import android.content.SharedPreferences
import android.content.res.Resources
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

@Suppress("UNCHECKED_CAST")
class PreferencesDelegate<T>(
    val preferences: SharedPreferences,
    private val name: String,
    private val value: T
) : ReadWriteProperty<Any?, T> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        with(preferences) {
            return when (value) {
                is Boolean -> (getBoolean(name, value) as? T) ?: value
                is Int -> (getInt(name, value) as T) ?: value
                is Float -> (getFloat(name, value) as T) ?: value
                is Long -> (getLong(name, value) as T) ?: value
                is String -> (getString(name, value) as T) ?: value
                else -> error("Only primitive types can be stored in SharedPreferences")
            }
        }
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        with(preferences.edit()) {
            when (value) {
                is Boolean -> putBoolean(name, value)
                is Int -> putInt(name, value)
                is Float -> putFloat(name, value)
                is Long -> putLong(name, value)
                is String -> putString(name, value)
                else -> error("Only primitive types can be stored in SharedPreferences")
            }
            apply()
        }
    }


}