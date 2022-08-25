package com.ansh.mvvm

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Quote::class],version = 1)

abstract class QuizDatabase : RoomDatabase() {

    abstract fun quizDao() : QuizDao

    companion object{
        private var INSTANCE : QuizDatabase? =null
        fun getDatabase(context: Context) : QuizDatabase{
            if(INSTANCE == null){

                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context,QuizDatabase::class.java,"quiz_database")
                        .build()
                }

            }
            return INSTANCE!!
        }
    }
 }