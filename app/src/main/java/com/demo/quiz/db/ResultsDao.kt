package com.demo.quiz.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.demo.quiz.model.Results

@Dao
interface ResultsDao {

    @Query("SELECT * FROM results")
    fun getAll(): List<Results>

    @Insert
    fun insertAll(users: List<Results>)

//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    fun loadAllByIds(userIds: IntArray): List<User>
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): User
}