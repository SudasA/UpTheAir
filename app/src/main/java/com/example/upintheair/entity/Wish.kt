package com.example.upintheair.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishes")
data class Wish(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "wish_id") val id: Int?,
    @ColumnInfo(name = "wish_name") val name: String,
    @ColumnInfo(name = "wish_description") val description: String
)