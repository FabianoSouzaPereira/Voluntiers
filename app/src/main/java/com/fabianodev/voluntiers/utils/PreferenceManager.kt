package com.fabianodev.voluntiers.utils

import android.content.SharedPreferences

class PreferenceManager(private val preferences: SharedPreferences) {

    private val editor: SharedPreferences.Editor = preferences.edit()

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
