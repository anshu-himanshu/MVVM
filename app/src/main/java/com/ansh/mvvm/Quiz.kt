package com.ansh.mvvm

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz")
data class Quote(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val question: String,
    val opt1: String,
    val opt2: String,
    val opt3: String,
    val opt4: String,
    val submitted_answer: String,
    val answer: String
)