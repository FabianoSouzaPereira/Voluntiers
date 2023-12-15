package com.fabianodev.voluntiers.utils

class Shared {
    companion object {
        @JvmStatic
        var instance = Shared()
    }

    init {
        instance = this
    }
}