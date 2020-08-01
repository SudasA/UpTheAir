package com.example.upintheair

import com.example.upintheair.entity.Wish

object FakeRepository {
    fun getData(): MutableList<Wish> {
        var list: MutableList<Wish> = mutableListOf()
        for (i: Int in 1..100) list.add(
            Wish(
                i,
                "Название $i",
                "Ооооооочень длинное описание $i $i $i $i $i $i $i $i $i $i $i $i $i $i $i $i $i $i $i"
            )
        )
        return list
    }
}