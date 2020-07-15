package com.example.upintheair

import android.text.InputFilter

fun loginFilter(): InputFilter {
    return InputFilter { source, start, end, dest, dstart, dend ->
        if (dest != null && source != null) {
            if (dest.toString() == "" && source != "@")
                return@InputFilter "@$source"
            if (dest.toString() != "" && dest[0] != '@')
                    return@InputFilter "@$source"
            if (dstart == 0 && dend == 1)
                if (dest.toString() != "@")
                    return@InputFilter "@$source"
                else
                    return@InputFilter ""
        }
        null
    }
}