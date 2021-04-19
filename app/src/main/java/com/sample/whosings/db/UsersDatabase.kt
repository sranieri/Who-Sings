package com.sample.whosings.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sample.whosings.db.dao.UserDao
import com.sample.whosings.db.model.User

@Database(entities = [User::class], exportSchema = false, version = 1)
abstract class UsersDatabase : RoomDatabase(){
    companion object{
        const val DB_NAME = "users_db"

        var instance: UsersDatabase? = null

        @Synchronized
        fun getInstance(context: Context) : UsersDatabase?{
            return try {
                if(instance == null){
                    instance = Room.databaseBuilder(context.applicationContext, UsersDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                }
                instance
            } catch (e: Exception) {
                null
            }
        }
    }

    abstract fun userDao(): UserDao

}