package com.sample.whosings.data.repository

import com.sample.whosings.db.dao.UserDao
import com.sample.whosings.db.model.User

class UsersRepository(val userDao: UserDao) {

    fun saveUser(username: String){
        updateCurrentUser(username)
    }

    fun updateCurrentUserScore(score: Int){
        getCurrentUser()?.let {
            updateUserScore(it, score)
        }
    }

    fun updateUserScore(username: String, score: Int){
        val newScore = getUserScore(username) + score
        userDao.updateUser(User(username, newScore))
    }

    fun updateUserScore(user: User, score: Int){
        user.score += score
        userDao.updateUser(user)
    }

    fun getUserScore(username: String) : Int{
        return userDao.getUser(username).score
    }

    fun getUsers(): List<User>{
        return userDao.getUsers()
    }

    fun getCurrentUser() : User?{
        return userDao.getCurrentUser()
    }

    fun updateCurrentUser(username: String){
        val users = userDao.getUsers()
        users.forEach {
            it.isCurrent = it.username == username
            userDao.updateUser(it)
        }
        if(users.firstOrNull { it.username == username } == null){
            userDao.insertUser(User(username, isCurrent = true))
        }
    }

    fun logoutUser(){
        val currentUser = getCurrentUser()
        currentUser?.let {
            it.isCurrent = false
            userDao.updateUser(it)
        }
    }
}