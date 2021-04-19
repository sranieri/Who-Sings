package com.sample.whosings.db.dao

import androidx.room.*
import com.sample.whosings.db.model.User

@Dao
interface UserDao {

    @Query("Select * from user")
    fun getUsers() : List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("Select * from user where username = :username")
    fun getUser(username: String) : User

    @Query("Select * from user where isCurrentUser = 1")
    fun getCurrentUser() : User?
}