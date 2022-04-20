package com.demo.quiz.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.quiz.model.Results

@Database(entities = [Results::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun resultsDao(): ResultsDao
}