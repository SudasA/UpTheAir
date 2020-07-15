package com.example.upintheair

import android.text.InputFilter

fun loginFilter() : InputFilter {
    return InputFilter { source, start, end, dest, dstart, dend ->
//        if (dest == "")
         if (source != null && source != "")
            if (source[start] != '@' && dend == 0)
                return@InputFilter "@$source"
        null
    }
}