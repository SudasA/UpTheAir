package com.example.upintheair

import android.text.InputFilter
import java.util.*

fun loginFilter(): InputFilter {
    return InputFilter { source, start, end, dest, dstart, dend ->
        if (dest != null && source != null) {
            if(source == "@" && dest.contains("@"))
                return@InputFilter ""
            if (dest.toString() == "" && source != "@")
                return@InputFilter "@${source.toString().toLowerCase(Locale.ENGLISH)}"
            if (dest.toString() != "" && dest[0] != '@')
                    return@InputFilter "@${source.toString().toLowerCase(Locale.ENGLISH)}"
            if (dstart == 0 && dend == 1)
                if (dest.toString() != "@")
                    return@InputFilter "@${source.toString().toLowerCase(Locale.ENGLISH)}"
                else
                    return@InputFilter ""
            else
                return@InputFilter source.toString().toLowerCase(Locale.ENGLISH)
        }
        null
    }
}