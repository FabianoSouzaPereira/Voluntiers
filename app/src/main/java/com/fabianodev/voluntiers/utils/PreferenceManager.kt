/*
 * Author: Fabiano Pereira
 *
 * This class, PreferenceManager, is designed to simplify the usage of SharedPreferences in Android applications.
 * It provides methods for storing and retrieving values of various data types such as String, Int, Boolean,
 * Long, Float, and Set<String> in SharedPreferences.
 *
 * Valid data types for this class:
 * - String
 * - Int
 * - Boolean
 * - Long
 * - Float
 * - Set<String>
 */

package com.fabianodev.voluntiers.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = preferences.edit()

    /**
     *   Return a instance os SharedPreferences.
     *   @return preferences that has context.getSharedPreferences("Preferences", Context.MODE_PRIVATE).
     */
    fun getPreferencesInstance(): SharedPreferences {
        return preferences
    }

    /**
     * Manages the storage of a string preference in SharedPreferences.
     * @param key The key associated with the preference.
     * @param value The string value to be saved.
     */
    fun savePreferenceString(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
    }

    /**
     * Retrieves a string preference from SharedPreferences.
     * @param key The key associated with the preference.
     * @param defaultValue The default value to return if the preference does not exist.
     * @return The string value associated with the key, or the default value if not found.
     */
    fun getPreferenceString(key: String, defaultValue: String): String {
        return preferences.getString(key, defaultValue) ?: defaultValue
    }

    /**
     * Manages the storage of an integer preference in SharedPreferences.
     * @param key The key associated with the preference.
     * @param value The integer value to be saved.
     */
    fun savePreferenceInt(key: String, value: Int) {
        editor.putInt(key, value)
        editor.apply()
    }

    /**
     * Retrieves an integer preference from SharedPreferences.
     * @param key The key associated with the preference.
     * @param defaultValue The default value to return if the preference does not exist.
     * @return The integer value associated with the key, or the default value if not found.
     */
    fun getPreferenceInt(key: String, defaultValue: Int): Int {
        return preferences.getInt(key, defaultValue)
    }

    /**
     * Manages the storage of a boolean preference in SharedPreferences.
     * @param key The key associated with the preference.
     * @param value The boolean value to be saved.
     */
    fun savePreferenceBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    /**
     * Retrieves a boolean preference from SharedPreferences.
     * @param key The key associated with the preference.
     * @param defaultValue The default value to return if the preference does not exist.
     * @return The boolean value associated with the key, or the default value if not found.
     */
    fun getPreferenceBoolean(key: String, defaultValue: Boolean): Boolean {
        return preferences.getBoolean(key, defaultValue)
    }

    /**
     * Manages the storage of a long integer preference in SharedPreferences.
     * @param key The key associated with the preference.
     * @param value The long integer value to be saved.
     */
    fun savePreferenceLong(key: String, value: Long) {
        editor.putLong(key, value)
        editor.apply()
    }

    /**
     * Retrieves a long integer preference from SharedPreferences.
     * @param key The key associated with the preference.
     * @param defaultValue The default value to return if the preference does not exist.
     * @return The long integer value associated with the key, or the default value if not found.
     */
    fun getPreferenceLong(key: String, defaultValue: Long): Long {
        return preferences.getLong(key, defaultValue)
    }

    /**
     * Manages the storage of a floating-point preference in SharedPreferences.
     * @param key The key associated with the preference.
     * @param value The floating-point value to be saved.
     */
    fun savePreferenceFloat(key: String, value: Float) {
        editor.putFloat(key, value)
        editor.apply()
    }

    /**
     * Retrieves a floating-point preference from SharedPreferences.
     * @param key The key associated with the preference.
     * @param defaultValue The default value to return if the preference does not exist.
     * @return The floating-point value associated with the key, or the default value if not found.
     */
    fun getPreferenceFloat(key: String, defaultValue: Float): Float {
        return preferences.getFloat(key, defaultValue)
    }

    /**
     * Manages the storage of a set of strings preference in SharedPreferences.
     * @param key The key associated with the preference.
     * @param value The set of strings to be saved.
     */
    fun savePreferenceStringSet(key: String, value: Set<String>) {
        editor.putStringSet(key, value)
        editor.apply()
    }

    /**
     * Retrieves a set of strings preference from SharedPreferences.
     * @param key The key associated with the preference.
     * @param defaultValue The default value to return if the preference does not exist.
     * @return The set of strings associated with the key, or the default value if not found.
     */
    fun getPreferenceStringSet(key: String, defaultValue: Set<String>): Set<String> {
        return preferences.getStringSet(key, defaultValue) ?: defaultValue
    }
}
